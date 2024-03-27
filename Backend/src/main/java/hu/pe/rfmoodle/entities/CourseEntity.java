package hu.pe.rfmoodle.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class CourseEntity extends BaseEntity {

    private String code;
    private String name;
    private int credit;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "approved_degrees",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "degree_id"))
    private Set<DegreeEntity> approvedDegrees = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "courses")
    private Set<UserEntity> users = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
    private Set<EventEntity> events;
}
