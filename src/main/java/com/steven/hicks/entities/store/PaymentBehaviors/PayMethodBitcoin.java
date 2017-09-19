package com.steven.hicks.entities.store.PaymentBehaviors;

import com.steven.hicks.entities.store.OrderPaymentBehavior;

public class PayMethodBitcoin extends OrderPaymentBehavior
{
    public String pay()
    {
        System.out.println("you have paid with BitCoin");

        return "You have paid with BitCoin";
    }
}
