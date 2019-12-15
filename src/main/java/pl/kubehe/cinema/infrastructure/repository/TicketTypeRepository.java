package pl.kubehe.cinema.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kubehe.cinema.infrastructure.model.TicketTypeEntity;

public interface TicketTypeRepository extends JpaRepository<TicketTypeEntity, Long> {
}
