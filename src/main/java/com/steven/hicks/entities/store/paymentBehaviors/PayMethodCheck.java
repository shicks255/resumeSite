package com.steven.hicks.entities.store.paymentBehaviors;

import com.steven.hicks.entities.store.ordering.OrderPaymentBehavior;
import com.steven.hicks.entities.store.ordering.OrderPaymentBehaviorNew;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;

@Entity
@DiscriminatorValue("check")
public class PayMethodCheck implements OrderPaymentBehaviorNew
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
