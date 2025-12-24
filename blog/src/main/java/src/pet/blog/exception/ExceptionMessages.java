package src.pet.blog.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessages {

    public static final String BAD_REQUEST = "Не валидные входные данные";
    public static final String NOT_FOUND = "По вашему запросу ничего не найдено";
}
