package net.lscrp.ucp.property;

import net.lscrp.ucp.server.model.BizType;
import net.lscrp.ucp.server.model.Property;
import net.lscrp.ucp.server.model.PropertyType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PropertyMapper {

    @Mapping(target = "owner", source = "owner.name")
    Property mapTo(PropertyEntity property);

    @Mapping(target = "owner", ignore = true)
    PropertyEntity mapTo(Property property);

    List<Property> mapTo(List<PropertyEntity> property);

    PropertyType mapTo(PropertyEntity.PropertyType propertyType);

    PropertyEntity.PropertyType mapTo(PropertyType propertyType);

    BizType mapTo(PropertyEntity.BizType bizType);

    PropertyEntity.BizType mapTo(BizType bizType);
}
