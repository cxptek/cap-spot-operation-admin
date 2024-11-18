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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Entity
@Table(name = "partner_admin_user")
@EqualsAndHashCode(callSuper = true)
public class PartnerAdminUser extends BaseEntity implements UserDetails {
    @Id
    private UUID id;

    @NotBlank
    private String email;

    private String displayName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private PartnerInfo partnerInfo;

    @NotBlank
    @JsonIgnore
    private String password;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "partner_admin_user_roles",
        joinColumns = @JoinColumn(name = "partner_admin_user_id"),
        inverseJoinColumns = @JoinColumn(name = "roles_name"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"partner_admin_user_id", "roles_name"})
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
    public String getUsername() {
        return email;
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
