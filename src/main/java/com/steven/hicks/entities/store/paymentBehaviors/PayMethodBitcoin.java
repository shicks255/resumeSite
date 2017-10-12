package com.steven.hicks.entities.store.paymentBehaviors;

import com.steven.hicks.entities.store.ordering.OrderPaymentBehaviorNew;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;

@Entity
@DiscriminatorValue("BitCoin")
public class PayMethodBitcoin implements OrderPaymentBehaviorNew
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int objectId;

    @Column
    public String bitcointWalletAddress = "";


    public void pay()
    {
        System.out.println("you have paid with BitCoin");
    }

    public PayMethodBitcoin()
    {}

    public PayMethodBitcoin(HttpServletRequest request)
    {

    }
}
