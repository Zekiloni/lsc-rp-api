package net.lscrp.ucp.property;

import net.lscrp.ucp.server.model.Property;
import net.lscrp.ucp.server.model.PropertyType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PropertyController implements net.lscrp.ucp.server.api.PropertyApi {

    private final PropertyService propertyService;

    private final PropertyMapper propertyMapper;

    @Override
    public ResponseEntity<List<Property>> listProperties(PropertyType type) {
        return ResponseEntity.ok(propertyMapper.mapTo(propertyService.getAllProperties(Optional.ofNullable(propertyMapper.mapTo(type)))));
    }
}
