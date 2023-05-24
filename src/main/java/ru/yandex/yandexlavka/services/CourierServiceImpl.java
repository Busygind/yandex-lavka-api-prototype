package ru.yandex.yandexlavka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.converters.CreateCourierDtoToCourierDtoConverter;
import ru.yandex.yandexlavka.dtos.CourierDTO;
import ru.yandex.yandexlavka.dtos.CreateCourierDTO;
import ru.yandex.yandexlavka.repos.CouriersRepository;
import ru.yandex.yandexlavka.requests.CreateCourierRequest;
import ru.yandex.yandexlavka.responses.CreateCouriersResponse;
import ru.yandex.yandexlavka.responses.GetCourierMetaInfoResponse;
import ru.yandex.yandexlavka.responses.GetCourierResponse;
import ru.yandex.yandexlavka.util.CourierEarningsCalculator;
import ru.yandex.yandexlavka.util.CourierRatingCalculator;
import ru.yandex.yandexlavka.validation.exceptions.EntityNotFoundException;
import ru.yandex.yandexlavka.validation.exceptions.IncorrectDatesIntervalException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CourierServiceImpl implements CourierService {

    private final CouriersRepository repository;
    private final CreateCourierDtoToCourierDtoConverter createCourierDtoToCourierDtoConverter;
    private final CourierRatingCalculator ratingCalculator;
    private final CourierEarningsCalculator earningsCalculator;

    @Autowired
    public CourierServiceImpl(CouriersRepository repository,
                              CreateCourierDtoToCourierDtoConverter converter,
                              CourierRatingCalculator ratingCalculator,
                              CourierEarningsCalculator earningsCalculator) {
        this.repository = repository;
        this.createCourierDtoToCourierDtoConverter = converter;
        this.ratingCalculator = ratingCalculator;
        this.earningsCalculator = earningsCalculator;
    }

    @Override
    public List<GetCourierResponse> getCouriers(Integer limit, Integer offset) {
        Page<CourierDTO> page = repository.findAll(PageRequest.of(offset, limit));
        List<CourierDTO> couriers = page.getContent();

        GetCourierResponse response = new GetCourierResponse();
        response.setCouriers(couriers);
        response.setLimit(limit);
        response.setOffset(offset);

        List<GetCourierResponse> responses = new ArrayList<>();
        responses.add(response);

        return responses;
    }

    @Override
    public List<CreateCouriersResponse> createCourier(CreateCourierRequest createCourierRequest) {
        List<CourierDTO> couriersToSave = new ArrayList<>();
        for (CreateCourierDTO courier : createCourierRequest.getCouriers()) {
            couriersToSave.add(createCourierDtoToCourierDtoConverter.convert(courier));
        }
        CreateCouriersResponse response = new CreateCouriersResponse();
        List<CreateCouriersResponse> responses = new ArrayList<>();
        response.setCouriers(repository.saveAll(couriersToSave));
        responses.add(response);
        return responses;
    }

    @Override
    public ResponseEntity<List<CourierDTO>> getCourierById(Long courierId) {
        Optional<CourierDTO> optional = repository.findById(courierId);
        return optional.map(courierDTO -> {
                    List<CourierDTO> courier = new ArrayList<>();
                    courier.add(courierDTO);
                    return ResponseEntity.status(HttpStatus.OK).body(courier);
                })
                .orElseThrow(() -> new EntityNotFoundException("No such courier with specified id"));
    }

    @Override
    public ResponseEntity<List<GetCourierMetaInfoResponse>> getCourierMetaInfo(Long courierId, Date startDate, Date endDate) {
        if (!startDate.before(endDate))
            throw new IncorrectDatesIntervalException("endDate should be greater than endDate");

        Optional<CourierDTO> optional = repository.findById(courierId);
        if (optional.isEmpty()) throw new EntityNotFoundException("No such courier with specified id");
        CourierDTO courier = optional.get();
        GetCourierMetaInfoResponse courierMetaInfoResponse = new GetCourierMetaInfoResponse(courier,
                ratingCalculator.calculate(courier, startDate.toInstant(), endDate.toInstant()),
                earningsCalculator.calculate(courier, startDate.toInstant(), endDate.toInstant()));
        List<GetCourierMetaInfoResponse> responses = new ArrayList<>();
        responses.add(courierMetaInfoResponse);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }
}
