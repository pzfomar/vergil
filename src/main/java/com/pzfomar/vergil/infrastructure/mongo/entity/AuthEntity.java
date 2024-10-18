package com.pzfomar.vergil.infrastructure.mongo.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import com.pzfomar.vergil.domain.enums.RolEnum;
import com.pzfomar.vergil.domain.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "auth")
public class AuthEntity implements Persistable<String> {
    @Version
    private Long version;

    @Id
    private String id;

    private String email;
    
    private String password;
    
    @Builder.Default
    private List<AdvertisingEntity> advertisings = List.of();

    @Builder.Default
    private List<TermConditionEntity> termsConditions = List.of();

    @Builder.Default
    private StatusEnum status = StatusEnum.VALIDATE;

    @Builder.Default
    private RolEnum rol = RolEnum.USER;

    @CreatedDate
    private LocalDateTime creationDate;
    
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    
    @Builder.Default
    private LocalDateTime deleteDate = null;
    
    @Override
    public boolean isNew() {
        return id == null;
    }
}
