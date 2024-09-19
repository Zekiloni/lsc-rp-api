package net.lscrp.ucp.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<PropertyEntity, Integer> {

    List<PropertyEntity> findAllByType(PropertyEntity.PropertyType type);

    List<PropertyEntity> findAllByOwnerId(Integer ownerId);
}
