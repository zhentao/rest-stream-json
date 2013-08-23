package com.zhentao.stream.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonFactory;
import com.zhentao.stream.config.EmbeddedDataSourceConfig;
import com.zhentao.stream.model.Creative;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EmbeddedDataSourceConfig.class)
@ActiveProfiles("integration")
public class CreativeDaoTest {
    @Autowired
    private DataSource dataSource;

    private CreativeDaoImpl creativeDao;

    @Before
    public void setUp() {
        creativeDao = new CreativeDaoImpl(new JsonFactory());
        creativeDao.setDataSource(dataSource);
    }

    @Test
    public void testFindById() {
        long id = 1;
        Creative creative = creativeDao.findById(id);
        assertThat(creative.getId(), is(id));
    }

    @Test
    public void testFindByCreated() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2010);
        List<Creative> creatives = creativeDao.findByDate(cal.getTime());
        assertThat(creatives.size(), is(2));
    }
}
