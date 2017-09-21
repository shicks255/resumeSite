package com.steven.hicks.entities.store.paymentBehaviors;

import com.steven.hicks.entities.store.ordering.OrderPaymentBehavior;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Paypal")
public class PayMethodPayPal extends OrderPaymentBehavior
{
    @Column
    public String paypalUserName = "";

    @Column
    public String paypalPassword = "";

    public String pay()
    {
        System.out.println("you have paid with paypal");

        return "You have paid with paypal";
    }
}
