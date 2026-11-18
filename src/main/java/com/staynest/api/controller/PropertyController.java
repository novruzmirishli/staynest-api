
package com.staynest.api.controller;

import com.staynest.api.dto.PropertyRequest;
import com.staynest.api.model.Property;
import com.staynest.api.service.PropertyService;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @GetMapping("/{id}")
    public Property getPropertyById(@PathVariable String id){
        return propertyService.getPropertyById(id);
    }

    @PostMapping
    public Property createProperty(@RequestBody PropertyRequest request){
        return propertyService.createProperty(request);
    }

    @PutMapping("/{id}")
    public Property updateProperty(@PathVariable String id, @RequestBody PropertyRequest request){
        return propertyService.updateProperty(id, request);
    }
    @DeleteMapping("/{id}")
    public boolean deleteProperty(@PathVariable String id){
        return propertyService.deleteProperty(id);
    }
}