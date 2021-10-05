package axxentis.intenship.laboratoireapi.configurations;

import axxentis.intenship.laboratoireapi.entities.Employee;
import axxentis.intenship.laboratoireapi.entities.RefreshToken;
import axxentis.intenship.laboratoireapi.repositories.EmployeeRepository;
import axxentis.intenship.laboratoireapi.security.jwt.JwtUtils;
import axxentis.intenship.laboratoireapi.security.services.EmployeeDetailsImpl;
import axxentis.intenship.laboratoireapi.services.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class CurrentUser {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RefreshTokenService refreshTokenService;

    public Employee information() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmployeeDetailsImpl employeeDetails = (EmployeeDetailsImpl) authentication.getPrincipal();
        return employeeRepository.findById(employeeDetails.getId()).orElse(null);
    }

    public EmployeeDetailsImpl getEmployeeDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (EmployeeDetailsImpl) authentication.getPrincipal();
    }

    public String getToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmployeeDetailsImpl employeeDetails = (EmployeeDetailsImpl) authentication.getPrincipal();
        return jwtUtils.generateJwtToken(employeeDetails);
    }

    public String getRefreshToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmployeeDetailsImpl employeeDetails = (EmployeeDetailsImpl) authentication.getPrincipal();
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(employeeDetails.getId());
        return refreshToken.getToken();
    }
}
