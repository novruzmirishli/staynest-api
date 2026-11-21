package com.staynest.api.controller;

import com.staynest.api.dto.PropertyRequest;
import jakarta.validation.Valid;
import com.staynest.api.model.Property;
import com.staynest.api.security.MockAuthenticationService;
import com.staynest.api.security.MockUser;
import com.staynest.api.service.PropertyService;

import java.util.List;
import java.util.Map;

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
            .body(Map.of(
    "success", false,
    "error", Map.of(
        "code", "UNAUTHORIZED",
        "message", "Missing or invalid authentication token"
    )
));
    }
        
List<Property> properties = propertyService.getAllProperties(user);

return ResponseEntity.ok(Map.of(
    "success", true,
    "message", "Properties retrieved successfully",
    "data", properties,
    "meta", Map.of(
        "total", properties.size(),
        "page", 1,
        "perPage", properties.size()
    )
));

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPropertyById(@PathVariable String id, @RequestHeader (value = "Authorization", required = false) String token){

        MockUser user = authenticationService.authenticate(token);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of(
    "success", false,
    "error", Map.of(
        "code", "UNAUTHORIZED",
        "message", "Missing or invalid authentication token"
    )
));
    }
        Property property = propertyService.getPropertyById(id);

        if (property == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of(
    "success", false,
    "error", Map.of(
        "code", "NOT_FOUND",
        "message", "Property not found"
    )
));
    }

    if (!user.getRole().equals("ADMIN")
            && !property.getOwnerId().equals(user.getUserId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(Map.of(
    "success", false,
    "error", Map.of(
        "code", "FORBIDDEN",
        "message", "You are not allowed to access this property"
    )
));
            }
        return ResponseEntity.ok(Map.of(
    "success", true,
    "message", "Property retrieved successfully",
    "data", property
));
    }

    @PostMapping
    public ResponseEntity<?> createProperty(
        @Valid @RequestBody PropertyRequest request,
        @RequestHeader(value = "Authorization", required = false) String token) {

    MockUser user = authenticationService.authenticate(token);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of(
    "success", false,
    "error", Map.of(
        "code", "UNAUTHORIZED",
        "message", "Missing or invalid authentication token"
    )
));
    }

    Property property = propertyService.createProperty(request, user);

    return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
    "success", true,
    "message", "Property created successfully",
    "data", property
));
}

    @PutMapping("/{id}")
public ResponseEntity<?> updateProperty(
        @PathVariable String id,
        @Valid @RequestBody PropertyRequest request,
        @RequestHeader(value = "Authorization", required = false) String token) {

    MockUser user = authenticationService.authenticate(token);

    if (user == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(
    "success", false,
    "error", Map.of(
        "code", "UNAUTHORIZED",
        "message", "Missing or invalid authentication token"
    )
));
    }

    Property property = propertyService.getPropertyById(id);

    if (property == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(
    "success", false,
    "error", Map.of(
        "code", "NOT_FOUND",
        "message", "Property not found"
    )
));
    }

    if (!user.getRole().equals("ADMIN")
            && !property.getOwnerId().equals(user.getUserId())) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of(
    "success", false,
    "error", Map.of(
        "code", "FORBIDDEN",
        "message", "You are not allowed to update this property"
    )
));
    }

    Property updatedProperty = propertyService.updateProperty(id, request);

    return ResponseEntity.ok(Map.of(
    "success", true,
    "message", "Property updated successfully",
    "data", updatedProperty
));
}

   @DeleteMapping("/{id}")
public ResponseEntity<?> deleteProperty(
        @PathVariable String id,
        @RequestHeader(value = "Authorization", required = false) String token) {

    MockUser user = authenticationService.authenticate(token);

    if (user == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(
    "success", false,
    "error", Map.of(
        "code", "UNAUTHORIZED",
        "message", "Missing or invalid authentication token"
    )
));
    }

    Property property = propertyService.getPropertyById(id);

    if (property == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(
    "success", false,
    "error", Map.of(
        "code", "NOT_FOUND",
        "message", "Property not found"
    )
));
    }

    if (!user.getRole().equals("ADMIN")
            && !property.getOwnerId().equals(user.getUserId())) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of(
    "success", false,
    "error", Map.of(
        "code", "FORBIDDEN",
        "message", "You are not allowed to delete this property"
    )
));
    }

    propertyService.deleteProperty(id);

    Map<String, Object> response = new java.util.LinkedHashMap<>();
response.put("success", true);
response.put("message", "Property deleted successfully");
response.put("data", null);

return ResponseEntity.ok(response);
}
}