package tech.kamikazi.warehousemanagementsystembackend.kafkaConfig;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
@Bean
    public NewTopic stockMovementTopic(){
    return new NewTopic("stock-movement-topic", 2, (short) 1);
}
}
