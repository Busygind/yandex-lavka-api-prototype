package ru.yandex.yandexlavka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.converters.CreateOrderDtoToOrderDtoConverter;
import ru.yandex.yandexlavka.dtos.CompleteOrderRequestDTO;
import ru.yandex.yandexlavka.dtos.CreateOrderDTO;
import ru.yandex.yandexlavka.dtos.OrderDTO;
import ru.yandex.yandexlavka.auxiliary_entities.CompleteOrder;
import ru.yandex.yandexlavka.repos.OrdersRepository;
import ru.yandex.yandexlavka.requests.CreateOrderRequest;
import ru.yandex.yandexlavka.validation.exceptions.EntityNotFoundException;
import ru.yandex.yandexlavka.validation.exceptions.OrderAssignedToAnotherCourierException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrdersService {

    OrdersRepository repository;
    CreateOrderDtoToOrderDtoConverter createOrderDtoToOrderDtoConverter;

    @Autowired
    public OrdersServiceImpl(OrdersRepository repository, CreateOrderDtoToOrderDtoConverter converter) {
        this.repository = repository;
        this.createOrderDtoToOrderDtoConverter = converter;
    }

    @Override
    public List<OrderDTO> getOrders(Integer limit, Integer offset) {
        Page<OrderDTO> page = repository.findAll(PageRequest.of(offset, limit));
        return page.getContent();
    }

    @Override
    public List<OrderDTO> createOrders(CreateOrderRequest request) {
        List<OrderDTO> ordersToSave = new ArrayList<>();
        for (CreateOrderDTO order : request.getOrders()) {
            ordersToSave.add(createOrderDtoToOrderDtoConverter.convert(order));
        }
        return repository.saveAll(ordersToSave);
    }

    public ResponseEntity<List<OrderDTO>> completeOrder(CompleteOrderRequestDTO dto) {
        List<OrderDTO> completedOrders = new ArrayList<>();
        for (CompleteOrder completedOrder : dto.getCompleteInfo()) {
            Optional<OrderDTO> optionalOrder = repository.findById(completedOrder.getOrderId());
            if (optionalOrder.isPresent()) {
                OrderDTO order = optionalOrder.get();
                if (order.getCourierId() != null && !order.getCourierId().equals(completedOrder.getCourierId())) {
                    throw new OrderAssignedToAnotherCourierException("Order with id=" + order.getOrderId()
                            + " is assigned to courier with id=" + order.getCourierId());
                }
                order.setCompletedTime(completedOrder.getCompleteTime());
                order.setCourierId(completedOrder.getCourierId());
                repository.save(order);

                completedOrders.add(order);

            } else throw new EntityNotFoundException("No such order with specified id");
        }
        if (completedOrders.size() == 0) throw new EntityNotFoundException("No such order with specified id");
        return ResponseEntity.status(HttpStatus.OK).body(completedOrders);
    }

    @Override
    public ResponseEntity<List<OrderDTO>> getOrder(Long orderId) {
        Optional<OrderDTO> optional = repository.findById(orderId);
        return optional.map(orderDTO -> {
                    List<OrderDTO> order = new ArrayList<>();
                    order.add(orderDTO);
                    return ResponseEntity.status(HttpStatus.OK).body(order);
                })
                .orElseThrow(() -> new EntityNotFoundException("No such order with specified id"));
    }
}
