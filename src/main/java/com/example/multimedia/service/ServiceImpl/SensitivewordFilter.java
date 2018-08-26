package com.example.multimedia.service.ServiceImpl;

import java.io.*;
import java.util.*;

public class SensitivewordFilter {
    private boolean mainflag = false;
    private HashMap sensitiveWordMap;
    private File file = new File("src/main/resources/static/SensitiveWord.txt");

    /**
     * @param sensitiveStr 需要转换的字符串
     * @return 转换结果
     */
    public String turnWord(String sensitiveStr){
        try {
            if (mainflag == false) getKeyWord();
        }catch (Exception e){
            //ignore
        }
        return replaceSensitiveWord(sensitiveStr);
    }

    /**
     * @param word 要添加的敏感词
     */
    public void addSensitiveWord(String word){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
            writer.write(word);
            writer.flush();
            writer.close();
        }catch (Exception e){
            //ignore;
        }
    }


    /**
     * 判断文字是否有敏感字符，并且将敏感字符转换成*号
     * @return 转换后的String
     */
    public String replaceSensitiveWord(String text){
        String result = text;
        //获取文字中的敏感词
        Set<String> set = new HashSet<String>();
        for (int i = 0;i < text.length();i ++){
            int length = checkSensitiveWord(text,i);
            if (length > 0){
                set.add(text.substring(i,i+length));
                i = i+length-1;
            }
        }
        Iterator<String> iterator = set.iterator();
        String word = null;
        String replaceString = null;
        while (iterator.hasNext()){
            word = iterator.next();
            replaceString = getReplaceChars("*",word.length());
            result = result.replaceAll(word,replaceString);
        }
        return result;
    }

    /**
     * 获取替换字符串
     * @param replaceChar
     * @param length
     * @return
     * @version 1.0
     */
    private String getReplaceChars(String replaceChar,int length){
        String resultReplace = replaceChar;
        for(int i = 1 ; i < length ; i++){
            resultReplace += replaceChar;
        }

        return resultReplace;
    }

    /**
     * 检查文字中是否包含敏感字符，检查规则如下：
     * @param txt
     * @param beginIndex
     * @return 如果存在，则返回敏感词字符的长度，不存在返回0
     * @version 1.0
     */
    public int checkSensitiveWord(String txt,int beginIndex){
        try{
            if (mainflag == false) getKeyWord();
        }catch (Exception e){}
        boolean  flag = false;    //敏感词结束标识位：用于敏感词只有1位的情况
        int matchFlag = 0;     //匹配表示数默认为0
        char word = 0;
        Map nowMap = sensitiveWordMap;
        for(int i = beginIndex; i < txt.length() ; i++){
            word = txt.charAt(i);
            if (word == '&' || word == '%' || word == ' ' || word == '*' || word == '.' || word == '_'){
                matchFlag++;
                continue;
            }
            nowMap = (Map) nowMap.get(word);     //获取指定key
            if(nowMap != null){     //存在，则判断是否为最后一个
                matchFlag++;     //找到相应key，匹配标识+1
                if("1".equals(nowMap.get("isEnd"))){       //如果为最后一个匹配规则，结束循环，返回匹配标识数
                    flag = true;       //结束标志位为true
                }
            }
            else{     //不存在，直接返回
                break;
            }
        }
        if(matchFlag < 2 || !flag){        //长度必须大于等于1，为词
            matchFlag = 0;
        }
        return matchFlag;
    }

    /**
     * 打开文件初始化 敏感词库
     * @param
     * @return
     * */
    private void getKeyWord() throws Exception{
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));  //读取敏感词文件
        Set<String> set = new HashSet<String>();
        BufferedReader bufferedReader = new BufferedReader(reader);
        String txt = null;
        while((txt = bufferedReader.readLine())!=null) set.add(txt);  //将所有文件内容去读到set中
        reader.close();
        sensitiveWordMap = new HashMap(set.size()); //初始化敏感词容器。减少扩容
        String key = null; //存储关键字
        Map nowMap = null;
        Map<String,String> newWordMap = null;
        Iterator<String> iterator = set.iterator(); //创建迭代器
        while(iterator.hasNext()){
            key = iterator.next();
            nowMap = sensitiveWordMap;
            for (int i = 0;i < key.length();i ++){
                char keyChar = key.charAt(i); //转成char
                Object wordMap = nowMap.get(keyChar);

                if (wordMap != null) nowMap = (Map)wordMap;
                else {
                    newWordMap = new HashMap<String, String>();
                    newWordMap.put("isEnd","0");
                    nowMap.put(keyChar,newWordMap);
                    nowMap = newWordMap;
                }

                if (i == key.length() -1) nowMap.put("isEnd","1");
            }
        }
        mainflag = true;
    }
}
