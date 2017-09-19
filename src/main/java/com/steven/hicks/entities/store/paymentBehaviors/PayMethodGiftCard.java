package com.steven.hicks.entities.store.paymentBehaviors;

import com.steven.hicks.entities.store.OrderPaymentBehavior;

public class PayMethodGiftCard extends OrderPaymentBehavior
{

    public String pay()
    {
        System.out.println("you have paid with a gift card");

        return "You have paid with a gift card";
    }

}
