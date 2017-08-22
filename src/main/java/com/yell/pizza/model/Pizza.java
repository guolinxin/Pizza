package com.yell.pizza.model;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Objects;

/**
 * * Class representing Pizza
 */
public class Pizza {

    private static final String PRICE_FORMAT = "#.00";

    private String id;
    private String title;
    private long priceInPence;

    public Pizza() {
    }

    /**
     * Create pizza instance from JSONObject
     *
     * @param pizzaJson
     */
    public Pizza(JSONObject pizzaJson) {
        this.id = pizzaJson.getString("id");
        this.title = pizzaJson.getString("title");
        this.priceInPence = pizzaJson.getInt("priceInPence");
    }

    public Pizza(String id, String title, int priceInPence) {
        this.id = id;
        this.title = title;
        this.priceInPence = priceInPence;
    }

    /**
     * Convert long value to double
     *
     * @return
     */
    public String getPriceInpound() {
        final DecimalFormat df = new DecimalFormat(PRICE_FORMAT);
        if (priceInPence > 0) {
            return df.format((double) priceInPence / 100);
        }
        return df.format(0);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPriceInPence() {
        return priceInPence;
    }

    public void setPriceInPence(long priceInPence) {
        this.priceInPence = priceInPence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return priceInPence == pizza.priceInPence &&
                Objects.equals(id, pizza.id) &&
                Objects.equals(title, pizza.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, priceInPence);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pizza{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", priceInPence=").append(priceInPence);
        sb.append('}');
        return sb.toString();
    }
}
