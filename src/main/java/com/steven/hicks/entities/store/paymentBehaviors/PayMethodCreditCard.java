package com.steven.hicks.entities.store.paymentBehaviors;

import com.steven.hicks.entities.store.ordering.OrderPaymentBehavior;

import javax.persistence.*;

@Entity
@DiscriminatorValue("CreditCard")
public class PayMethodCreditCard extends OrderPaymentBehavior
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int objectId;

    @Column
    private long creditCardNumber;

    @Column
    private String expirationDate = "";

    @Column
    private int secCode;

    @Column
    private String cardHoldersName = "";

    @Column
    private int cardTypeCode;

    public String pay()
    {
        System.out.println("you have paid with a credit card");

        return "You have paid with a credit card.";
    }


    @Override
    public String toString()
    {
        return "PayMethodCreditCard{" +
                "creditCardNumber=" + creditCardNumber +
                ", expirationDate='" + expirationDate + '\'' +
                ", secCode=" + secCode +
                ", cardHoldersName='" + cardHoldersName + '\'' +
                ", cardTypeCode=" + cardTypeCode +
                '}';
    }

    public int getObjectId()
    {
        return objectId;
    }

    public void setObjectId(int objectId)
    {
        this.objectId = objectId;
    }

    public long getCreditCardNumber()
    {
        return creditCardNumber;
    }

    public void setCreditCardNumber(long creditCardNumber)
    {
        this.creditCardNumber = creditCardNumber;
    }

    public String getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate)
    {
        this.expirationDate = expirationDate;
    }

    public int getSecCode()
    {
        return secCode;
    }

    public void setSecCode(int secCode)
    {
        this.secCode = secCode;
    }

    public String getCardHoldersName()
    {
        return cardHoldersName;
    }

    public void setCardHoldersName(String cardHoldersName)
    {
        this.cardHoldersName = cardHoldersName;
    }

    public int getCardTypeCode()
    {
        return cardTypeCode;
    }

    public void setCardTypeCode(int cardTypeCode)
    {
        this.cardTypeCode = cardTypeCode;
    }
}
