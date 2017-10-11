package com.steven.hicks.entities.store.ordering;

import javax.persistence.*;

@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn()
@Embeddable
public abstract class OrderPaymentBehavior
{
    @Column
    public String paymentType;

    public abstract String pay();

    //----------Getters & Setters

    public String getPaymentType()
    {
        return paymentType;
    }

    public void setPaymentType(String paymentType)
    {
        this.paymentType = paymentType;
    }
}
