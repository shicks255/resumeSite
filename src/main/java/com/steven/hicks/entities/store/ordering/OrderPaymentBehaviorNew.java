package com.steven.hicks.entities.store.ordering;


import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embeddable;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn()
@Embeddable
public interface OrderPaymentBehaviorNew
{
    void pay();
}
