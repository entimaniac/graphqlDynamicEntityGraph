package dev.entimaniac.deg.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.entimaniac.deg.graphql.PersonGraphQL;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;

@RestController
public class GraphQLController {
    private final GraphQL graphQL;

    public GraphQLController(PersonGraphQL personGraphQL) {
        GraphQLSchema schema =
                new GraphQLSchemaGenerator()
                        .withBasePackages("dev.entimaniac.deg")
                        .withOperationsFromSingleton(personGraphQL)
                        .generate();

        graphQL = GraphQL.newGraphQL(schema).build();
    }


    @PostMapping(value = "/graphql", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> graphql(@RequestBody Map<String, String> request, HttpServletRequest raw) {
        ExecutionResult executionResult = graphQL.execute(ExecutionInput.newExecutionInput()
                .query(request.get("query"))
                .operationName(request.get("operationName"))
                .context(raw)
                .build());
        return executionResult.toSpecification();
    }

}
