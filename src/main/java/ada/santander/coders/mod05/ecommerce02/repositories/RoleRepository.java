package ada.santander.coders.mod05.ecommerce02.repositories;

import ada.santander.coders.mod05.ecommerce02.entities.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
