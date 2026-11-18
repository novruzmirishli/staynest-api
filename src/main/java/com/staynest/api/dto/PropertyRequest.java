package com.staynest.api.dto;

import java.math.BigDecimal;

public class PropertyRequest {
    
    private String title;
    private String description;
    private BigDecimal pricePerNight;
    private String location;

    public PropertyRequest() {
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

    
}