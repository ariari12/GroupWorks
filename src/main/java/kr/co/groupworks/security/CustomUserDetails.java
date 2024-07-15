package kr.co.groupworks.security;

import jakarta.servlet.http.HttpSession;
import kr.co.groupworks.entity.cis.Employee;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class CustomUserDetails implements UserDetails {

    private final Employee employee;


    public CustomUserDetails(Employee employee) {
        this.employee = employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(() -> employee.getRankName());
        return collect;
    }

    @Override
    public String getPassword() {
        return employee.getEmployeePW();
    }

    @Override
    public String getUsername() {
        return employee.getEmployeeId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
