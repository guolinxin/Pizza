package com.yell.pizza.model;


import org.json.JSONObject;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * * Class representing Recipe
 */
public class Recipe {

    private String pizzaId;
    private String[] ingredients;

    public Recipe() {
    }

    public Recipe(String pizzaId, String[] ingredients) {
        this.pizzaId = pizzaId;
        this.ingredients = ingredients;
    }

    /**
     * Create Recipe instance from JSONObject
     *
     * @param recipeJsonObj
     */
    public Recipe(JSONObject recipeJsonObj) {
        this.pizzaId = recipeJsonObj.getString("pizzaId");
        this.ingredients = recipeJsonObj.getJSONArray("ingredients").toList().toArray(new String[0]);
    }


    public String getIngredientsString() {
        return Stream.of(ingredients)
                .collect(Collectors.joining(", "));
    }

    public String getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(String pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(pizzaId, recipe.pizzaId) &&
                Arrays.equals(ingredients, recipe.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pizzaId, ingredients);
    }

    /**
     * Override toString method
     * Formating MenuItem sting output
     *
     * @return
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Recipe{");
        sb.append("pizzaId='").append(pizzaId).append('\'');
        sb.append(", ingredients=").append(Arrays.toString(ingredients));
        sb.append('}');
        return sb.toString();
    }
}
