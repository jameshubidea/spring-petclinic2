package org.springframework.samples.petclinic.owner;

import java.time.LocalDate;

/**
 * Read-only projection containing the pet and its owner's information for search results.
 */
public interface PetSearchResult {

	Integer getPetId();

	String getPetName();

	LocalDate getBirthDate();

	String getTypeName();

	Integer getOwnerId();

	String getOwnerFirstName();

	String getOwnerLastName();
}
