package com.pzfomar.vergil.infrastructure.config.security;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "auth")
public class AuthEntity implements Persistable<String> {
    @Version
    private Long version;

    @Id
    private String id;

    private String username;

    private String password;

    @Builder.Default()
    private boolean active = true;

    @Builder.Default()
    private List<String> roles = List.of("USER");

    @CreatedDate
    protected LocalDateTime creationDate;
    
    @LastModifiedDate
    protected LocalDateTime lastModifiedDate;
    
    @Override
    public boolean isNew() {
        return id == null;
    }
}
