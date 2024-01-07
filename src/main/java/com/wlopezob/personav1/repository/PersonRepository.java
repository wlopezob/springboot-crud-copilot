package com.wlopezob.personav1.repository;

import com.wlopezob.personav1.model.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByDni(String dni);
}