package pl.kubehe.cinema.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "screening", catalog = "cinema", schema = "public")
public class ScreeningEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "movie_id")
  private MovieEntity movie;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "room_id")
  private RoomEntity room;

  @Column(name = "start_time", nullable = false)
  private LocalDateTime startTime;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation")
  private Set<TicketEntity> tickets;
}
