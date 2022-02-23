package com.demo.jmh;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/2/22
 * Time: 13:51
 * Description: No Description
 */
public class JMHStarter{

    public static void main(String[]args) throws  RunnerException {
        Options opt = new OptionsBuilder()
        .include(LinkedListIterationBenchMark.class.getSimpleName())
        .forks(1)
        .warmupIterations(2)
        .measurementIterations(2)
        .output("E:/Benchmark.log")
        .build();
        new Runner(opt).run();
    }

}
