package com.steven.hicks.entities.store.paymentBehaviors;

import com.steven.hicks.entities.store.OrderPaymentBehavior;

public class PayMethodPayPal extends OrderPaymentBehavior
{
    public String pay()
    {
        System.out.println("you have paid with paypal");

        return "You have paid with paypal";
    }
}
