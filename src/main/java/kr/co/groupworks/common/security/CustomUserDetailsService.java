package kr.co.groupworks.common.security;

import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Long employeeId = Long.valueOf(username);
        Employee employee = employeeRepository.findById(employeeId).get();
        if(employee != null) {
            return new CustomUserDetails(employee);
        }
        return null;
    }
}