package ru.yandex.yandexlavka.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Data;
import ru.yandex.yandexlavka.enums.CourierType;

import java.util.List;

@Data
@Entity
@Table(name = "couriers")
@JsonPropertyOrder({"courier_id", "courier_type", "regions", "working_hours"})
public class CourierDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courier_id")
    @JsonProperty("courier_id")
    private Long courierId;

    @Column(name = "courier_type")
    @JsonProperty("courier_type")
    private CourierType courierType;

    @ElementCollection
    @CollectionTable(name = "courier_regions", joinColumns = @JoinColumn(name = "courier_id"))
    private List<Integer> regions;

    @ElementCollection
    @CollectionTable(name = "courier_working_hours", joinColumns = @JoinColumn(name = "courier_id"))
    @JsonProperty("working_hours")
    private List<String> workingHours;
}
