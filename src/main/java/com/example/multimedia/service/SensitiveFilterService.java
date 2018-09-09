package com.example.multimedia.service;

public interface SensitiveFilterService {
    void addSensitiveWord(String word);
    int checkSensitiveWord(String txt,int beginIndex);
    String turnWord(String sensitiveStr);
}
