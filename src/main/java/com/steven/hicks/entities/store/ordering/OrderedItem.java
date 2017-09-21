package com.steven.hicks.entities.store.ordering;

import javax.persistence.*;

@Entity
public class OrderedItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderedItemObjectId;

    @ManyToOne
    @JoinColumn(name = "orderObjectId")
    private StoreOrder order;

    @Column
    public int itemNumber;

    @Column
    private int quantity;

    @Override
    public String toString()
    {
        return "OrderedItem{" +
                "orderObjectId=" + order.getObjectId() +
                ", item=" + itemNumber +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderedItem that = (OrderedItem) o;

        if (orderedItemObjectId != that.orderedItemObjectId) return false;
        if (itemNumber != that.itemNumber) return false;
        if (quantity != that.quantity) return false;
        return order.equals(that.order);
    }

    @Override
    public int hashCode()
    {
        int result = orderedItemObjectId;
        result = 31 * result + order.hashCode();
        result = 31 * result + itemNumber;
        result = 31 * result + quantity;
        return result;
    }

    public int getOrderedItemObjectId()
    {
        return orderedItemObjectId;
    }

    public void setOrderedItemObjectId(int orderedItemObjectId)
    {
        this.orderedItemObjectId = orderedItemObjectId;
    }

    public StoreOrder getOrder()
    {
        return order;
    }

    public void setOrder(StoreOrder order)
    {
        this.order = order;
    }

    public int getItemNumber()
    {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber)
    {
        this.itemNumber = itemNumber;
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
