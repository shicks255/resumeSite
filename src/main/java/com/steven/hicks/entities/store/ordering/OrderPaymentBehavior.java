package com.steven.hicks.entities.store.ordering;


import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embeddable;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.servlet.http.HttpServletRequest;

@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn()
@Embeddable
public interface OrderPaymentBehavior
{
    void pay();
}
