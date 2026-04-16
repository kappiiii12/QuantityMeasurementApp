package com.quantitymeasurementapp.app.auth;

import com.quantitymeasurementapp.app.entity.AppUser;
import com.quantitymeasurementapp.app.repository.UserRepository;
import com.quantitymeasurementapp.app.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authManager,
                          JwtUtil jwtUtil,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.authManager     = authManager;
        this.jwtUtil         = jwtUtil;
        this.userRepository  = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * POST /auth/register
     * Body: { "username": "kapil", "password": "secret123" }
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent())
            return ResponseEntity.badRequest().body("Username already taken.");

        AppUser user = new AppUser(
            request.getUsername(),
            passwordEncoder.encode(request.getPassword()),
            "ROLE_USER"
        );
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully.");
    }

    /**
     * POST /auth/login
     * Body: { "username": "kapil", "password": "secret123" }
     * Returns: { "token": "eyJhbGci..." }
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()));

        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
