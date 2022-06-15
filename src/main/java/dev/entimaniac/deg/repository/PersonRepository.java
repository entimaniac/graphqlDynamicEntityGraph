package dev.entimaniac.deg.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;

import dev.entimaniac.deg.model.Person;

@Repository
public interface PersonRepository extends EntityGraphJpaRepository<Person, Long>, EntityGraphJpaSpecificationExecutor<Person> {
}
