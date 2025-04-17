```java
// File: src/main/java/com/example/loginapi/LoginApiApplication.java

package com.example.loginapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the Login API Spring Boot application.
 */
@SpringBootApplication
public class LoginApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginApiApplication.class, args);
    }
}

// File: src/main/java/com/example/loginapi/controller/LoginController.java

package com.example.loginapi.controller;

import com.example.loginapi.dto.LoginRequest;
import com.example.loginapi.dto.LoginResponse;
import com.example.loginapi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to handle login operations.
 */
@RestController
@RequestMapping("/api/v1")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Endpoint for user login.
     * 
     * @param loginRequest the login request containing username and password
     * @return ResponseEntity containing login response
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = loginService.authenticate(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}

// File: src/main/java/com/example/loginapi/dto/LoginRequest.java

package com.example.loginapi.dto;

/**
 * Data Transfer Object for login request.
 */
public class LoginRequest {
    
    private String username;
    private String password;

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

// File: src/main/java/com/example/loginapi/dto/LoginResponse.java

package com.example.loginapi.dto;

/**
 * Data Transfer Object for login response.
 */
public class LoginResponse {

    private String message;
    private String token;

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

// File: src/main/java/com/example/loginapi/service/LoginService.java

package com.example.loginapi.service;

import com.example.loginapi.dto.LoginRequest;
import com.example.loginapi.dto.LoginResponse;
import org.springframework.stereotype.Service;

/**
 * Service class for handling login logic.
 */
@Service
public class LoginService {

    /**
     * Authenticates a user based on the login request.
     *
     * @param loginRequest the login request
     * @return LoginResponse containing message and token
     */
    public LoginResponse authenticate(LoginRequest loginRequest) {
        // Security: Validate user inputs
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        
        // Perform authentication logic and generate token
        boolean isAuthenticated = username.equals("user") && password.equals("password");
        LoginResponse loginResponse = new LoginResponse();
        
        if (isAuthenticated) {
            loginResponse.setMessage("Login successful.");
            // Generate JWT token or session ID for user
            loginResponse.setToken("sample-jwt-token");
        } else {
            loginResponse.setMessage("Invalid username or password.");
        }

        return loginResponse;
    }
}
```