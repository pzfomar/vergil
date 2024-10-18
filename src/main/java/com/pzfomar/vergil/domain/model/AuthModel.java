package com.pzfomar.vergil.domain.model;

import java.time.LocalDateTime;
import java.util.List;

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
public class AuthModel {
	private Long version;
	
	private String id;
	
	private String email;
	
	private String password;
	
	@Builder.Default
	private List<AdvertisingModel> advertisings = List.of();
	
	@Builder.Default
	private List<TermConditionModel> termsConditions = List.of();

    @Builder.Default
    private StatusEnum status = StatusEnum.VALIDATE;

    @Builder.Default
    private RolEnum rol = RolEnum.USER;

	private LocalDateTime creationDate;
	
	private LocalDateTime lastModifiedDate;
	
    @Builder.Default
    private LocalDateTime deleteDate = null;

	public boolean isNew() {
		return id == null;
	}
}
