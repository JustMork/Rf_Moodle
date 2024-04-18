package hu.pe.rfmoodle.controllers;

import hu.pe.rfmoodle.entities.CourseEntity;
import hu.pe.rfmoodle.entities.EventEntity;
import hu.pe.rfmoodle.entities.UserEntity;
import hu.pe.rfmoodle.repositiories.CourseRepository;
import hu.pe.rfmoodle.repositiories.EventRepository;
import jdk.jfr.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {

    @Autowired
    private final EventRepository eventRepository;
    @Autowired
    private final CourseRepository courseRepository;

    @PostMapping("/{id}")
    public ResponseEntity<EventEntity> createEvent(@AuthenticationPrincipal UserEntity authenticatedUser, @PathVariable long id, @RequestBody EventEntity event){

        Optional<CourseEntity> oCourse = courseRepository.findById(id);
        if(oCourse.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if(!authenticatedUser.isAdmin()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        CourseEntity course = oCourse.get();

        EventEntity createdEvent = EventEntity.builder()
                .name(event.getName())
                .description(event.getDescription())
                .build();

        eventRepository.save(createdEvent);

        course.getEvents().add(createdEvent);
        courseRepository.save(course);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@AuthenticationPrincipal UserEntity authenticatedUser, @PathVariable long id){

        Optional<EventEntity> oEvent = eventRepository.findById(id);
        if(oEvent.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if(!authenticatedUser.isAdmin()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        eventRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
