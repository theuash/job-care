package com.example.backend;

import com.example.backend.model.LoginRequest;
import com.example.backend.model.LoginResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        if (req.email().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        return new LoginResponse("mock-token-" + System.currentTimeMillis(), "Demo Employer");
    }
}
