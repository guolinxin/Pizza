package com.yell.pizza.service;

import com.yell.pizza.exception.DataProcessingException;
import com.yell.pizza.model.Pizza;
import com.yell.pizza.model.Recipe;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class PizzaServiceTest {

    // test data
    public static String pizzasJson;
    public static String recipesJson;

    public static PizzaService pizzaService;

    @Before
    public void before() throws IOException {

        // read data from file
        File pizzaFile = ResourceUtils.getFile("classpath:data/pizzas.json");
        File recipesFile = ResourceUtils.getFile("classpath:data/recipes.json");
        pizzasJson = new String(Files.readAllBytes(pizzaFile.toPath()));
        recipesJson = new String(Files.readAllBytes(recipesFile.toPath()));

        pizzaService = new PizzaService();
    }

    /**
     * Test Print MenuItem list to console
     *
     * @throws DataProcessingException
     */
    @Test
    public void printMenuList_Test() throws DataProcessingException {
        pizzaService.printMenu(pizzasJson, recipesJson);
    }


    @Test
    public void createMenuList_Test() throws DataProcessingException {
        pizzaService.createMenus(pizzasJson, recipesJson);
    }

    @Test(expected = DataProcessingException.class)
    public void createMenuListWithNullData_Test() throws DataProcessingException {
        pizzaService.createMenus(null, null);
    }


    @Test(expected = DataProcessingException.class)
    public void createMenuListWithNoneMappingData_Test() throws DataProcessingException {
        pizzaService.createMenus(pizzasJson, null);
    }

    @Test
    public void createPizzas() throws DataProcessingException {
        List<Pizza> pizzaList = pizzaService.createPizzas(pizzasJson);
        Assert.assertFalse(pizzaList.isEmpty());
    }

    @Test
    public void createRecipes() throws DataProcessingException {
        List<Recipe> recipeList = pizzaService.createRecipes(recipesJson);
        Assert.assertFalse(recipeList.isEmpty());
    }

    @Test(expected = JSONException.class)
    public void createPizzasWithNullPrice_Test() throws DataProcessingException {
        String pizzaWithNullPriceJson = "{ \"pizzas\": [ { \"id\": \"pepperoni\", \"title\": \"Pepperoni\", \"priceInPence\": null } ] }";
        pizzaService.createPizzas(pizzaWithNullPriceJson);
    }

    @Test(expected = JSONException.class)
    public void createRecipesWithNullIngredients_Test() throws DataProcessingException {
        String recipesWithNullIngredientsJson = "{ \"recipes\": [ { \"pizzaId\": \"pepperoni\", \"ingredients\": null } ] }";
        pizzaService.createRecipes(recipesWithNullIngredientsJson);
    }

    @Test(expected = DataProcessingException.class)
    public void createPizzaWithDuplicateId() throws DataProcessingException {
        String pizzaWithDuplicateIdJson = "{ \"pizzas\": [ { \"id\": \"pepperoni\", \"title\": \"Pepperoni\", \"priceInPence\": 899 }, { \"id\": \"pepperoni\", \"title\": \"Pepperoni\", \"priceInPence\": 899 } ] }";
        pizzaService.createPizzas(pizzaWithDuplicateIdJson);
    }

    @Test(expected = DataProcessingException.class)
    public void createRecipeWithDuplicateId() throws DataProcessingException {
        String recipeWithDuplicateIdJson = "{ \"recipes\": [ { \"pizzaId\": \"pepperoni\", \"ingredients\": [\"cheese\", \"sausage\", \"tomato\", \"pepperoni\"] }, { \"pizzaId\": \"pepperoni\", \"ingredients\": [\"cheese\", \"sausage\", \"tomato\", \"pepperoni\"] } ] }";
        pizzaService.createRecipes(recipeWithDuplicateIdJson);
    }

}
