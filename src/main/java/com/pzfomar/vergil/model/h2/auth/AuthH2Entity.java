package com.pzfomar.vergil.model.h2.auth;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import com.pzfomar.vergil.model.h2.advertising.AdvertisingH2Entity;
import com.pzfomar.vergil.model.h2.termcondition.TermConditionH2Entity;
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
@Table(name = "auth")
public class AuthH2Entity implements Persistable<String> {
    @Version
    private Long version;

    @Id
    private String id;

    private String email;
    
    private String password;
    
    @Builder.Default
    private List<AdvertisingH2Entity> advertisings = List.of();

    @Builder.Default
    private List<TermConditionH2Entity> termsConditions = List.of();

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
