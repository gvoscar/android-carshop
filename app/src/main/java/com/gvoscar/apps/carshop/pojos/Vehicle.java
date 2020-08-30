package com.gvoscar.apps.carshop.pojos;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Vehicle {
    private String id;
    private String photoUrl;
    private int occupants;
    private double price;
    private boolean used;
    private String model;
    private long createAt;
    private String dataExtra;
    private String dataExtraValue;
    private Category category;

    public Vehicle() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getOccupants() {
        return occupants;
    }

    public void setOccupants(int occupants) {
        this.occupants = occupants;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public String getDataExtra() {
        return dataExtra;
    }

    public void setDataExtra(String dataExtra) {
        this.dataExtra = dataExtra;
    }

    public String getDataExtraValue() {
        return dataExtraValue;
    }

    public void setDataExtraValue(String dataExtraValue) {
        this.dataExtraValue = dataExtraValue;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", photoUrl='" + photoUrl + '\'' +
                ", occupants=" + occupants +
                ", price=" + price +
                ", used=" + used +
                ", model='" + model + '\'' +
                ", createAt=" + createAt +
                ", dataExtra='" + dataExtra + '\'' +
                ", dataExtraValue='" + dataExtraValue + '\'' +
                ", category=" + category +
                '}';
    }

    public Map<String, Object> toMap() {
        return new HashMap<String, Object>() {{
            put("id", id);
            put("photoUrl", photoUrl);
            put("occupants", occupants);
            put("price", price);
            put("used", used);
            put("model", model);
            put("createAt", createAt);
            put("dataExtra", dataExtra);
            put("dataExtraValue", dataExtraValue);
            put("category", category.toMap());
        }};
    }
}
