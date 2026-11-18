
package com.staynest.api.service;

import com.staynest.api.dto.PropertyRequest;
import com.staynest.api.model.Property;
import com.staynest.api.repository.PropertyRepository;
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

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Property getPropertyById(String id) {
        return propertyRepository.findById(id).orElse(null);
    }

    public Property creaProperty(PropertyRequest request){

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
}