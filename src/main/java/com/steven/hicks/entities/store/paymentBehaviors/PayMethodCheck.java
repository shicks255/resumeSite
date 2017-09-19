package com.steven.hicks.entities.store.paymentBehaviors;

import com.steven.hicks.entities.store.OrderPaymentBehavior;

public class PayMethodCheck extends OrderPaymentBehavior
{

    public String pay()
    {
        System.out.println("you have paid with a check");

        return "You have paid with a check";
    }
}
