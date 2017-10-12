package com.steven.hicks.entities.store.paymentBehaviors;

import com.steven.hicks.entities.store.ordering.OrderPaymentBehaviorNew;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;

public class PayMethodGiftCard implements OrderPaymentBehaviorNew
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int objectId;

    @Column
    private String giftCardNumber = "";

    public void pay()
    {
        System.out.println("you have paid with a gift card");
    }

    public PayMethodGiftCard() {}

    public PayMethodGiftCard(HttpServletRequest request)
    {

    }

}
