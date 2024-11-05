package com.pzfomar.vergil.model.domain.auth;

import java.time.LocalDateTime;
import java.util.List;

import com.pzfomar.vergil.model.domain.advertising.AdvertisingEntity;
import com.pzfomar.vergil.model.domain.termcondition.TermConditionEntity;
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
public class AuthEntity {
	private Long version;

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

	private LocalDateTime creationDate;

	private LocalDateTime lastModifiedDate;

	@Builder.Default
	private LocalDateTime deleteDate = null;

	public boolean isNew() {
		return id == null;
	}
}
