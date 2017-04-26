package com.steven.hicks.entities.StoreItems;

import com.steven.hicks.Utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.*;
import java.util.List;

@Entity
public class StoreItemType
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int itemTypeCode;

    @Column
    public String itemType;


    public static List<StoreItemType> getItemTypes()
    {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        Query query = session.createQuery("from StoreItemType order by itemTypeCode");
        List<StoreItemType> list = query.list();

        return list;
    }

    public static StoreItemType getItemType(int itemTypeCode)
    {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        StoreItemType itemType = session.get(StoreItemType.class, itemTypeCode);
        session.close();
        factory.close();
        return itemType;
    }

    public static StoreItemType getItemTypeByName(String itemTypeName)
    {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        Query query = session.createQuery("from StoreItemType where itemType = \'" + itemTypeName + "\'");
        List<StoreItemType> items = query.list();
        if (items.size() > 0)
            return items.get(0);
        else
            return null;
    }


    public int getItemTypeCode()
    {
        return itemTypeCode;
    }

    public void setItemTypeCode(int itemTypeCode)
    {
        this.itemTypeCode = itemTypeCode;
    }

    public String getItemType()
    {
        return itemType;
    }

    public void setItemType(String itemType)
    {
        this.itemType = itemType;
    }
}
