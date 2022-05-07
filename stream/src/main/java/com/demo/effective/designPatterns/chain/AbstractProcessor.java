package com.demo.effective.designPatterns.chain;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/7
 * Time: 15:50
 * Description: No Description
 */
public abstract  class AbstractProcessor implements Processor{
    private Processor next;

    public AbstractProcessor(Processor processor) {
        this.next = processor;
    }

    @Override
    public Processor getNextProcessor() {
        return next;
    }

    @Override
    public abstract void process(String param);
}
