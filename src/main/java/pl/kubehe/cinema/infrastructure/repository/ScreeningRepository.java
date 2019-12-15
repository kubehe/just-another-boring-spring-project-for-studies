package pl.kubehe.cinema.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kubehe.cinema.infrastructure.model.ScreeningEntity;

import java.time.LocalDateTime;
import java.util.List;


public interface ScreeningRepository extends JpaRepository<ScreeningEntity, Long> {

  @Query("select s from ScreeningEntity s where s.startTime >= :currentTime and s.startTime >= :startTime and s.startTime <= :endTime ")
  List<ScreeningEntity> findAllByCurrentTimeAndSearchTime(@Param("currentTime") LocalDateTime currentTime, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
