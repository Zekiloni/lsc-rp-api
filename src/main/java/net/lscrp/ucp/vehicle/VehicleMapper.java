package net.lscrp.ucp.vehicle;

import net.lscrp.ucp.server.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    @Mapping(target = "owner", ignore = true)
    Vehicle mapTo(VehicleEntity vehicle);

    @Mapping(target = "owner", ignore = true)
    VehicleEntity mapTo(Vehicle vehicle);

    List<Vehicle> mapTo(List<VehicleEntity> vehicles);
}
