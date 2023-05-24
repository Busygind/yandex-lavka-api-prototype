package ru.yandex.yandexlavka.responses;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import ru.yandex.yandexlavka.dtos.CourierDTO;

import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({"couriers", "limit", "offset"})
public class GetCourierResponse {

    private List<CourierDTO> couriers;

    private Integer limit;

    private Integer offset;
}
