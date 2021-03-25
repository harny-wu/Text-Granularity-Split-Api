package com.gdut.wdy.textgranularitysplitapi.util;


import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @ClassName : longAndShortSentenceSplitter @Description :长短句分割器 @Author : WDD @Date: 2021-01-22
 * 22:15
 */
@Slf4j
public class LongAndShortSentenceSplitter {
  // 长文本结束符号
  public static String[] longSentenceSymbol = {"。", ".", "！", "!", "\n", "\r","~","..."};
  public static Set<String> longSentenceSymbolSet =
      new HashSet(Arrays.asList(longSentenceSymbol));
  // 短文本结束的符号（未包括"（）"，"()"）
  public static String[] ShortSentenceSymbol = {",", "，", ":", "：", ";", "；","\t"," "};
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
    int leftParenthesisIndex=0;
    for (int i = 0; i < origintext.length(); i++) {
      String curr = String.valueOf(origintext.charAt(i));
      //当最后没有以标点符号作为结束时，将该句划为短句
      if(i==origintext.length()-1&&!longSentenceSymbolSet.contains(curr)&&!ShortSentenceSymbolSet.contains(curr)){
        shortSentences.add(origintext.substring(shortBeginIndex,i+1));
        break;
      }
      if (longSentenceSymbolSet.contains(curr)) {
        longSentences.add(origintext.substring(longBeginIndex, i));
        shortSentences.add(origintext.substring(shortBeginIndex,i));
        longBeginIndex = i + 1;
        shortBeginIndex=i+1;
      } else if (ShortSentenceSymbolSet.contains(curr)) {
        shortSentences.add(origintext.substring(shortBeginIndex, i));
        shortBeginIndex = i + 1;
      } else if (curr.equals("(")||curr.equals("（")){
        leftParenthesisIndex=i;
      } else if (curr.equals(")")||curr.equals("）")){
        shortSentences.add(origintext.substring(leftParenthesisIndex,i+1));
      }
    }
    result.add(longSentences);
    result.add(shortSentences);
    return result;
  }

  public static void main(String[] args) {
    List<List<String>>result=LongAndShortSentenceSplitter.SplitlongAndShortSentence("客栈很整洁，房间里设施齐全。大叔人很好，帮忙订门票，车票还免费接送。下次有机会会再去。景区很漂亮，值得一玩，客栈离景区不远，周围也有很多超市，饭店，很方便。");
    Iterator i =result.get(1).iterator();
    while (i.hasNext()){
      System.out.println(i.next().toString());
    }
}
}
