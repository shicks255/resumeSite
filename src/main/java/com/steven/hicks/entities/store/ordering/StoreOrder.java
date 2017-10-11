package com.steven.hicks.entities.store.ordering;

import com.steven.hicks.Utilities.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class StoreOrder
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int objectId;

    @Column
    private String userName = "";

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderedItem> itemsFromOrder = new ArrayList<>();

    @Embedded
    private OrderPaymentBehaviorNew orderPaymentBehavior;

    @Column
    private String orderPaymentType = "";

    @Column
    private LocalDateTime orderTimeStamp;

//    --------Constructors
    public StoreOrder() {}

    public StoreOrder(String userName)
    {
        this.userName = userName;
    }

    @Override
    public String toString()
    {
        return "StoreOrder{" +
                "objectId=" + objectId +
                ", userName='" + userName + '\'' +
                ", orderTimeStamp=" + orderTimeStamp +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoreOrder order = (StoreOrder) o;

        return objectId == order.objectId;
    }

    @Override
    public int hashCode()
    {
        return objectId;
    }

    public static StoreOrder getOrderById(int objectId)
    {
        Session session = HibernateUtil.sessionFactory.openSession();
        StoreOrder order = session.get(StoreOrder.class, objectId);
        return order;
    }


//    ---------Getters & Setters
    public int getObjectId()
    {
        return objectId;
    }

    public void setObjectId(int objectId)
    {
        this.objectId = objectId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public List<OrderedItem> getItemsFromOrder()
    {
        return itemsFromOrder;
    }

    public void setItemsFromOrder(List<OrderedItem> itemsFromOrder)
    {
        this.itemsFromOrder = itemsFromOrder;
    }

    public LocalDateTime getOrderTimeStamp()
    {
        return orderTimeStamp;
    }

    public void setOrderTimeStamp(LocalDateTime orderTimeStamp)
    {
        this.orderTimeStamp = orderTimeStamp;
    }

    public OrderPaymentBehaviorNew getOrderPaymentBehavior()
    {
        return orderPaymentBehavior;
    }

    public void setOrderPaymentBehavior(OrderPaymentBehaviorNew orderPaymentBehavior)
    {
        this.orderPaymentBehavior = orderPaymentBehavior;
    }

    public String getOrderPaymentType()
    {
        return orderPaymentType;
    }

    public void setOrderPaymentType(String orderPaymentType)
    {
        this.orderPaymentType = orderPaymentType;
    }
}
