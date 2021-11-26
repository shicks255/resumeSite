package com.steven.hicks.entities.store.paymentBehaviors;

import com.steven.hicks.Utilities.CommonUtils;
import com.steven.hicks.entities.store.ordering.OrderPaymentBehavior;
import com.steven.hicks.entities.store.ordering.StoreOrder;

import javax.persistence.*;
import jakarta.servlet.http.HttpServletRequest;

@Entity
@DiscriminatorValue("CreditCard")
public class PayMethodCreditCard implements OrderPaymentBehavior
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int objectId;

    @OneToOne
    @JoinColumn
    private StoreOrder order;

    @Column
    private String creditCardNumber = "";

    @Column
    private String expirationDate = "";

    @Column
    private int secCode;

    @Column
    private String cardHoldersName = "";

    @Column
    private String cardTypeCode;

    public void pay()
    {
        System.out.println("you have paid with a credit card");
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

    public PayMethodCreditCard()
    {}

//    --------*BUILD*--------
    public PayMethodCreditCard(HttpServletRequest request)
    {
        String creditCardNumber = CommonUtils.getString(request.getParameter("creditCardNumber"));
        int securityCode = CommonUtils.getInteger(request.getParameter("securityCode"));
        String date = CommonUtils.getString(request.getParameter("expirationDate"));
        String cardHoldersName = CommonUtils.getString(request.getParameter("cardHoldersName"));
        String cardTypeCode = CommonUtils.getString(request.getParameter("cardTypeCode"));

        this.creditCardNumber = creditCardNumber;
        this.secCode = securityCode;
        this.expirationDate = date;
        this.cardHoldersName = cardHoldersName;
        this.cardTypeCode = cardTypeCode;
    }


//    --------Getters & Setters
    public int getObjectId()
    {
        return objectId;
    }

    public void setObjectId(int objectId)
    {
        this.objectId = objectId;
    }

    public StoreOrder getOrder()
    {
        return order;
    }

    public void setOrder(StoreOrder order)
    {
        this.order = order;
    }

    public String getCreditCardNumber()
    {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber)
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

    public String getCardTypeCode()
    {
        return cardTypeCode;
    }

    public void setCardTypeCode(String cardTypeCode)
    {
        this.cardTypeCode = cardTypeCode;
    }
}
