package com.gdut.wdy.textgranularitysplitapi.service;

import com.gdut.wdy.textgranularitysplitapi.entity.ResponseData;
import com.gdut.wdy.textgranularitysplitapi.entity.Result;
import com.gdut.wdy.textgranularitysplitapi.util.LongAndShortSentenceSplitter;
import com.gdut.wdy.textgranularitysplitapi.util.ResultUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextGranularityService {
    public Result<ResponseData> SingleTextGranularitySplit(String originText){
        List<List<String>> result= LongAndShortSentenceSplitter.SplitlongAndShortSentence(originText);
        ResponseData responseData=ResponseData.builder().originText(originText).longTextList(result.get(0)).shortTextList(result.get(1)).build();
        return ResultUtil.success(responseData);
    }
}
