package com.cxptek.entity;

import com.cxptek.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Entity
public class SysUser implements UserDetails {
    @Id
    private String username;

    private String displayName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @NotBlank
    @JsonIgnore
    private String password;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "sys_user_roles",
        joinColumns = @JoinColumn(name = "username"),
        inverseJoinColumns = @JoinColumn(name = "roles_name"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"username", "roles_name"})
    )
    private Set<Role> roles = new HashSet<>();

    @Override
    @JsonIgnore
    public Set<SimpleGrantedAuthority> getAuthorities() {
        return Stream
                .concat(getRoleNames().stream(), getPermissionNames().stream())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    @Transient
    private Set<String> roleNames;

    @Transient
    private Set<String> permissionNames;

    public Set<String> getRoleNames() {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }

    public Set<String> getPermissionNames() {
        return roles.stream()
                .map(Role::getPermissions)
                .flatMap(Collection::stream)
                .map(Permission::getName)
                .collect(Collectors.toSet());
    }
}
