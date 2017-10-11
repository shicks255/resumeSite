<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="cart" type="com.steven.hicks.entities.store.Cart"  scope="request"/>
<%--<jsp:useBean id="cartItems" type="java.util.List<com.steven.hicks.entities.store.CartItem>" scope="request"/>--%>
<%--<jsp:useBean id="storeItems" type="java.util.List<com.steven.hicks.entities.StoreItemGeneric>" scope="request"/>--%>
<%--<jsp:useBean id="itemPictures" type="java.util.List<com.steven.hicks.entities.store.StoreItemPicture>" scope="request"/>--%>

<jsp:include page="/_pageSections/portalNavBar.jsp"/>

<script>

    $(document).ready(function()
    {
    });

    function showPaymentBox()
    {
        var paymentType = $( '#paymentMethodSelect' ).val();
        console.log(paymentType);

//        $('label[id*="toggleExplanation"]')

        $( 'div[id*="_PaymentBox"]' ).each(function(i, obj)
        {
            console.log(this);
            $( this ).hide();
        });

        switch (paymentType)
        {
            case "CREDIT":
                $( '#credit_PaymentBox' ).show();
                break;
            case "CHECK":
                $( '#check_PaymentBox' ).show();
                break;
            case "GIFT_CARD":
                $( '#giftCard_PaymentBox' ).show();
                break;
            case "PAYPAL":
                $( '#paypal_PaymentBox' ).show();
                break;
            case "BITCOIN":
                $( '#bitcoin_PaymentBox' ).show();
                break;
        }
    }

    function doCheckout()
    {
        window.location = '${pageContext.request.contextPath}/portal?action=orderCheckout';
    }

</script>

<div class="container">


    <h3>CHECK OUT</h3>

    <button style="display:inline-block" class="btn waves-effect waves-light" id="checkoutBtn" onclick="doCheckout();">Checkout</button>

    Choose a payment method:

    <label for="paymentMethodSelect">Payment Type:</label>
    <select onchange="showPaymentBox();" class="browser-default" required="true" id="paymentMethodSelect" name="paymentMethodSelect">
        <option value="CREDIT">Credit Card</option>
        <option value="CHECK">Check</option>
        <option value="GIFT_CARD">Gift Card</option>
        <option value="PAYPAL">Paypal</option>
        <option value="BITCOIN">Bitcoin</option>
    </select>

    <div id="credit_PaymentBox" style="display:none">
        credit card
    </div>


    <div id="check_PaymentBox" style="display:none">
        check
    </div>


    <div id="giftCard_PaymentBox" style="display:none">
        gift card
    </div>


    <div id="paypal_PaymentBox" style="display:none">
        paypal
    </div>


    <div id="bitcoin_PaymentBox" style="display:none">
        bitcoin
    </div>

</div>

<jsp:include page="/_pageSections/portalFooter.jsp" />