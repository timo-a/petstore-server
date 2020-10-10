package com.example.petstore.backend.db;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.petstore.PetstoreApplication;
import com.example.petstore.backend.api.model.Pet;
import com.example.petstore.backend.api.model.Pet.StatusEnum;

public interface PetRepository extends CrudRepository<PetBE, Long> {
	
	//spring generates a query from this method name
	public List<PetBE> findByStatus(StatusEnum e);
	
	public default List<PetBE> findPetByStatusBK(StatusEnum e) {
		
		Spliterator<PetBE> spliterator = findAll().spliterator();
		return StreamSupport.stream(spliterator, false)
				            .filter(x -> x.getStatus().equals(e))
		                    .collect(Collectors.toList());

	}
	
	@Query("SELECT p FROM PetBE p INNER JOIN p.tags t WHERE t.name IN :tags")
	public List<PetBE> findPetsByAnyTag(@Param("tags") List<String> tags);
	

	public default List<PetBE> findPetsByAnyTagBK(List<String> tags) {
		Spliterator<PetBE> spliterator = findAll().spliterator();
		Predicate<PetBE> allContained = x -> {
			List<String> ss = x.getTags().stream()
			                             .map(TagBE::getName)
			                             .collect(Collectors.toList());
			return ss.containsAll(tags);
		};
		List<PetBE> filtered = StreamSupport.stream(spliterator, false)
				                          .filter(allContained)
				                          .collect(Collectors.toList());
		return filtered;
	}

}
