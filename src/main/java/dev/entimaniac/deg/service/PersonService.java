package dev.entimaniac.deg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;

import dev.entimaniac.deg.model.Person;
import dev.entimaniac.deg.repository.PersonRepository;
import dev.entimaniac.deg.util.EntityGraphGenerator;
import io.leangen.graphql.execution.ResolutionEnvironment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonService {
    @Autowired PersonRepository repository;

    public List<Person> getAll(ResolutionEnvironment env) {
        EntityGraph entityGraph = EntityGraphGenerator.getEntityGraph(env);
        return (List<Person>) repository.findAll(entityGraph);
    }
}
