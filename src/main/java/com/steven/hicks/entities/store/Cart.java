package com.steven.hicks.entities.store;

import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.entities.StoreItemGeneric;
import org.hibernate.Session;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        indexes =
                {
                        @Index(name = "USER_NAME_OF_CART_INDX", columnList = "userNameOfCart", unique = true)
                })
public class Cart
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int objectId;

    @Column
    private String userNameOfCart;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    private List<CartItem> itemsInCart = new ArrayList<>();

//    -----Basics


    @Override
    public String toString()
    {
        return "Cart{" +
                "objectId=" + objectId +
                ", userNameOfCart='" + userNameOfCart + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        return objectId == cart.objectId;
    }

    @Override
    public int hashCode()
    {
        return objectId;
    }


//    -----Data Access

    public static Cart getCartByUser(String userName)
    {
        Session session = HibernateUtil.sessionFactory.openSession();

        org.hibernate.query.Query query = session.createQuery("from Cart where userNameOfCart = \'" + userName + "\'");
        List<Cart> carts = query.list();

        session.close();

        Cart userCart = null;

        if (carts.size() > 0)
            userCart = carts.get(0);

        return userCart;
    }

    public static Cart getCartByObjectId(int cartObjectId)
    {
        Session session = HibernateUtil.sessionFactory.openSession();

        org.hibernate.query.Query query = session.createQuery("from Cart where objectId = " + cartObjectId);
        List<Cart> carts = query.list();

        session.close();

        Cart cart = null;

        if (carts.size() > 0)
            cart = carts.get(0);

        return cart;
    }


    public BigDecimal getSubTotal()
    {
        List<CartItem> items = itemsInCart;
        BigDecimal subTotal = new BigDecimal("0.0");

        Session session = HibernateUtil.sessionFactory.openSession();

        for (CartItem item : items)
        {
            StoreItemGeneric storeItem = session.get(StoreItemGeneric.class, item.getItemObjectIt());
            subTotal = subTotal.add(storeItem.getItemPrice().multiply(new BigDecimal("" + item.getQuantity())));
        }

        session.close();

        return subTotal;
    }

    public BigDecimal getTotal()
    {
        BigDecimal subTotal = getSubTotal();
        BigDecimal total = subTotal.multiply(new BigDecimal("1.07"));

        total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
        return total;
    }

    public boolean isItemAlreadyInCartByItemNumber(int itemNumber)
    {
        List<CartItem> items = itemsInCart;

        for (CartItem item : items)
        {
            if (item.getStoreItem().getItemNumber() == itemNumber)
                return true;
        }

        return false;
    }

    public CartItem getItemFromCartByItemNumber(int itemNumber)
    {
        List<CartItem> items = itemsInCart;

        for (CartItem item : items)
        {
            if (item.getItemObjectIt() == itemNumber)
                return item;
        }

        return null;
    }

//    -----Getters & Setters


    public int getObjectId()
    {
        return objectId;
    }

    public void setObjectId(int objectId)
    {
        this.objectId = objectId;
    }

    public String getUserNameOfCart()
    {
        return userNameOfCart;
    }

    public void setUserNameOfCart(String userNameOfCart)
    {
        this.userNameOfCart = userNameOfCart;
    }

    public List<CartItem> getItemsInCart()
    {
        return itemsInCart;
    }

    public void setItemsInCart(List<CartItem> itemsInCart)
    {
        this.itemsInCart = itemsInCart;
    }
}
