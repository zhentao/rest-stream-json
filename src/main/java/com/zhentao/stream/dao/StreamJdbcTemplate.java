package com.zhentao.stream.dao;

import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class StreamJdbcTemplate extends JdbcTemplate {

    public StreamJdbcTemplate(final DataSource dataSource) {
        super(dataSource);
    }

    public StreamJdbcTemplate(final DataSource dataSource, final boolean lazyInit) {
        super(dataSource, lazyInit);
    }

    /**
     * Prepare the given JDBC Statement (or PreparedStatement or CallableStatement), applying statement settings such as
     * fetch size, max rows, and query timeout. Unlike in {@link JdbcTemplate} you can also specify a negative fetch
     * size.
     *
     * @param stmt
     *            the JDBC Statement to prepare
     * @throws java.sql.SQLException
     *             if thrown by JDBC API
     * @see #setFetchSize
     * @see #setMaxRows
     * @see #setQueryTimeout
     * @see org.springframework.jdbc.datasource.DataSourceUtils#applyTransactionTimeout
     */
    @Override
    protected void applyStatementSettings(final Statement stmt) throws SQLException {
        int fetchSize = getFetchSize();
        stmt.setFetchSize(fetchSize);

        int maxRows = getMaxRows();
        if (maxRows > 0) {
            stmt.setMaxRows(maxRows);
        }
        DataSourceUtils.applyTimeout(stmt, getDataSource(), getQueryTimeout());
    }
}
