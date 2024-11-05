package com.pzfomar.vergil.model.mongodb.auth;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import com.pzfomar.vergil.model.mongodb.advertising.AdvertisingMongodbEntity;
import com.pzfomar.vergil.model.mongodb.termcondition.TermConditionMongodbEntity;
import com.pzfomar.vergil.utility.enums.RolEnum;
import com.pzfomar.vergil.utility.enums.StatusEnum;

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
public class AuthMongodbEntity implements Persistable<String> {
    @Version
    private Long version;

    @Id
    private String id;

    private String email;
    
    private String password;
    
    @Builder.Default
    private List<AdvertisingMongodbEntity> advertisings = List.of();

    @Builder.Default
    private List<TermConditionMongodbEntity> termsConditions = List.of();

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
