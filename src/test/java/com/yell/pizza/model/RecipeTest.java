package com.yell.pizza.model;

import com.yell.pizza.exception.DataProcessingException;
import org.junit.Assert;
import org.junit.Test;

public class RecipeTest {

    @Test
    public void createRecipe_Test() throws DataProcessingException {
        String[] pepperoniIngredients = {"cheese", "sausage", "tomato", "pepperoni"};
        Recipe recipe = new Recipe("pepperoni", pepperoniIngredients);
        Assert.assertEquals("pepperoni", recipe.getPizzaId());
    }

}
