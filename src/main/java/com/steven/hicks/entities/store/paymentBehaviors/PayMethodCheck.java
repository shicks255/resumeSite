package com.steven.hicks.entities.store.paymentBehaviors;

import com.steven.hicks.entities.store.ordering.OrderPaymentBehavior;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("check")
public class PayMethodCheck extends OrderPaymentBehavior
{

    @Column
    public String routingNumber;


    public String pay()
    {
        System.out.println("you have paid with a check");

        return "You have paid with a check";
    }
}
