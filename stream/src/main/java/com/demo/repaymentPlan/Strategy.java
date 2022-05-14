package com.demo.repaymentPlan;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author lzy
 * @version 1.0
 * Date: 2022/5/15
 * Time: 0:18
 * Created with IntelliJ IDEA
 * Description: No Description
 */
public class Strategy {
    public static void main(String[] args) {
        CreditCard creditCard1 = CreditCard.builder()
                .cardId("123")
                .totalAmount(new BigDecimal("100000.00"))
                .usedAmount(new BigDecimal("56000.00"))
                .repaymentDate(LocalDate.of(2022,6,10))
                .build();
    }
}
