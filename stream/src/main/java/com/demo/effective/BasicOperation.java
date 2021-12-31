package com.demo.effective;

/**
 * @author lz
 * @date 2021-12-06 16:31
 */
public enum BasicOperation implements Operation{
    PLUS("+"){
        public double apply(double x,double y){
            return x+y;
        }
    },
    MINUS("-"){
        public double apply(double x,double y){
            return x-y;
        }
    },
    DIVIDE("/"){
        public double apply(double x,double y){
            return x/y;
        }
    },
    TIMES("*"){
        public double apply(double x,double y){
            return x*y;
        }
    };
    private final String symbol;
    BasicOperation(String symbol){
        this.symbol = symbol;
    }

}
