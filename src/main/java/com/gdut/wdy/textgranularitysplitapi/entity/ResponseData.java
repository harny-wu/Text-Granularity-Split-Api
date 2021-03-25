package com.gdut.wdy.textgranularitysplitapi.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseData {
    String originText;
    List<String>longTextList;
    List<String>shortTextList;
}
