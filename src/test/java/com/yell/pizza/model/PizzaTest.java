package com.yell.pizza.model;

import com.yell.pizza.exception.DataProcessingException;
import org.junit.Assert;
import org.junit.Test;

public class PizzaTest {

    @Test
    public void createPizza_Test() throws DataProcessingException {
        Pizza pizza = new Pizza("pepperoni", "Pepperoni", 899);
        Assert.assertEquals("Pepperoni",pizza.getTitle());
    }

}
