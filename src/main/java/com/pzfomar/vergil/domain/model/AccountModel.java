package com.pzfomar.vergil.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AccountModel {
    private String id;
    private String facebookId;
    private String email;
    private String password;
    protected LocalDateTime creationDate;
    protected LocalDateTime lastModifiedDate;
}
