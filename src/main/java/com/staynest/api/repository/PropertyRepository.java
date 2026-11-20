
package com.staynest.api.repository;

import com.staynest.api.model.Property;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PropertyRepository {

    private final List<Property> properties = new ArrayList<>();

    public PropertyRepository() {
        properties.add(new Property(
                "1",
                "Seaside Villa Mardakan",
                "A beautiful villa with a sea view.",
                new BigDecimal("150.00"),
                "Baku, Azerbaijan",
                "user-a"
        ));

        properties.add(new Property(
                "2",
                "Mountain Cottage Quba",
                "A peaceful cottage surrounded by nature.",
                new BigDecimal("65.00"),
                "Gabala, Azerbaijan",
                "user-b"
        ));
        properties.add(new Property(
        "3",
        "City Apartment Baku",
        "A modern apartment in the city center.",
        new BigDecimal("90.00"),
        "Baku, Azerbaijan",
        "user-a"

        ));

        properties.add(new Property(
        "4",
        "Forest House Gabala",
        "A comfortable house surrounded by green forests.",
        new BigDecimal("110.00"),
        "Gabala, Azerbaijan",
        "user-b"
        
        ));
    }

    public List<Property> findAll() {
        return properties;
    }

    public Optional<Property> findById(String id) {
        return properties.stream()
                .filter(property -> property.getId().equals(id))
                .findFirst();
    }

    public Property save(Property property) {
        properties.add(property);
        return property;
    }

    public void delete(Property property) {
        properties.remove(property);
    }
}
