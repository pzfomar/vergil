package com.pzfomar.vergil.model.h2.auth;

import java.util.stream.Collectors;

import com.pzfomar.vergil.model.domain.auth.AuthEntity;
import com.pzfomar.vergil.model.h2.advertising.AdvertisingH2Mapper;
import com.pzfomar.vergil.model.h2.termcondition.TermConditionH2Mapper;

public class AuthH2Mapper {
	private AuthH2Mapper() {
		throw new IllegalStateException("AuthMapper class");
	}

	public static AuthEntity toModel(AuthH2Entity data) {
		return AuthEntity.builder()
				.version(data.getVersion())
				.id(data.getId())
				.email(data.getEmail())
				.password(data.getPassword())
				.advertisings(data.getAdvertisings()
						.stream()
						.map(AdvertisingH2Mapper::toModel)
						.collect(Collectors.toList()))
				.termsConditions(data.getTermsConditions()
						.stream()
						.map(TermConditionH2Mapper::toModel)
						.collect(Collectors.toList()))
				.status(data.getStatus())
				.rol(data.getRol())
				.creationDate(data.getCreationDate())
				.lastModifiedDate(data.getLastModifiedDate())
				.deleteDate(data.getDeleteDate())
				.build();
	}

	public static AuthH2Entity toEntity(AuthEntity data) {
		return AuthH2Entity.builder()
				.version(data.getVersion())
				.id(data.getId())
				.email(data.getEmail())
				.password(data.getPassword())
				.advertisings(data.getAdvertisings()
						.stream()
						.map(AdvertisingH2Mapper::toEntity)
						.collect(Collectors.toList()))
				.termsConditions(data.getTermsConditions()
						.stream()
						.map(TermConditionH2Mapper::toEntity)
						.collect(Collectors.toList()))
				.status(data.getStatus())
				.rol(data.getRol())
				.creationDate(data.getCreationDate())
				.lastModifiedDate(data.getLastModifiedDate())
				.deleteDate(data.getDeleteDate())
				.build();
	}
}
