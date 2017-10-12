package com.steven.hicks.entities.store.paymentBehaviors;

import com.steven.hicks.entities.store.ordering.OrderPaymentBehavior;
import com.steven.hicks.entities.store.ordering.OrderPaymentBehaviorNew;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;

@Entity
@DiscriminatorValue("Paypal")
public class PayMethodPayPal implements OrderPaymentBehaviorNew
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
