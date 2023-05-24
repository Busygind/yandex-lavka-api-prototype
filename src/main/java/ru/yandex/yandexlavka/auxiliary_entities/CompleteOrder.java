package ru.yandex.yandexlavka.auxiliary_entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@ToString
public class CompleteOrder {
    @NotNull
    @JsonProperty("courier_id")
    private Long courierId;
    @NotNull
    @JsonProperty("order_id")
    private Long orderId;
    @NotNull
    @JsonProperty("complete_time")
    private Instant completeTime;
}
