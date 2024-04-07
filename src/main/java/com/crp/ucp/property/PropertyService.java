package com.crp.ucp.property;

import com.crp.ucp.server.model.PropertyType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public List<PropertyEntity> getAllProperties(Optional<PropertyEntity.PropertyType> propertyType) {
        if (propertyType.isPresent()) {
            return propertyRepository.findAllByType(propertyType.get());
        } else {
            return propertyRepository.findAll();
        }
    }
}
