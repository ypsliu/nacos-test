package com.demo.effective.designPatterns.chain;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/7
 * Time: 15:50
 * Description: 责任链模式
 */
public interface Processor {

    Processor getNextProcessor();

    void process(String param);
}
