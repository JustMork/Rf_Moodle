package hu.pe.rfmoodle.controllers;

import hu.pe.rfmoodle.entities.CourseEntity;
import hu.pe.rfmoodle.entities.DegreeEntity;
import hu.pe.rfmoodle.repositiories.DegreeRepository;
import hu.pe.rfmoodle.repositiories.UserRepository;
import hu.pe.rfmoodle.security.http.AuthenticationResponse;
import hu.pe.rfmoodle.security.http.RegisterRequest;
import hu.pe.rfmoodle.security.JwtService;
import hu.pe.rfmoodle.entities.UserEntity;
import hu.pe.rfmoodle.security.http.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final DegreeRepository degreeRepository;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){

        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Optional<DegreeEntity> oDegree = degreeRepository.findById(1L);

        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .name(request.getName())
                .degree(oDegree.get())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);

        String token = jwtService.generateToken((UserDetails) user);
        return ResponseEntity.ok(
                AuthenticationResponse.builder()
                        .token(token)
                        .build()
        );
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserEntity user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(
                AuthenticationResponse.builder()
                        .token(token)
                        .build()
        );
    }
    @GetMapping
    public ResponseEntity<UserEntity> getAuthenticatedUser(@AuthenticationPrincipal UserEntity authenticatedUser){
        return ResponseEntity.ok(authenticatedUser);
    }

}
