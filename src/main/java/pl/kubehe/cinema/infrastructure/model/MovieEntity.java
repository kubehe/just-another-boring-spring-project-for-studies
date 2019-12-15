package pl.kubehe.cinema.infrastructure.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie", catalog = "cinema", schema = "public")
public class MovieEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(unique = true, nullable = false)
  private String title;

  @EqualsAndHashCode.Exclude
  @OneToMany(mappedBy = "movie", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
  private Set<ScreeningEntity> screenings;

}
