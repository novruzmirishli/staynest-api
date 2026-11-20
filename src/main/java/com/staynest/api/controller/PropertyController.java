package com.staynest.api.controller;

import com.staynest.api.dto.PropertyRequest;
import com.staynest.api.model.Property;
import com.staynest.api.security.MockAuthenticationService;
import com.staynest.api.security.MockUser;
import com.staynest.api.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

    private final PropertyService propertyService;
    private final MockAuthenticationService authenticationService;

    public PropertyController(PropertyService propertyService, MockAuthenticationService authenticationService) {
        this.propertyService = propertyService;
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public ResponseEntity<?> getAllProperties(
        @RequestHeader(value = "Authorization", required = false) String token) {

    MockUser user = authenticationService.authenticate(token);

    if (user == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("Missing or invalid authentication token");
    }
        return ResponseEntity.ok(propertyService.getAllProperties(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPropertyById(@PathVariable String id, @RequestHeader (value = "Authorization", required = false) String token){

        MockUser user = authenticationService.authenticate(token);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Missing or invalid authentication token");
    }
        Property property = propertyService.getPropertyById(id);

        if (property == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Property not found");
    }

    if (!user.getRole().equals("ADMIN")
            && !property.getOwnerId().equals(user.getUserId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("You are not allowed to access this property");
            }
        return ResponseEntity.ok(property);
    }

    @PostMapping
    public ResponseEntity<?> createProperty(
        @RequestBody PropertyRequest request,
        @RequestHeader(value = "Authorization", required = false) String token) {

    MockUser user = authenticationService.authenticate(token);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Missing or invalid authentication token");
    }

    Property property = propertyService.createProperty(request, user);

    return ResponseEntity.status(HttpStatus.CREATED).body(property);
}

    @PutMapping("/{id}")
public ResponseEntity<?> updateProperty(
        @PathVariable String id,
        @RequestBody PropertyRequest request,
        @RequestHeader(value = "Authorization", required = false) String token) {

    MockUser user = authenticationService.authenticate(token);

    if (user == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Missing or invalid authentication token");
    }

    Property property = propertyService.getPropertyById(id);

    if (property == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Property not found");
    }

    if (!user.getRole().equals("ADMIN")
            && !property.getOwnerId().equals(user.getUserId())) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("You are not allowed to update this property");
    }

    Property updatedProperty = propertyService.updateProperty(id, request);

    return ResponseEntity.ok(updatedProperty);
}

    @DeleteMapping("/{id}")
    public boolean deleteProperty(@PathVariable String id){
        return propertyService.deleteProperty(id);
    }
}