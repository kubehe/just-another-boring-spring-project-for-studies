package pl.kubehe.cinema.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ticket", catalog = "cinema", schema = "public")
public class TicketEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(nullable = false)
  private Long seatRow;


  @Column(nullable = false)
  private Long seatColumn;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "reservation_id")
  private ReservationEntity reservation;


  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "ticket_type_id")
  private TicketTypeEntity ticketType;

}
