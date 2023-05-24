package ru.yandex.yandexlavka.services;

import org.springframework.http.ResponseEntity;
import ru.yandex.yandexlavka.dtos.CourierDTO;
import ru.yandex.yandexlavka.requests.CreateCourierRequest;
import ru.yandex.yandexlavka.responses.CreateCouriersResponse;
import ru.yandex.yandexlavka.responses.GetCourierMetaInfoResponse;
import ru.yandex.yandexlavka.responses.GetCourierResponse;

import java.util.Date;
import java.util.List;

public interface CourierService {

    List<GetCourierResponse> getCouriers(Integer limit, Integer offset);

    List<CreateCouriersResponse> createCourier(CreateCourierRequest createCourierRequest);

    ResponseEntity<List<CourierDTO>> getCourierById(Long courierId);

    ResponseEntity<List<GetCourierMetaInfoResponse>> getCourierMetaInfo(Long courierId, Date startDate, Date endDate);
}
