package ru.yandex.yandexlavka.responses;

import lombok.Getter;
import lombok.Setter;
import ru.yandex.yandexlavka.dtos.CourierDTO;

import java.util.List;

@Getter
@Setter
public class CreateCouriersResponse {
    private List<CourierDTO> couriers;

}
