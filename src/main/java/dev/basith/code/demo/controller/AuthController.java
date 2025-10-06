package dev.basith.code.demo.controller;

import dev.basith.code.demo.User;
import dev.basith.code.demo.repository.UserRepository;
import dev.basith.code.demo.service.UserService;
import dev.basith.code.demo.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> user) {
        String email = user.get("email");
        String password = passwordEncoder.encode(user.get("password"));

        if(userRepository.findByEmail(email).isPresent()){
            return new ResponseEntity<>("Email Already Exists", HttpStatus.CONFLICT);
        }
        userService.createUser(User.builder().email(email).password(password).build());
        return new ResponseEntity<>("Successfully Registered", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> user) {
        String email = user.get("email");
        String password = user.get("password");

        var userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            return new ResponseEntity<>("User Not Registered", HttpStatus.NOT_FOUND);
        }
        var userFound = userOptional.get();
        if(!passwordEncoder.matches(password, userFound.getPassword())){
            return new ResponseEntity<>("Invalid User", HttpStatus.UNAUTHORIZED);
        }
        String token = jwtUtil.generateToken(email);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
