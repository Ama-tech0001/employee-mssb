package axxentis.intenship.laboratoireapi.repositories;


import axxentis.intenship.laboratoireapi.entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PrivilegeRepository extends JpaRepository <Privilege, Long> {
    Boolean existsByLibelle(String libelle);

    Optional<Privilege> findByLibelle(String libelle);

    List<Privilege> findPrivilegeByLibelleContains(String searchKey);
}
