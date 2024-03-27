package hu.pe.rfmoodle.controllers;

import hu.pe.rfmoodle.entities.CourseEntity;
import hu.pe.rfmoodle.entities.EventEntity;
import hu.pe.rfmoodle.entities.UserEntity;
import hu.pe.rfmoodle.repositiories.CourseRepository;
import hu.pe.rfmoodle.repositiories.DegreeRepository;
import hu.pe.rfmoodle.repositiories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

// Listázás, Feliratkozás, Leiratkozás,
@RestController
@CrossOrigin
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DegreeRepository degreeRepository;


    @PostMapping("/{id}")
    public ResponseEntity<Set<CourseEntity>> enrollCourse(@AuthenticationPrincipal UserEntity authenticatedUser, @PathVariable long id){
        Optional<UserEntity> oUser = userRepository.findByUsername(authenticatedUser.getUsername());
        Optional<CourseEntity> oCourse = courseRepository.findById(id);
        if(oUser.isEmpty() || oCourse.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        UserEntity user = oUser.get();
        CourseEntity course = oCourse.get();

        if(!course.getApprovedDegrees().contains(authenticatedUser.getDegree())){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

        user.getCourses().add(course);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(user.getCourses());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Set<CourseEntity>> unEnrollCourse(@AuthenticationPrincipal UserEntity authenticatedUser, @PathVariable long id){
        Optional<UserEntity> oUser = userRepository.findByUsername(authenticatedUser.getUsername());
        Optional<CourseEntity> oCourse = courseRepository.findById(id);
        if(oUser.isEmpty() || oCourse.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        UserEntity user = oUser.get();
        CourseEntity course = oCourse.get();

        user.getCourses().remove(course);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(user.getCourses());
    }



    @GetMapping("")
    public ResponseEntity<List<CourseEntity>> listAllCourses(){
        return ResponseEntity.status(HttpStatus.OK).body(courseRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity <CourseEntity> getCourseById(@PathVariable long id){
        Optional<CourseEntity> course = courseRepository.findById(id);
        if(course.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(course.get());
    }

    @GetMapping("{id}/users")
    public ResponseEntity<Set<UserEntity>> listEnrolled(@AuthenticationPrincipal UserEntity authenticatedUser, @PathVariable long id){
        Optional<CourseEntity> oCourse = courseRepository.findById(id);
        if(oCourse.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        CourseEntity course = oCourse.get();
        return ResponseEntity.status(HttpStatus.OK).body(course.getUsers());
    }

    @GetMapping("/own")
    public ResponseEntity<Set<CourseEntity>> getOwnCourses(@AuthenticationPrincipal UserEntity authenticatedUser){
        return ResponseEntity.status(HttpStatus.OK).body(authenticatedUser.getCourses());
    }

    @GetMapping("{id}/events")
    public ResponseEntity<Set<EventEntity>> listEvents(@AuthenticationPrincipal UserEntity authenticatedUser, @PathVariable long id){
        Optional<CourseEntity> oCourse = courseRepository.findById(id);
        if(oCourse.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        CourseEntity course = oCourse.get();

        if(!course.getUsers().contains(authenticatedUser)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(course.getEvents());
    }
}
