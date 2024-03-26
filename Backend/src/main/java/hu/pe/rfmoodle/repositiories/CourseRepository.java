package hu.pe.rfmoodle.repositiories;

import hu.pe.rfmoodle.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository <CourseEntity, Long> {
    Optional<CourseEntity> findByName(String name);
}
