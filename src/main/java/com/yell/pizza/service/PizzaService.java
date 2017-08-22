package com.yell.pizza.service;

import com.yell.pizza.exception.DataProcessingException;
import com.yell.pizza.model.MenuItem;
import com.yell.pizza.model.Pizza;
import com.yell.pizza.model.Recipe;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class representing pizza service.
 */
public class PizzaService {

    private static final String RECIPES = "recipes";
    private static final String PIZZAS = "pizzas";
    private static final String INVALID_DATA = "Invalid data";
    private static final String INVALID_PIZZA_DATA = "Invalid pizza data";
    private static final String INVALID_RECIPE_DATA = "Invalid recipe data";

    /**
     * Print list of MenuItems to console
     *
     * @param pizzaJson
     * @param recipeJson
     * @throws DataProcessingException
     */
    public void printMenu(String pizzaJson, String recipeJson) throws DataProcessingException {
        createMenus(pizzaJson, recipeJson).forEach(System.out::println);
    }

    /**
     * Create list of MenuItems from pizzas and recipes json string
     *
     * @param pizzas
     * @param recipes
     * @return
     * @throws DataProcessingException
     */
    public List<MenuItem> createMenus(String pizzas, String recipes) throws DataProcessingException {

        if (StringUtils.isBlank(pizzas) || StringUtils.isBlank(recipes)) {
            throw new DataProcessingException(INVALID_DATA);
        }

        List<Pizza> pizzaList = createPizzas(pizzas);
        List<Recipe> recipeList = createRecipes(recipes);
        List<MenuItem> menuItemList = new ArrayList<>();

        pizzaList.forEach(pizza -> {
            Optional<Recipe> recipe = recipeList.stream()
                    .filter(rec -> rec.getPizzaId().equals(pizza.getId()))
                    .findFirst();

            if (recipe.isPresent()) {
                menuItemList.add(new MenuItem(pizza, recipe.get()));
            }
        });

        return menuItemList;

    }

    /**
     * Create Recipe list from Json string
     *
     * @param recipes
     * @return
     * @throws DataProcessingException
     */
    public List<Recipe> createRecipes(String recipes) throws DataProcessingException {

        if (StringUtils.isBlank(recipes)) {
            throw new DataProcessingException(INVALID_RECIPE_DATA);
        }

        JSONObject recipeObj = new JSONObject(recipes);
        JSONArray recipeArray = recipeObj.getJSONArray(RECIPES);

        // create list of pizza from data
        List<Recipe> recipeList = new ArrayList<>();

        recipeArray.forEach(recipeJsonObj -> recipeList.add(new Recipe((JSONObject) recipeJsonObj)));

        // count duplicate values
        long duplicatedPizzaCount = recipeList.stream()
                .collect(Collectors.groupingBy(Recipe::getPizzaId)).values().stream()
                .filter(groups -> groups.size() > 1)
                .count();

        if (duplicatedPizzaCount > 0) {
            throw new DataProcessingException("Duplicated Pizza found");
        }

        return recipeList;
    }

    /**
     * Create Pizza list from Json string
     *
     * @param pizzas
     * @return
     * @throws DataProcessingException
     */
    public List<Pizza> createPizzas(String pizzas) throws DataProcessingException {

        if (StringUtils.isBlank(pizzas)) {
            throw new DataProcessingException(INVALID_PIZZA_DATA);
        }

        JSONObject pizzaObj = new JSONObject(pizzas);
        JSONArray pizzaArray = pizzaObj.getJSONArray(PIZZAS);

        // create list of pizza from data
        List<Pizza> pizzaList = new ArrayList<>();
        pizzaArray.forEach(pizzaJsonObj -> pizzaList.add(new Pizza((JSONObject) pizzaJsonObj)));

        // count duplicate values
        long duplicatedPizzaCount = pizzaList.stream()
                .collect(Collectors.groupingBy(Pizza::getId)).values().stream()
                .filter(groups -> groups.size() > 1)
                .count();

        if (duplicatedPizzaCount > 0) {
            throw new DataProcessingException("Duplicated Pizza found");
        }

        return pizzaList;
    }

}
