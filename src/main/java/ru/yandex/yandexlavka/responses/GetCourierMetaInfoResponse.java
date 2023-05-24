package ru.yandex.yandexlavka.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.yandex.yandexlavka.dtos.CourierDTO;
import ru.yandex.yandexlavka.enums.CourierType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"courier_id", "courier_type", "regions", "working_hours", "rating", "earnings"})
public class GetCourierMetaInfoResponse {

    @JsonProperty("courier_id")
    private Long courierId;

    @JsonProperty("courier_type")
    private CourierType courierType;

    private List<Integer> regions;

    @JsonProperty("working_hours")
    private List<String> workingHours;

    private Integer rating;

    private Integer earnings;


    public GetCourierMetaInfoResponse(CourierDTO courier, Integer rating, Integer earnings) {
        this.courierId = courier.getCourierId();
        this.courierType = courier.getCourierType();
        this.regions = courier.getRegions();
        this.workingHours = courier.getWorkingHours();
        this.rating = rating;
        this.earnings = earnings;
    }

}
