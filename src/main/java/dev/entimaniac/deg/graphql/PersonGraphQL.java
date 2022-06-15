package dev.entimaniac.deg.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.entimaniac.deg.model.Person;
import dev.entimaniac.deg.service.PersonService;
import io.leangen.graphql.annotations.GraphQLEnvironment;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.execution.ResolutionEnvironment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PersonGraphQL {
    @Autowired
    PersonService service;


    @GraphQLQuery(name = "test")
    public String placeholder(@GraphQLEnvironment ResolutionEnvironment env) {
        return "tested";
    }


    @GraphQLQuery(name = "persons")
    public List<Person> getAll(@GraphQLEnvironment ResolutionEnvironment env) {
        return service.getAll(env);
    }
}
