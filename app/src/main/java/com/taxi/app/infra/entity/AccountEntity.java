package com.taxi.app.infra.entity;

import java.util.UUID;

import com.taxi.app.domain.enums.AccountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class AccountEntity {

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

    @Column(name = "account_type")
    private AccountType accountType;

    public static AccountEntity of(UUID id) {
        final AccountEntity accountEntity = new AccountEntity();
        accountEntity.id = id;
        return accountEntity;
    }

}
