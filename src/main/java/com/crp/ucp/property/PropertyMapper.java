package com.crp.ucp.property;

import com.crp.ucp.server.model.Property;
import com.crp.ucp.server.model.PropertyType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PropertyMapper {

    @Mapping(target = "owner", ignore = true)
    Property mapTo(PropertyEntity property);

    @Mapping(target = "owner", ignore = true)
    PropertyEntity mapTo(Property property);

    List<Property> mapTo(List<PropertyEntity> property);

    PropertyEntity.PropertyType mapTo(PropertyType propertyType);

    PropertyType mapTo(PropertyEntity.PropertyType propertyType);
}
