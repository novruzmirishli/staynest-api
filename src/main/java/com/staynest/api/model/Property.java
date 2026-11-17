
package com.staynest.api.model;

import java.math.BigDecimal;

public class Property {

    private String id;
    private String title;
    private String description;
    private BigDecimal pricePerNight;
    private String location;
    private String ownerId;

    public Property() {
    }

    public Property(String id, String title, String description,
                    BigDecimal pricePerNight, String location, String ownerId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.location = location;
        this.ownerId = ownerId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
