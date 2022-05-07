package com.demo.effective.designPatterns.chain;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/7
 * Time: 15:53
 * Description: No Description
 */
public class ProcessorImpl2 extends AbstractProcessor{
    public ProcessorImpl2(Processor processor) {
        super(processor);
    }

    @Override
    public void process(String param) {
        System.out.println("processor 2 is processing:" + param);
        if (getNextProcessor() != null) {
            getNextProcessor().process(param);
        }
    }
}
