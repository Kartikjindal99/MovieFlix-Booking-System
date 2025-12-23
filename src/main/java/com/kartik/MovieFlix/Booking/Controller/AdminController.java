package com.kartik.MovieFlix.Booking.Controller;

import com.kartik.MovieFlix.Booking.DTO.RegisterRequestDTO;
import com.kartik.MovieFlix.Booking.Entity.User;
import com.kartik.MovieFlix.Booking.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/registeradminuser")
    public ResponseEntity<User> registerAdminUser(
            @RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok(
                authenticationService.registerAdminUser(registerRequestDTO)
        );
    }
}

