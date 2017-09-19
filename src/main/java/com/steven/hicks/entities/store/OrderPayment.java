package com.steven.hicks.entities.store;

import javax.persistence.*;

@Entity
public class OrderPayment
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int objectId;

    @Embedded
    public OrderPaymentBehavior m_orderPaymentBehavior;




}
