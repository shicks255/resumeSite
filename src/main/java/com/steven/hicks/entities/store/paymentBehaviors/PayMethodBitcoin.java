package com.steven.hicks.entities.store.paymentBehaviors;

import com.steven.hicks.entities.store.ordering.OrderPaymentBehavior;

import javax.persistence.*;
import jakarta.servlet.http.HttpServletRequest;

@Entity
@DiscriminatorValue("BitCoin")
public class PayMethodBitcoin implements OrderPaymentBehavior
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
