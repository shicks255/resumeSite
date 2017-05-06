package com.steven.hicks.entities.store;

import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.entities.StoreItemGeneric;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;

@Entity
public class CartItem
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int objectId;

    @Column
    private int cartObjectId;

    @Column
    private int itemObjectIt;

    @Column
    private int quantity;

//    --------Basics


    @Override
    public String toString()
    {
        return "CartItem{" +
                "objectId=" + objectId +
                ", cartObjectId=" + cartObjectId +
                ", itemObjectIt=" + itemObjectIt +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItem cartItem = (CartItem) o;

        return objectId == cartItem.objectId;
    }

    @Override
    public int hashCode()
    {
        return objectId;
    }

//    --------Data
    public StoreItemGeneric getStoreItem()
    {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        StoreItemGeneric item = session.get(StoreItemGeneric.class, getItemObjectIt());

        session.close();
        factory.close();

        return item;
    }

    //    --------Getters & Stters
    public int getObjectId()
    {
        return objectId;
    }

    public void setObjectId(int objectId)
    {
        this.objectId = objectId;
    }

    public int getCartObjectId()
    {
        return cartObjectId;
    }

    public void setCartObjectId(int cartObjectId)
    {
        this.cartObjectId = cartObjectId;
    }

    public int getItemObjectIt()
    {
        return itemObjectIt;
    }

    public void setItemObjectIt(int itemObjectIt)
    {
        this.itemObjectIt = itemObjectIt;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
