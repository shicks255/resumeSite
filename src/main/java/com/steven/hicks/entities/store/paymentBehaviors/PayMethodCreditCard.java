package com.steven.hicks.entities.store.paymentBehaviors;

import com.steven.hicks.entities.store.OrderPaymentBehavior;

public class PayMethodCreditCard extends OrderPaymentBehavior
{
    public String pay()
    {
        System.out.println("you have paid with a credit card");

        return "You have paid with a credit card.";
    }
}
