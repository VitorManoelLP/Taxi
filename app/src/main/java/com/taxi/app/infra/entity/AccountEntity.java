package com.taxi.app.infra.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.taxi.app.domain.enums.AccountType;
import com.taxi.app.domain.enums.Roles;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity implements UserDetails {

    @Id
    private UUID id;

    @Column(name = "image_path")
    private String imagePath;

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @Column(name = "account_type")
    private AccountType accountType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private final Set<UserRolesEntity> authorities = new HashSet<>();

    public static AccountEntity of(UUID id) {
        final AccountEntity accountEntity = new AccountEntity();
        accountEntity.id = id;
        return accountEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream().map(UserRolesEntity::getRoles).toList();
    }

    public void addAuthorityBase() {
        UserRolesEntity rolesEntity = new UserRolesEntity(null, RolesEntity.of(Roles.USER), this);
        authorities.add(rolesEntity);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
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
}
