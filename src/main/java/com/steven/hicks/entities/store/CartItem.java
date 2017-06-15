package com.steven.hicks.entities.store;

import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.entities.StoreItemGeneric;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
public class CartItem
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int objectId;

    @Column
    private int itemObjectIt;

    @Column
    private int quantity;

    @ManyToOne
    @JoinColumn(name="cartObjectId")
    private Cart cart;

    @Override
    public String toString()
    {
        return "CartItem{" +
                "objectId=" + objectId +
                ", cartObjectId=" + cart.getObjectId() +
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

    public static CartItem getCartItem(int objectId)
    {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        CartItem cartItem = session.get(CartItem.class, objectId);

        session.close();
        factory.close();

        return cartItem;
    }

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

    public Cart getCart()
    {
        return cart;
    }

    public void setCart(Cart cart)
    {
        this.cart = cart;
    }
}
