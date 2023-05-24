package ru.yandex.yandexlavka.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.*;
import ru.yandex.yandexlavka.auxiliary_entities.CompleteOrder;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CompleteOrderRequestDTO {

    @JsonProperty("complete_info")
    @Valid
    private List<CompleteOrder> completeInfo;

}
