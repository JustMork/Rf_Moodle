package hu.pe.rfmoodle.controllers;

import hu.pe.rfmoodle.entities.DegreeEntity;
import hu.pe.rfmoodle.repositiories.DegreeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/degree")
@RequiredArgsConstructor
public class DegreeController {

    @Autowired
    private DegreeRepository degreeRepository;

    @GetMapping("")
    public ResponseEntity<List<DegreeEntity>> listDegrees(){
        return ResponseEntity.status(HttpStatus.OK).body(degreeRepository.findAll());
    }

}
