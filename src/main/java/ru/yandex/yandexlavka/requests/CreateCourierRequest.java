package ru.yandex.yandexlavka.requests;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import ru.yandex.yandexlavka.dtos.CreateCourierDTO;

import java.util.List;

@Getter
@Setter
public class CreateCourierRequest {
    @Valid
    private List<CreateCourierDTO> couriers;
}
