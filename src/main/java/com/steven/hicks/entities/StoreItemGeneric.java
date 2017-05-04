package com.steven.hicks.entities;

import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.entities.store.StoreItemPicture;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "ITEM_TYPE",discriminatorType = DiscriminatorType.STRING)
@DiscriminatorColumn()
public abstract class StoreItemGeneric
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int itemNumber;

    @Column
    private String itemName;
    @Column
    private String itemDescription;
    @Column
    private int itemType;
    @Column
    private String itemPrice;
    @Column
    private int pictureObjectId;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoreItemGeneric that = (StoreItemGeneric) o;

        return itemNumber == that.itemNumber;
    }

    @Override
    public int hashCode()
    {
        return itemNumber;
    }

    public static List<StoreItemGeneric> getAllItems()
    {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        org.hibernate.query.Query query = session.createQuery("from StoreItemGeneric ");
        List<StoreItemGeneric> list = query.list();

        factory.close();
        session.close();

        return list;
    }

    public static <T> List<T> getItemsOfType(String itemType)
    {
        List<T> genericItems = new ArrayList<>();

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        org.hibernate.query.Query query = session.createQuery("from StoreItemGeneric");
        genericItems = query.list();
        factory.close();
        session.close();

        return genericItems;
    }

    public static StoreItemGeneric getItem(int itemNumber)
    {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        StoreItemGeneric item = session.get(StoreItemGeneric.class, itemNumber);
        factory.close();
        session.close();

        return item;
    }

    public StoreItemPicture getItemPicture()
    {
        return StoreItemPicture.getItemPicture(getPictureObjectId());
    }

    public String getItemName()
    {
        return itemName;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public String getItemDescription()
    {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription)
    {
        this.itemDescription = itemDescription;
    }

    public int getItemNumber()
    {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber)
    {
        this.itemNumber = itemNumber;
    }

    public int getItemType()
    {
        return itemType;
    }

    public void setItemType(int itemType)
    {
        this.itemType = itemType;
    }

    public String getItemPrice()
    {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice)
    {
        this.itemPrice = itemPrice;
    }

    public int getPictureObjectId()
    {
        return pictureObjectId;
    }

    public void setPictureObjectId(int pictureObjectId)
    {
        this.pictureObjectId = pictureObjectId;
    }
}
