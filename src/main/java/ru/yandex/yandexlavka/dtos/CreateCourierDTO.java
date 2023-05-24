package ru.yandex.yandexlavka.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.yandex.yandexlavka.enums.CourierType;

import ru.yandex.yandexlavka.validation.TimeIntervals;

import java.util.List;

@Getter
@Setter
public class CreateCourierDTO {
    @JsonProperty("courier_type")
    @NotNull
    private CourierType courierType;

    @NotNull
    private List<Integer> regions;

    @NotNull
    @JsonProperty("working_hours")
    @TimeIntervals
    private List<String> workingHours;
}
