package com.zhentao.stream.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import com.zhentao.stream.dao.CreativeDao;
import com.zhentao.stream.model.Creative;


public class CreativeServiceImpl implements CreativeService {

    private final CreativeDao creativeDao;

    public CreativeServiceImpl(CreativeDao creativeDao) {
        this.creativeDao = creativeDao;

    }

    @Override
    public Creative findCreativeById(long id) {
        return creativeDao.findById(id);
    }

    @Override
    public List<Creative> findByDate(Date after) {
        return creativeDao.findByDate(after);
    }

    @Override
    public void streamCreatives(final Date after, final OutputStream stream) throws IOException {
        creativeDao.streamCreatives(after, stream);
    }
}
