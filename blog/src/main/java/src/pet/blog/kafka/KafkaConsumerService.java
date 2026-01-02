package src.pet.blog.kafka;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import src.pet.blog.controller.dto.RegisterDtoFromAuthorService;
import src.pet.blog.service.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final UserService userService;

    private static final String JSON_VALUE_TYPE_AUTH_TO_BLOG =
            "spring.json.value.default.type=src.pet.blog.controller.dto.RegisterDtoFromAuthorService";
    public static final String TOPIC_REGISTRATION_USER = "registration.topik.event";

    @Transactional
    @KafkaListener(topics = TOPIC_REGISTRATION_USER,
            groupId = "blog-account.group",
            properties = {JSON_VALUE_TYPE_AUTH_TO_BLOG})
    public void processRegisterUser(ConsumerRecord<String, RegisterDtoFromAuthorService> message) {

        String expectedKey = "1";

        if (!expectedKey.equals(message.key())) {
            log.debug("Пропускаем сообщение с ключом: {}", message.key());
            return;
        }

        log.info("Обрабатываем сообщение с целевым ключом: {}", message.key());
        userService.processRegister(message.value());
    }
}
