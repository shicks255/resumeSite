package com.steven.hicks.entities.store;

import com.steven.hicks.Utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class StoreItemType
{
    @Id
    public int itemTypeCode;

    @Column
    public String itemType;


    public static List<StoreItemType> getItemTypes()
    {
        Session session = HibernateUtil.sessionFactory.openSession();

        Query query = session.createQuery("from StoreItemType order by itemTypeCode");
        List<StoreItemType> list = query.list();

        session.close();

        return list;
    }

    public static StoreItemType getItemType(int itemTypeCode)
    {
        Session session = HibernateUtil.sessionFactory.openSession();

        StoreItemType itemType = session.get(StoreItemType.class, itemTypeCode);
        session.close();

        return itemType;
    }

    public static StoreItemType getItemTypeByName(String itemTypeName)
    {
        Session session = HibernateUtil.sessionFactory.openSession();

        Query query = session.createQuery("from StoreItemType where itemType = \'" + itemTypeName + "\'");
        List<StoreItemType> items = query.list();

        session.close();

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
