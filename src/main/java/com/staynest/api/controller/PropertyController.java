
package com.staynest.api.controller;

import com.staynest.api.dto.PropertyRequest;
import com.staynest.api.model.Property;
import com.staynest.api.service.PropertyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



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
    public Property creaProperty(@RequestBody PropertyRequest request){
        return propertyService.creaProperty(request);
    }
}