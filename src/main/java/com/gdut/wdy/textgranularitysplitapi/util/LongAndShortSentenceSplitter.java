package com.gdut.wdy.textgranularitysplitapi.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @ClassName : longAndShortSentenceSplitter @Description :长短句分割器 @Author : WDD @Date: 2021-01-22
 * 22:15
 */
@Slf4j
public class LongAndShortSentenceSplitter {
    // 长文本结束符号
    public static String[] longSentenceSymbol = {"。", ".", "！", "!", "\n", "\r", "~", "..."};
    public static Set<String> longSentenceSymbolSet =
            new HashSet(Arrays.asList(longSentenceSymbol));
    // 短文本结束的符号（未包括"（）"，"()"）
    public static String[] ShortSentenceSymbol = {",", "，", ":", "：", ";", "；", "\t", " "};
    public static Set<String> ShortSentenceSymbolSet =
            new HashSet(Arrays.asList(ShortSentenceSymbol));

    /**
     * @param origintext 源文本
     * @return List<String>，result[0]为短句列表，result[1]为长句列表
     */
    public static List<List<String>> SplitlongAndShortSentence(String origintext) {
        List<String> longSentences = new ArrayList<>();
        List<String> shortSentences = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();
        int longBeginIndex = 0;
        int shortBeginIndex = 0;
        int leftParenthesisIndex = 0;
        for (int i = 0; i < origintext.length(); i++) {
            String curr = String.valueOf(origintext.charAt(i));
            //当最后没有以标点符号作为结束时，将该句划为短句
            if (i == origintext.length() - 1 && !longSentenceSymbolSet.contains(curr) && !ShortSentenceSymbolSet.contains(curr)) {
                String shortEndText=origintext.substring(shortBeginIndex, i + 1);
                if (!StringUtils.isBlank(shortEndText)){
                    shortSentences.add(shortEndText);
                }
                String longEndText=origintext.substring(longBeginIndex, i + 1);
                if (!StringUtils.isBlank(longEndText)){
                    longSentences.add(longEndText);
                }
                break;
            }
            String longText=origintext.substring(longBeginIndex, i);
            String shortText=origintext.substring(shortBeginIndex, i);
            if (longSentenceSymbolSet.contains(curr)) {
                if (!StringUtils.isBlank(longText)){
                    longSentences.add(longText);
                }
                if (!StringUtils.isBlank(shortText)){
                    shortSentences.add(shortText);
                }
                longBeginIndex = i + 1;
                shortBeginIndex = i + 1;
            } else if (ShortSentenceSymbolSet.contains(curr)) {
                if (!StringUtils.isBlank(shortText)){
                    shortSentences.add(shortText);
                }
                shortBeginIndex = i + 1;
            } else if (curr.equals("(") || curr.equals("（")) {
                leftParenthesisIndex = i;
            } else if (curr.equals(")") || curr.equals("）")) {
                String text=origintext.substring(leftParenthesisIndex, i + 1);
                if (!StringUtils.isBlank(text)){
                    shortSentences.add(text);
                }
            }
        }
        result.add(longSentences);
        result.add(shortSentences);


        return result;
    }

    public static void main(String[] args) {
        List<List<String>> result = LongAndShortSentenceSplitter.SplitlongAndShortSentence("很棒！！房间阳光充足，房间布置温馨，用品齐全干净，卫生情况很好，没有什么味道，空气很流通，住的很舒适，服务员细心体贴地介绍各种情况，值得推荐");
        Iterator i = result.get(0).iterator();
        while (i.hasNext()) {
            System.out.println(i.next().toString());
        }
    }
}
