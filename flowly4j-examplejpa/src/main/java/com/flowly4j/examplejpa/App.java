package com.flowly4j.examplejpa;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.flowly4j.core.Workflow;
import com.flowly4j.core.context.ExecutionContext;
import com.flowly4j.core.input.Param;
import com.flowly4j.core.serialization.Serializer;
import com.flowly4j.jpa.JpaFlowlyRepository;
import io.vavr.jackson.datatype.VavrModule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.val;

import java.time.Instant;

import static com.flowly4j.examplejpa.CustomKeys.*;


/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {

        val objectMapperContext = new ObjectMapper();
        objectMapperContext.registerModule(new JavaTimeModule());
        objectMapperContext.registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));
        objectMapperContext.registerModule(new VavrModule(new VavrModule.Settings().deserializeNullAsEmptyCollection(true)));
        objectMapperContext.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapperContext.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        EntityManager entityManager = Persistence.createEntityManagerFactory("com.flowly4j.examplejpa").createEntityManager();

        val repository = new JpaFlowlyRepository(entityManager, objectMapperContext);

        val factory = new ExecutionContext.ExecutionContextFactory(new Serializer(objectMapperContext));

        System.out.println(repository.getToRetry().toList());

        Workflow workflow = new WorkflowA(repository, factory);

        String sessionId = workflow.init(Param.of(KEY1, "asd"), Param.of(KEY2, 123), Param.of(KEY6, Instant.EPOCH));

        val result = workflow.execute(sessionId);

        System.out.println(result);

        entityManager.close();

    }

}
