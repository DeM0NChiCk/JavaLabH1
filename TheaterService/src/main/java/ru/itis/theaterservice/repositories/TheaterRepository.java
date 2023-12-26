package ru.itis.theaterservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.theaterservice.models.Theater;

import java.util.List;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
    List<Theater> findAllByCity(String city);
}
