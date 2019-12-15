package pl.kubehe.cinema.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kubehe.cinema.infrastructure.model.MovieEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

  @Query("select m from MovieEntity m join  m.screenings as s  where s.startTime >= :currentTime and s.startTime >= :startTime and s.startTime <= :endTime")
  List<MovieEntity> findAllByScreeningTime(@Param("currentTime") LocalDateTime currentTime, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
