package com.d288.vacationbookings.dao;

import com.d288.vacationbookings.entities.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "excursions", path = "excursions")
public interface ExcursionRepository extends JpaRepository<Excursion, Long> {
}
