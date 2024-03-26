package hu.pe.rfmoodle.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "degrees")
public class DegreeEntity extends BaseEntity {

    private String name;


    @JsonIgnore
    @ManyToMany(mappedBy = "approvedDegrees" ,fetch = FetchType.EAGER)
    private Set<CourseEntity> availableCourses;
}
