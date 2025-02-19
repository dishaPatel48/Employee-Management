package com.organization.employeeManagement.setUp;

import com.organization.employeeManagement.dto.UserLoginDTO;
import com.organization.employeeManagement.dto.UserSignUpDTO;
import com.organization.employeeManagement.dto.UserFounderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/signup", params = "role")
    public ResponseEntity<Void> signUpEmployee(@RequestBody UserSignUpDTO userSignUpDTO, @RequestParam Role role) {
        authService.signUp(userSignUpDTO, role);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PostMapping("/signup/founder")
    public ResponseEntity<Void> signUpManager(@RequestBody UserFounderDTO userFounderDTO) {
        authService.signUpFounder(userFounderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userLoginDTO) {
        String token = authService.verify(userLoginDTO);
        return ResponseEntity.ok(token);
    }

    @PutMapping(value = "/user/update/{username}", params = "role")
    public ResponseEntity<Void> updateRole(@PathVariable String username,@RequestParam Role role) {
        authService.updateRole(username,role);
        return ResponseEntity.ok(null);
    }


}
