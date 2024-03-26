package hu.pe.rfmoodle.controllers;

import hu.pe.rfmoodle.entities.CourseEntity;
import hu.pe.rfmoodle.entities.DegreeEntity;
import hu.pe.rfmoodle.entities.EventEntity;
import hu.pe.rfmoodle.repositiories.CourseRepository;
import hu.pe.rfmoodle.repositiories.DegreeRepository;
import hu.pe.rfmoodle.repositiories.EventRepository;
import hu.pe.rfmoodle.repositiories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
@CrossOrigin
@RequestMapping("/api/demo")
@RequiredArgsConstructor
public class DemoController {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DegreeRepository degreeRepository;
    @Autowired
    private EventRepository eventRepository;
    @PostMapping("")
    public ResponseEntity<?> createDummyData(){
        CourseEntity course1 = CourseEntity.builder()
                .code("911")
                .name("Literature")
                .credit(2)
                .approvedDegrees(new HashSet<>())
                .events(new HashSet<>())
                .build();

        CourseEntity course2 = CourseEntity.builder()
                .code("420")
                .name("Data Structures")
                .credit(4)
                .approvedDegrees(new HashSet<>())
                .events(new HashSet<>())
                .build();

        CourseEntity course3 = CourseEntity.builder()
                .code("404")
                .name("Networking")
                .credit(6)
                .approvedDegrees(new HashSet<>())
                .events(new HashSet<>())
                .build();

        DegreeEntity degree1 = DegreeEntity.builder()
                .name("IT")
                .build();
        DegreeEntity degree2 = DegreeEntity.builder()
                .name("Dumb")
                .build();


        EventEntity event = EventEntity.builder().name("Ágyba szarás").description("van").build();
        eventRepository.save(event);


        course2.getEvents().add(event);

        course1.getApprovedDegrees().add(degree2);
        course2.getApprovedDegrees().add(degree1);
        course3.getApprovedDegrees().add(degree1);


        degreeRepository.save(degree1);
        degreeRepository.save(degree2);
        courseRepository.save(course1);
        courseRepository.save(course2);
        courseRepository.save(course3);
        return null;
    }

}
