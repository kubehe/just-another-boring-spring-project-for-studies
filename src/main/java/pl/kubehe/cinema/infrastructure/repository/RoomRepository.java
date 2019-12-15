package pl.kubehe.cinema.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kubehe.cinema.infrastructure.model.RoomEntity;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
}
