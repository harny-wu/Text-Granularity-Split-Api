package com.gdut.wdy.textgranularitysplitapi.controller;

import com.gdut.wdy.textgranularitysplitapi.entity.ResponseData;
import com.gdut.wdy.textgranularitysplitapi.entity.Result;
import com.gdut.wdy.textgranularitysplitapi.service.TextGranularityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TextGranularityAPI {

    @Autowired
    private TextGranularityService textGranularityService;

    @PostMapping("singleTextGranularitySplit")
    public Result<ResponseData> SingleTextGranularitySplit(@RequestParam("originText") String originText) {
        log.info("singleTextGranularitySplitTask--origin:{}",originText);
        return textGranularityService.SingleTextGranularitySplit(originText);
    }
}
