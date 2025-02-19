package com.organization.employeeManagement.setUp;

import com.organization.employeeManagement.dto.UserLoginDTO;
import com.organization.employeeManagement.dto.UserSignUpDTO;
import com.organization.employeeManagement.dto.UserFounderDTO;
import com.organization.employeeManagement.entities.Employee;
import com.organization.employeeManagement.exception.IncompleteInformation;
import com.organization.employeeManagement.exception.RecordAlreadyExist;
import com.organization.employeeManagement.exception.RecordDoesNotExist;
import com.organization.employeeManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EmployeeService employeeService;

    public void signUp(UserSignUpDTO userSignUpDTO, Role role) {
        String username = verifyUsername(userSignUpDTO.getUsername());
        Employee employee = verifyEmployee(userSignUpDTO.getEmpId());
        String password = new BCryptPasswordEncoder(10).encode(userSignUpDTO.getPassword());

        User user = new User(username, password);
        user.setRole(role);
        user.setEmployee(employee);
        userRepository.save(user);
    }


    public void signUpFounder(UserFounderDTO userFounderDTO) {
        String username = verifyUsername(userFounderDTO.getUsername());
        String password = new BCryptPasswordEncoder(10).encode(userFounderDTO.getPassword());

        User user = new User(username, password);
        user.setRole(Role.FOUNDER);
        userRepository.save(user);
    }

    private String verifyUsername(String username) {
        if (userRepository.existsById(username)) {
            throw new RecordAlreadyExist("This user already exist");
        }
        return username;
    }

    private Employee verifyEmployee(Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employeeService.getEmployeeById(id) == null) {
            throw new RecordDoesNotExist("There is no employee with this id");
        }
        if (userRepository.existsByEmployeeId(id)) {
            throw new RecordAlreadyExist("There is user already present with this id");
        }
        return employee;
    }

    public String verify(UserLoginDTO userLoginDTO) {
        Authentication authentication =
                authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(),
                        userLoginDTO.getPassword()));
        if (!authentication.isAuthenticated()) {
            throw new IncompleteInformation("password does not match");
        }
        return jwtService.generateToken(authentication.getName());
    }

    public void updateRole(String username, Role role) {
        User user = userRepository.findById(username).orElseThrow(
                () -> new RecordDoesNotExist("No user with username: " + username)
        );

        user.setRole(role);
        userRepository.save(user);
    }
}
