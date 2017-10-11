package com.steven.hicks.entities.store.ordering;

import javax.persistence.*;

//@Entity
public class OrderPayment
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long objectId;

    @OneToOne (mappedBy = "orderPayment")
    private StoreOrder storeOrder;

    @Column
    @Embedded
    private OrderPaymentBehaviorNew paymentBehavior;

    @Column
    private String paymentBehaviorType = "";

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderPayment that = (OrderPayment) o;

        return objectId == that.objectId;
    }

    @Override
    public int hashCode()
    {
        return (int) (objectId ^ (objectId >>> 32));
    }

    @Override
    public String toString()
    {
        return "OrderPayment{" +
                "objectId=" + objectId +
                ", paymentBehavior=" + paymentBehavior +
                '}';
    }

    public StoreOrder getStoreOrder()
    {
        return storeOrder;
    }

    public void setStoreOrder(StoreOrder storeOrder)
    {
        this.storeOrder = storeOrder;
    }

    public long getObjectId()
    {
        return objectId;
    }

    public void setObjectId(long objectId)
    {
        this.objectId = objectId;
    }

    public OrderPaymentBehaviorNew getPaymentBehavior()
    {
        return paymentBehavior;
    }

    public void setPaymentBehavior(OrderPaymentBehaviorNew paymentBehavior)
    {
        this.paymentBehavior = paymentBehavior;
    }

    public String getPaymentBehaviorType()
    {
        return paymentBehaviorType;
    }

    public void setPaymentBehaviorType(String paymentBehaviorType)
    {
        this.paymentBehaviorType = paymentBehaviorType;
    }
}
