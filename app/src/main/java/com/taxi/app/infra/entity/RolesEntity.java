package com.taxi.app.infra.entity;

import org.springframework.security.core.GrantedAuthority;

import com.taxi.app.domain.enums.Roles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "ROLES")
@Getter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class RolesEntity implements GrantedAuthority {

    private static final String DEFAULT_ROLE = "ROLE_USER";

    @Id
    private Long id;

    @Column(name = "authority")
    private String authority = DEFAULT_ROLE;

    public static RolesEntity of(Roles roles) {
        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.id = roles.getId();
        rolesEntity.authority = roles.getDescription();
        return rolesEntity;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

}
