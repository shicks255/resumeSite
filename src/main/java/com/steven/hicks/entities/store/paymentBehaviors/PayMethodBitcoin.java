package com.steven.hicks.entities.store.paymentBehaviors;

import com.steven.hicks.entities.store.ordering.OrderPaymentBehavior;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BitCoin")
public class PayMethodBitcoin extends OrderPaymentBehavior
{
    @Column
    public String bitcointWalletAddress = "";


    public String pay()
    {
        System.out.println("you have paid with BitCoin");

        return "You have paid with BitCoin";
    }
}
