package com.pzfomar.vergil.infrastructure.db.mongo.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Document("accounts")
public class AccountEntity {
    @Id
    private String id;
    private String facebookId;
    private String email;
    private String password;
    @CreatedDate
    protected LocalDateTime creationDate;
    @LastModifiedDate
    protected LocalDateTime lastModifiedDate;
}
