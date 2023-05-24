package ru.yandex.yandexlavka.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.yandexlavka.dtos.CourierDTO;

public interface CouriersRepository extends JpaRepository<CourierDTO, Long> {

}
