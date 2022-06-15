package dev.entimaniac.deg.controller;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.entimaniac.deg.graphql.PersonGraphQL;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.execution.AsyncExecutionStrategy;
import graphql.execution.AsyncSerialExecutionStrategy;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;

@RestController
public class GraphQLController {
    private final GraphQL graphQL;

    public GraphQLController(PersonGraphQL personGraphQL) {
        GraphQLSchema schema =
                new GraphQLSchemaGenerator()
                        .withResolverBuilders(new AnnotatedResolverBuilder())
                        .withOperationsFromSingleton(personGraphQL)
                        .withValueMapperFactory(new JacksonValueMapperFactory())
                        .generate();

        graphQL =
                GraphQL.newGraphQL(schema)
                        .queryExecutionStrategy(new AsyncExecutionStrategy())
                        .mutationExecutionStrategy(new AsyncSerialExecutionStrategy())
                        .build();
    }

    /**
     * This method is responsible for acting as an endpoint and handling graphql requests.
     *
     * @param request the request map
     * @param raw     the raw http request
     * @return the Map having the key as string and value as Object and finally converted into the
     * JSON string
     */
    @CrossOrigin
    @PostMapping(
            value = "/graphql",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String, Object> graphql(
            @RequestBody Map<String, String> request, HttpServletRequest raw)
            throws ExecutionException, InterruptedException {
        CompletableFuture<ExecutionResult> promise =
                graphQL.executeAsync(
                        ExecutionInput.newExecutionInput()
                                .query(request.get("query"))
                                .operationName(request.get("operationName"))
                                .context(raw)
                                .build());
        return promise.get().toSpecification();
    }
}
