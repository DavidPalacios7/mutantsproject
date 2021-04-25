package com.mercadolibre.exam.repository;

import com.mercadolibre.exam.model.dna.DnaDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MutantsRepository extends JpaRepository<DnaDB,Long> {

    long countDnaDBByIsMutantTrue();

}
