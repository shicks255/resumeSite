package com.steven.hicks.entities;

import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.entities.store.StoreItemPicture;
import com.steven.hicks.entities.store.StoreItemType;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.annotations.Type;
import org.hibernate.query.Query;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn()
public abstract class StoreItemGeneric
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int itemNumber;

    @Column
    private String itemName;
    @Column
    @Type(type="text")
    private String itemDescription;
    @Column
    private int itemType;
    @Column
    private BigDecimal itemPrice;

    @OneToMany(mappedBy = "storeItemGeneric", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<StoreItemPicture> itemPictures = new ArrayList<>();

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

    public Integer getFirstPictureId()
    {
        Integer id = null;
        if (itemPictures.size() > 0)
            id = itemPictures.get(0).getObjectId();

        return id;
    }

    public Integer getSmallPictureId()
    {
        Integer id = null;
        if (itemPictures.size() > 0)
        {
            List<StoreItemPicture> pictureToUse = itemPictures.stream()
                    .filter(pic -> pic.getPictureCaption().contains("_small"))
                    .collect(Collectors.toList());

            id = pictureToUse.get(0).getObjectId();
        }
        return id;
    }

    public static List<StoreItemGeneric> getAllItems()
    {
        Session session = HibernateUtil.sessionFactory.openSession();

        org.hibernate.query.Query query = session.createQuery("from StoreItemGeneric ");
        List<StoreItemGeneric> list = query.list();

        session.close();

        return list;
    }

    public static <T> List<T> getItemsOfType(String itemType)
    {
        List<T> genericItems = new ArrayList<>();

        Session session = HibernateUtil.sessionFactory.openSession();
        org.hibernate.query.Query query = session.createQuery("from StoreItemGeneric");
        genericItems = query.list();
        session.close();

        return genericItems;
    }

    public static StoreItemGeneric getItem(int itemNumber)
    {
        Session session = HibernateUtil.sessionFactory.openSession();

        StoreItemGeneric item = session.get(StoreItemGeneric.class, itemNumber);
        session.close();

        return item;
    }

    public static StoreItemGeneric getItemWithPictures(int itemNumber)
    {
        Session session = HibernateUtil.sessionFactory.openSession();

        StoreItemGeneric item = session.get(StoreItemGeneric.class, itemNumber);
        item.getItemPictures();
        session.close();

        return item;
    }

    public static StoreItemGeneric getItemByName(String name)
    {
        Session session = HibernateUtil.sessionFactory.openSession();

        String queryString = "from StoreItemGeneric where itemName=:title";
        org.hibernate.query.Query query = session.createQuery(queryString)
                .setParameter("title", name);
        List<StoreItemGeneric> items = query.list();

        items.forEach(item -> Hibernate.initialize(item.getItemPictures()));

        session.close();

        return items.get(0);
    }

    public static List<StoreItemGeneric> searchForItems(String searchTerms)
    {
        Session session = HibernateUtil.sessionFactory.openSession();

        String queryString = "from StoreItemGeneric where itemDescription like :terms or itemName like :terms";
        Query query = session.createQuery(queryString)
                .setParameter("terms", "%" + searchTerms + "%");
        List<StoreItemGeneric> items = query.list();

        //To initialize the pics
        items.forEach(item -> Hibernate.initialize(item.getItemPictures()));

        session.close();

        return items;
    }

    public StoreItemType getItemTypeObject()
    {
        return StoreItemType.getItemType(itemType);
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

    public BigDecimal getItemPrice()
    {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice)
    {
        this.itemPrice = itemPrice;
    }

    public List<StoreItemPicture> getItemPictures()
    {
        return itemPictures;
    }

    public void setItemPictures(List<StoreItemPicture> itemPictures)
    {
        this.itemPictures = itemPictures;
    }
}
