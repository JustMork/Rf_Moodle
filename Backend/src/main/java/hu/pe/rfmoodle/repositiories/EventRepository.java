package hu.pe.rfmoodle.repositiories;

import hu.pe.rfmoodle.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

}
