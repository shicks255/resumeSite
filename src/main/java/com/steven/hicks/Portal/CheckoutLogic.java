package com.steven.hicks.Portal;

import com.steven.hicks.entities.store.Cart;
import com.steven.hicks.entities.store.Order;

public class CheckoutLogic
{

    public static void checkout(Cart cart)
    {
        Order order = new Order();

        order.setItemsFromOrder(cart.getItemsInCart());

    }

}
