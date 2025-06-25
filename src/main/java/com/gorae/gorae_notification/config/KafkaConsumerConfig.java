package com.gorae.gorae_notification.config;

import com.gorae.gorae_notification.kafka.consumer.badge.dto.BadgeNotificationEvent;
import com.gorae.gorae_notification.kafka.consumer.post.dto.*;
import com.gorae.gorae_notification.kafka.consumer.user.dto.UserNotificationChangeEvent;
import com.gorae.gorae_notification.kafka.consumer.user.dto.UserNotificationEvent;
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
    public ConcurrentKafkaListenerContainerFactory<String, UserNotificationEvent> userKafkaListenerFactory() {
        return  buildFactory(UserNotificationEvent.class, "user-notification");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserNotificationChangeEvent> userChangeKafkaListenerFactory() {
        return  buildFactory(UserNotificationChangeEvent.class, "user-notification-change");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CommentNotificationEvent> commentKafkaListenerFactory() {
        return buildFactory(CommentNotificationEvent.class, "comment-notification");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, LikedNotificationEvent> likedKafkaListenerFactory() {
        return buildFactory(LikedNotificationEvent.class, "liked-notification");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AdoptNotificationEvent> adoptKafkaListenerFactory() {
        return buildFactory(AdoptNotificationEvent.class, "adopt-notification");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BadgeNotificationEvent> badgeKafkaListenerFactory() {
        return  buildFactory(BadgeNotificationEvent.class, "badge-notification");
    }
}
