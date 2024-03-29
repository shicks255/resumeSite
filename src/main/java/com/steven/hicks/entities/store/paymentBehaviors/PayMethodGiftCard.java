package com.steven.hicks.entities.store.paymentBehaviors;

import com.steven.hicks.entities.store.ordering.OrderPaymentBehavior;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import jakarta.servlet.http.HttpServletRequest;

public class PayMethodGiftCard implements OrderPaymentBehavior
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
