package pl.kubehe.cinema.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kubehe.cinema.infrastructure.model.TicketEntity;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
}
