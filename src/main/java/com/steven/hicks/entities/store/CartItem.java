package com.steven.hicks.entities.store;

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
}
