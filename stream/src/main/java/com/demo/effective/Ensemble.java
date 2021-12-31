package com.demo.effective;

/**
 * @author lz
 * @date 2021-12-06 16:22
 */
public enum Ensemble {
    SOLO(0),
    DUET(2),
    TRI0(3),
    QUAf(4),
    QUINTET(5),
    SEXTET(6),
    SEPTT(7),
    OCTET(8),
    DOUBLE_QUARTET(8),
    NONET(9),
    DECTET(10),
    TRIPLE_QUARTET(12);

    private final int numberOfMusicians;
    Ensemble(int size){
        this.numberOfMusicians = size;
    }
    public int numberOfMusicians(){
        return numberOfMusicians;
    }
}
