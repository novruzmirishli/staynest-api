
package com.staynest.api.service;

import com.staynest.api.model.Property;
import com.staynest.api.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Property getPropertyById(String id) {
        return propertyRepository.findById(id).orElse(null);
    }
}
