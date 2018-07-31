package com.example.multimedia.service.ServiceImpl;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Pinyin {
    HanyuPinyinOutputFormat hanyuPinyinOutputFormat = null;

    private String[] pinyin;

    public Pinyin() {
        hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
        hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        pinyin = null;
    }

    //转换单个中文字符
    public String getCharacterPinYin(char c) {
        try {
            pinyin = PinyinHelper.toHanyuPinyinStringArray(c, hanyuPinyinOutputFormat);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }

        //如果c不是汉字，返回null
        if (null == pinyin) {
            return null;
        }

        //多音字返回第一个值
        return pinyin[0];
    }

    //转换一个字符串
    public String getStringPinYin(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); ++i) {
            String tmp = getCharacterPinYin(str.charAt(i));
            if (null == tmp) {
                //如果str.charAt(i)不是汉字，则保持原样
                stringBuilder.append(str.charAt(i));
            } else {
                stringBuilder.append(tmp);
            }
        }
        return stringBuilder.toString().trim();
    }
}
