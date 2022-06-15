package dev.entimaniac.deg.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;

import graphql.schema.SelectedField;
import io.leangen.graphql.execution.ResolutionEnvironment;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EntityGraphGenerator {

  /**
   * This method takes in a graphQl's resolutionEnvironment and build an entity graph out of the
   * query paths.
   */
  public static EntityGraph getEntityGraph(ResolutionEnvironment environment) {
    // GetFields() recursively gets all fields requested
    List<SelectedField> selectedFields =
        environment.dataFetchingEnvironment.getSelectionSet().getFields();

    // SelectedField provides a qualified bath like top-level-field/sub-object/sub-object-field.
    // we convert the "/" slashes into "." periods to build an entity graph with
    List<String> attributePaths =
        selectedFields.stream().map(f -> f.getQualifiedName().replaceAll("/", ".")).toList();

    return (attributePaths.isEmpty())
        ? null
        : EntityGraphUtils.fromAttributePaths(attributePaths.toArray(new String[0]));
  }
}
