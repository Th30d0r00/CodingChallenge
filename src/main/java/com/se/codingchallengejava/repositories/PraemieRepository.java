package com.se.codingchallengejava.repositories;

import com.se.codingchallengejava.Entities.Praemie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PraemieRepository extends JpaRepository<Praemie, Long> {

}
