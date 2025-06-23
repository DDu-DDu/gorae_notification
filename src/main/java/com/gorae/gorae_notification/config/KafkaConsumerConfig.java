package com.gorae.gorae_notification.config;

import com.gorae.gorae_notification.kafka.consumer.post.dto.AdoptEvent;
import com.gorae.gorae_notification.kafka.consumer.post.dto.CommentEvent;
import com.gorae.gorae_notification.kafka.consumer.post.dto.LikedEvent;
import com.gorae.gorae_notification.kafka.consumer.user.dto.UserEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    private Map<String, Object> commonProps(String groupId) {
        return Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                ConsumerConfig.GROUP_ID_CONFIG, groupId,
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class
        );
    }

    private <T> ConcurrentKafkaListenerContainerFactory<String, T> buildFactory(Class<T> clazz, String groupId) {
        JsonDeserializer<T> deserializer = new JsonDeserializer<>(clazz);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(false);

        DefaultKafkaConsumerFactory<String, T> consumerFactory = new DefaultKafkaConsumerFactory<>(
                commonProps(groupId),
                new StringDeserializer(),
                deserializer
        );

        ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserEvent> userKafkaListenerFactory() {
        return  buildFactory(UserEvent.class, "user");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CommentEvent> commentKafkaListenerFactory() {
        return buildFactory(CommentEvent.class, "comment");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, LikedEvent> likedKafkaListenerFactory() {
        return buildFactory(LikedEvent.class, "liked");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AdoptEvent> adoptKafkaListenerFactory() {
        return buildFactory(AdoptEvent.class, "adopt");
    }
}
