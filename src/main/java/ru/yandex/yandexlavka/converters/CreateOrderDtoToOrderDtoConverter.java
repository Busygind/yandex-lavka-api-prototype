package ru.yandex.yandexlavka.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.yandex.yandexlavka.dtos.CreateOrderDTO;
import ru.yandex.yandexlavka.dtos.OrderDTO;

@Component
public class CreateOrderDtoToOrderDtoConverter implements Converter<CreateOrderDTO, OrderDTO> {

    @Override
    public OrderDTO convert(CreateOrderDTO source) {
        OrderDTO order = new OrderDTO();
        order.setWeight(source.getWeight());
        order.setRegions(source.getRegions());
        order.setCost(source.getCost());
        order.setDeliveryHours(source.getDeliveryHours());
        return order;
    }
}
