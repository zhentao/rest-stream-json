package com.zhentao.stream.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import com.zhentao.stream.model.Creative;


public interface CreativeService {
    Creative findCreativeById(long id);

    List<Creative> findByDate(Date after);

    void streamCreatives(final Date after, final OutputStream stream) throws IOException;
}
