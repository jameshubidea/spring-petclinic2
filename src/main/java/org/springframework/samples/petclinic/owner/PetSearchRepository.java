package org.springframework.samples.petclinic.owner;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

/**
 * Database queries used by the pet search feature.
 * This is a read-only repository interface; it does not expose save/delete operations.
 */
public interface PetSearchRepository extends Repository<Pet, Integer> {

	@Query("""
            select p.id as petId,
                   p.name as petName,
                   p.birthDate as birthDate,
                   t.name as typeName,
                   o.id as ownerId,
                   o.firstName as ownerFirstName,
                   o.lastName as ownerLastName
            from Owner o
            join o.pets p
            left join p.type t
            where lower(p.name) like lower(concat('%', :keyword, '%'))
               or lower(t.name) like lower(concat('%', :keyword, '%'))
               or lower(o.firstName) like lower(concat('%', :keyword, '%'))
               or lower(o.lastName) like lower(concat('%', :keyword, '%'))
            order by p.name
            """)
	List<PetSearchResult> searchPets(@Param("keyword") String keyword);
}
