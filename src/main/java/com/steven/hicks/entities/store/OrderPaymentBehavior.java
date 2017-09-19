package com.steven.hicks.entities.store;

import javax.persistence.*;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn()
public abstract class OrderPaymentBehavior
{

    @Column
    private String paymentType;

    public abstract String pay();


}
