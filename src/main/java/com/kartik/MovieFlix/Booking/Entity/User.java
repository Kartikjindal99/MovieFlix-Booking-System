package com.kartik.MovieFlix.Booking.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String username;
    private String email;
    private String password;

    @ElementCollection(fetch=FetchType.EAGER)
    private Set<String>roles;

    @OneToMany(mappedBy = "user")
    private List<Booking> booking;

    public Collection<?extends GrantedAuthority> getAuthorities(){
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

}
