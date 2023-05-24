package ru.yandex.yandexlavka.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yandex.yandexlavka.dtos.CompleteOrderRequestDTO;
import ru.yandex.yandexlavka.dtos.OrderDTO;
import ru.yandex.yandexlavka.requests.CreateOrderRequest;

import java.util.List;

public interface OrdersService {

    List<OrderDTO> getOrders(Integer limit, Integer offset);

    List<OrderDTO> createOrders(CreateOrderRequest request);

    ResponseEntity<List<OrderDTO>> completeOrder(CompleteOrderRequestDTO dto);

    ResponseEntity<List<OrderDTO>> getOrder(Long orderId);
}
