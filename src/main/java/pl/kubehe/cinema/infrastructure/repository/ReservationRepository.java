package pl.kubehe.cinema.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kubehe.cinema.infrastructure.model.ReservationEntity;

import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

  Optional<ReservationEntity> findByUuid(UUID uuid);
}
