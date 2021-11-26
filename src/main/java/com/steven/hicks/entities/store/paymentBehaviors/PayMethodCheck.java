package com.steven.hicks.entities.store.paymentBehaviors;

import com.steven.hicks.entities.store.ordering.OrderPaymentBehavior;

import javax.persistence.*;
import jakarta.servlet.http.HttpServletRequest;

@Entity
@DiscriminatorValue("check")
public class PayMethodCheck implements OrderPaymentBehavior
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int objectId;


    @Column
    public String routingNumber;


    public void pay()
    {
        System.out.println("you have paid with a check");
    }

    public PayMethodCheck(){}
    public PayMethodCheck(HttpServletRequest request)
    {

    }
}
