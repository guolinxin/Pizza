package com.yell.pizza.model;

import java.util.Objects;

/**
 * Class representing MenuItem
 */
public class MenuItem {

    private Pizza pizza;
    private Recipe recipe;

    public MenuItem() {
    }

    public MenuItem(Pizza pizza, Recipe recipe) {
        this.pizza = pizza;
        this.recipe = recipe;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(pizza, menuItem.pizza) &&
                Objects.equals(recipe, menuItem.recipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pizza, recipe);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(pizza.getTitle())
                .append(" : ")
                .append("£")
                .append(pizza.getPriceInpound())
                .append(" : ")
                .append(recipe.getIngredientsString())
                .toString();
    }
}
