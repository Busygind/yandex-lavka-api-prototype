package ru.yandex.yandexlavka.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
@JsonPropertyOrder({"order_id", "weight", "regions", "delivery_hours", "cost", "completed_time"})
public class OrderDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    @JsonProperty("order_id")
    private Long orderId;

    private Float weight;

    private Integer regions;

    @ElementCollection
    @CollectionTable(name = "order_delivery_hours", joinColumns = @JoinColumn(name = "order_id"))
    @JsonProperty("delivery_hours")
    private List<String> deliveryHours;

    private Integer cost;

    @Column(name = "completed_time")
    @JsonProperty("completed_time")
    private Instant completedTime;

    @JsonIgnore
    @JsonProperty("courier_id")
    @Column(name = "courier_id")
    private Long courierId;
}
