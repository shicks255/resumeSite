package com.steven.hicks.entities.store.ordering;

import com.steven.hicks.entities.store.paymentBehaviors.*;

import jakarta.servlet.http.HttpServletRequest;

public class PaymentMethodFactory
{
    public static OrderPaymentBehavior buildAndReturnPaymentMethod(HttpServletRequest request)
    {
        OrderPaymentBehavior paymentBehavior = null;
        String paymentType = request.getParameter("paymentType");

        if (paymentType.length() > 0)
        {
            if (paymentType.equalsIgnoreCase("CREDIT"))
            {
                paymentBehavior = new PayMethodCreditCard(request);
            }
            if (paymentType.equalsIgnoreCase("CHECK"))
            {
                paymentBehavior = new PayMethodCheck(request);
            }
            if (paymentType.equalsIgnoreCase("GIFT_CARD"))
            {
                paymentBehavior = new PayMethodGiftCard(request);
            }
            if (paymentType.equalsIgnoreCase("PAYPAL"))
            {
                paymentBehavior = new PayMethodPayPal(request);
            }
            if (paymentType.equalsIgnoreCase("BITCOIN"))
            {
                paymentBehavior = new PayMethodBitcoin(request);
            }
        }

        return paymentBehavior;
    }
}
