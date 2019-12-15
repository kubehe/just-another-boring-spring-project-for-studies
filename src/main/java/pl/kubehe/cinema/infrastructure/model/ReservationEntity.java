package pl.kubehe.cinema.infrastructure.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "reservation", catalog = "cinema", schema = "public")
public class ReservationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Builder.Default
  @Column(nullable = false, unique = true)
  private UUID uuid = UUID.randomUUID();

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String surname;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "screening_id")
  private ScreeningEntity screening;

  @EqualsAndHashCode.Exclude
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation")
  private Set<TicketEntity> tickets;
}
