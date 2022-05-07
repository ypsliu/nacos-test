package com.demo.effective.designPatterns.chain;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/7
 * Time: 15:52
 * Description: No Description
 */
public class ProcessorImpl1 extends AbstractProcessor{

    public ProcessorImpl1(Processor processor) {
        super(processor);
    }

    @Override
    public void process(String param) {
        System.out.println("processor 1 is processing:" + param);
        if (getNextProcessor() != null) {
            getNextProcessor().process(param);
        }
    }
}
