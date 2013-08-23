package com.zhentao.stream.dao;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.zhentao.stream.exception.CreativeNotFoundException;
import com.zhentao.stream.model.Creative;


public class CreativeDaoImpl extends JdbcDaoSupport implements CreativeDao {

    private static final String SELECT_CREATIVE_BY_ID = "SELECT * from creative WHERE id = ?";
    private static final String SELECT_CREATIVES_BY_DATE = "SELECT * from creative WHERE created >= ?";

    private final JsonFactory jsonFactory;

    public CreativeDaoImpl(JsonFactory jsonFactory) {
        this.jsonFactory = jsonFactory;
    }

    @Override
    public Creative findById(long creativeId) {
        try {
            Creative creative = getJdbcTemplate().queryForObject(SELECT_CREATIVE_BY_ID, new Object[] { creativeId },
                                            new BeanPropertyRowMapper<Creative>(Creative.class));
            return creative;
        } catch (EmptyResultDataAccessException e) {
            throw new CreativeNotFoundException(creativeId);
        }
    }

    @Override
    public List<Creative> findByDate(Date after) {
        List<Creative> Creatives = getJdbcTemplate().query(SELECT_CREATIVES_BY_DATE, new Object[] { after },
                                        new BeanPropertyRowMapper<Creative>(Creative.class));
        return Creatives;
    }

    @Override
    public void streamCreatives(final Date after, final OutputStream stream) throws IOException {
        final JsonGenerator jg = jsonFactory.createGenerator(stream, JsonEncoding.UTF8);
        jg.writeStartArray();

        getJdbcTemplate().query(SELECT_CREATIVES_BY_DATE, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setDate(1, new java.sql.Date(after.getTime()));
            }
        }, new RowCallbackHandler() {

            @Override
            public void processRow(ResultSet rs) throws SQLException {
                try {
                    jg.writeStartObject();

                    jg.writeNumberField("id", rs.getLong("id"));
                    jg.writeStringField("screenShotLocation", rs.getString("screenShotLocation"));
                    jg.writeBooleanField("sound", rs.getBoolean("sound"));
                    jg.writeStringField("creativeUrl", rs.getString("creativeUrl"));
                    jg.writeStringField("creativeHtml", rs.getString("creativeHtml"));
                    jg.writeStringField("largestSwf", rs.getString("largestSwf"));
                    jg.writeStringField("largestImg", rs.getString("largestImg"));
                    jg.writeStringField("clickThroughUrl", rs.getString("clickThroughUrl"));
                    jg.writeStringField("landingUrl", rs.getString("landingUrl"));
                    jg.writeNumberField("height", rs.getInt("height"));
                    jg.writeNumberField("width", rs.getInt("width"));
                    jg.writeNumberField("loadTime", rs.getInt("loadTime"));
                    jg.writeStringField("md5", rs.getString("md5"));
                    jg.writeNumberField("created", rs.getDate("created").getTime());

                    jg.writeEndObject();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        jg.writeEndArray();
        jg.close();
    }
}
