package com.steven.hicks.entities.store.paymentBehaviors;

import com.steven.hicks.entities.store.ordering.OrderPaymentBehavior;

import javax.persistence.*;
import jakarta.servlet.http.HttpServletRequest;

@Entity
@DiscriminatorValue("Paypal")
public class PayMethodPayPal implements OrderPaymentBehavior
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int objectId;

    @Column
    public String paypalUserName = "";

    @Column
    public String paypalPassword = "";

    public void pay()
    {
        System.out.println("you have paid with paypal");
    }

    public PayMethodPayPal() {}

    public PayMethodPayPal(HttpServletRequest request)
    {

    }

}
