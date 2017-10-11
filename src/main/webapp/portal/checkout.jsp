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
        showPaymentBox();
        $('.datepicker').pickadate({
            selectMonths: true, // Creates a dropdown to control month
            selectYears: 15, // Creates a dropdown of 15 years to control year,
            today: 'Today',
            clear: 'Clear',
            close: 'Ok',
            closeOnSelect: false // Close upon selecting a date,
        });
    });

    function showPaymentBox()
    {
        var paymentType = $( '#paymentMethodSelect' ).val();
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
        var paymentType = $( '#paymentMethodSelect' ).val();
        window.location = '${pageContext.request.contextPath}/portal?action=orderCheckout&paymentType=' + paymentType;
    }

</script>

<div class="container" style="borer: 1px solid black">


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

    <div id="credit_PaymentBox" style="padding : 5px; margin: 0 auto; display:none; width: 90%;border: 1px solid black">
        <div class="row">
            <form class="col s12">
                <div class="row">
                    <div class="input-field col s6">
                        <input placeholder="Dashes are optional" id="creditCardNumber" type="text" class="validate">
                        <label for="creditCardNumber">Credit Card #</label>
                    </div>
                    <div class="input-field col s6">
                        <input placeholder="usually 3 or 4 digit number" id="securityCode" type="text" class="validate">
                        <label for="securityCode">Security Code</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col m4 s4 l4">
                        <input type="text" class="datepicker" id="expirationDate">
                        <label for="expirationDate">Expiration Date</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        <input placeholder="Name as it appears on credit card" type="text" id="cardHoldersName">
                        <label for="cardHoldersName">Cardholder's Name:</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col m8 s8 l8">
                        <label for="cardTypeCode">Card Type:</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col m4 s4 l4">
                        <select class="browser-default" required id="cardTypeCode" name="cardTypeCode">
                            <option value="VISA">Visa</option>
                        </select>
                    </div>
                </div>
            </form>
        </div>
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