package com.crp.ucp.property;

import com.crp.ucp.server.model.Property;
import com.crp.ucp.server.model.PropertyType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PropertyApi implements com.crp.ucp.server.api.PropertyApi {

    private final PropertyService propertyService;

    private final PropertyMapper propertyMapper;

    @Override
    public ResponseEntity<List<Property>> listProperties(PropertyType type) {
        return ResponseEntity.ok(propertyMapper.mapTo(propertyService.getAllProperties(Optional.ofNullable(propertyMapper.mapTo(type)))));
    }
}
