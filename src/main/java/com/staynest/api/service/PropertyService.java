
package com.staynest.api.service;

import com.staynest.api.dto.PropertyRequest;
import com.staynest.api.model.Property;
import com.staynest.api.repository.PropertyRepository;
import com.staynest.api.security.MockUser;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    private final AtomicInteger nextId = new AtomicInteger(3);

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> getAllProperties(MockUser user) {

        if (user.getRole().equals("ADMIN")) {
            return propertyRepository.findAll();
        }

        return propertyRepository.findAll().stream()
            .filter(property -> property.getOwnerId().equals(user.getUserId()))
            .toList();
    }

    public Property getPropertyById(String id) {
        return propertyRepository.findById(id).orElse(null);
    }

    public Property createProperty(PropertyRequest request){

    Property property = new Property(
        String.valueOf(nextId.getAndIncrement()),
        request.getTitle(),
        request.getDescription(),
        request.getPricePerNight(),
        request.getLocation(),
        "user-a"
    );

    return propertyRepository.save(property);
    }

    public Property updateProperty(String id, PropertyRequest request){
        Property property = propertyRepository.findById(id).orElse(null);
    
        if (property == null) {
            return null;
        }

        property.setTitle(request.getTitle());
        property.setDescription(request.getDescription());
        property.setPricePerNight(request.getPricePerNight());
        property.setLocation(request.getLocation());

    return property;
    }

    public boolean deleteProperty(String id) {
        Property property = propertyRepository.findById(id).orElse(null);

        if (property == null) {
            return false;
        }

        propertyRepository.delete(property);
        return true;
    }
}