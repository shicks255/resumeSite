package com.steven.hicks.entities.store.ordering;

import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.entities.store.paymentBehaviors.*;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;

public class PaymentLogic
{
    public static PayMethodCreditCard makeCreditCardPayment(HttpServletRequest request)
    {
        PayMethodCreditCard creditCardPayment = new PayMethodCreditCard();

        String creditCardNumber = request.getParameter("creditCardNumber");
        String expirationDate = request.getParameter("expirationDate");
        int securityCode = Integer.valueOf(request.getParameter("securityCode"));
        String cardHolderName = request.getParameter("cardholdersName");
        int cardTypeCode = Integer.valueOf(request.getParameter("cardTypeCode"));

        creditCardPayment.setCreditCardNumber(creditCardNumber);
        creditCardPayment.setExpirationDate(expirationDate);
        creditCardPayment.setSecCode(securityCode);
        creditCardPayment.setCardHoldersName(cardHolderName);
        creditCardPayment.setCardTypeCode(cardTypeCode);

        Session session = HibernateUtil.sessionFactory.openSession();
        session.beginTransaction();
        session.save(creditCardPayment);
        session.getTransaction().commit();
        session.close();

        return creditCardPayment;
    }

    public static PayMethodCheck makeCheckPayment(HttpServletRequest request)
    {
        PayMethodCheck checkPayment = new PayMethodCheck();
        return checkPayment;
    }

    public static PayMethodGiftCard makeGiftCardPayment(HttpServletRequest request)
    {
        PayMethodGiftCard giftCardPayment = new PayMethodGiftCard();
        return giftCardPayment;
    }

    public static PayMethodPayPal makePayPalPayment(HttpServletRequest request)
    {
        PayMethodPayPal paypalPayment = new PayMethodPayPal();
        return paypalPayment;
    }

    public static PayMethodBitcoin makeBitCoinPayment(HttpServletRequest request)
    {
        PayMethodBitcoin bitcoinPayment = new PayMethodBitcoin();
        return bitcoinPayment;
    }
}
