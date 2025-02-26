package com.JeongCommunity.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Table(name = "users")
@NoArgsConstructor
@Getter
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="password")
    private String password;

    @Builder
    public User(String email, String password, String auth) {
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getPassword() {
        return this.password;
    };

    @Override
    public String getUsername() {
        return this.email;
    }

    // 계정 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        // 만료 여부 확인하는 코드
        return true;
    }

    // 계정 잠금 여부
    @Override
    public boolean isAccountNonLocked() {
        // 잠금 여부를 확인하는 코드
        return true;
    };

    // 패스워드 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        // 패스워드 만료 여부 확인하는 코드
        return true;
    };

    // 계정 사용 가능 여부
    @Override
    public boolean isEnabled() {
        // 계정 사용 가능 여부 확인하는 코드
        return true;
    };


}
