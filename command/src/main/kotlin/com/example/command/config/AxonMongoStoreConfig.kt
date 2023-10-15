package com.example.command.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.mongodb.client.MongoClient
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore
import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.axonframework.extensions.mongo.DefaultMongoTemplate
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine
import org.axonframework.serialization.Serializer
import org.axonframework.serialization.json.JacksonSerializer
import org.axonframework.spring.config.AxonConfiguration
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AxonMongoEventStoreConfig(private val objectMapper: ObjectMapper) {

    @Bean
    fun eventStore(storageEngine: EventStorageEngine, configuration: AxonConfiguration): EmbeddedEventStore {
        return EmbeddedEventStore.builder()
            .storageEngine(storageEngine)
            .build()
    }

    // The `MongoEventStorageEngine` stores each event in a separate MongoDB document
    @Bean
    fun storageEngine(client: MongoClient): EventStorageEngine {
        return MongoEventStorageEngine.builder()
            .mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(client).build())
            .eventSerializer(jacksonMessageSerializer())
            .snapshotSerializer(JacksonSerializer.defaultSerializer())
            .build()
    }

    @Bean
    @Qualifier("messageSerializer")
    fun jacksonMessageSerializer(): Serializer {
        objectMapper.registerModule(KotlinModule.Builder().build())
        objectMapper.registerModule(JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)

        return JacksonSerializer.builder()
            .objectMapper(objectMapper)
            .lenientDeserialization()
            .build()
    }
}