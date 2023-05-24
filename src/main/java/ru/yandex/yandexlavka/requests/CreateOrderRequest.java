package ru.yandex.yandexlavka.requests;

import jakarta.validation.Valid;
import lombok.*;
import ru.yandex.yandexlavka.dtos.CreateOrderDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateOrderRequest {
    @Valid
    private List<CreateOrderDTO> orders;
}
