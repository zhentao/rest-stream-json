package com.zhentao.stream.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.zhentao.stream.model.Creative;
import com.zhentao.stream.service.CreativeService;

@Controller
@RequestMapping(value = "creatives")
public class CreativeController {
    private final CreativeService creativeService;

    @Autowired
    public CreativeController(CreativeService creativeService) {
        this.creativeService = creativeService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
                                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Creative get(@PathVariable("id") long id) {

        return creativeService.findCreativeById(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Creative> getCreativesCreatedAfter(
                                    @RequestParam("after") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date after) {
        return creativeService.findByDate(after);
    }

    @RequestMapping(value = "stream", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public void streamCreativesCreatedAfter(
                                    @RequestParam("after") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date after,
                                    HttpServletResponse response) throws IOException {
        creativeService.streamCreatives(after, response.getOutputStream());
    }
}
