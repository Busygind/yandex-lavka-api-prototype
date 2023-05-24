package ru.yandex.yandexlavka.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.yandex.yandexlavka.validation.TimeIntervals;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {

    @Positive
    @NotNull
    private Float weight;
    @NotNull
    private Integer regions;

    @NotNull
    @JsonProperty("delivery_hours")
    @TimeIntervals
    private List<String> deliveryHours;

    @Positive
    private Integer cost;
}
