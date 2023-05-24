package ru.yandex.yandexlavka.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.yandex.yandexlavka.dtos.CourierDTO;
import ru.yandex.yandexlavka.dtos.CreateCourierDTO;

@Component
public class CreateCourierDtoToCourierDtoConverter implements Converter<CreateCourierDTO, CourierDTO> {

    @Override
    public CourierDTO convert(CreateCourierDTO source) {
        CourierDTO courierDTO = new CourierDTO();
        courierDTO.setCourierType(source.getCourierType());
        courierDTO.setRegions(source.getRegions());
        courierDTO.setWorkingHours(source.getWorkingHours());
        return courierDTO;
    }
}
