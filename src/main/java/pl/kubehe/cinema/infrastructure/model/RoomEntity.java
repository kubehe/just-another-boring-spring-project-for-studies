package pl.kubehe.cinema.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "room", catalog = "cinema", schema = "public")
public class RoomEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(nullable = false, unique = true)
  private String name;

  @Column(name = "columns_number")
  private Long columnsNumber;

  @Column(name = "rows_number")
  private Long rowsNumber;


}
