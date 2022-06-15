package dev.entimaniac.deg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphJpaRepositoryFactoryBean;

@EnableAsync
@EnableScheduling
@EnableJpaAuditing
@EntityScan(basePackages = "dev.entimaniac.deg")
@EnableJpaRepositories(
        repositoryFactoryBeanClass = EntityGraphJpaRepositoryFactoryBean.class)
@EnableTransactionManagement
@SpringBootApplication
public class GraphqlDynamicEntityGraphApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlDynamicEntityGraphApplication.class, args);
    }

}
