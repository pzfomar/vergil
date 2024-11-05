package com.pzfomar.vergil.model.mongodb.auth;

import java.util.stream.Collectors;

import com.pzfomar.vergil.model.domain.auth.AuthEntity;
import com.pzfomar.vergil.model.mongodb.advertising.AdvertisingMongodbMapper;
import com.pzfomar.vergil.model.mongodb.termcondition.TermConditionMongodbMapper;

public class AuthMongodbMapper {
	private AuthMongodbMapper() {
		throw new IllegalStateException("AuthMapper class");
	}

	public static AuthEntity toModel(AuthMongodbEntity data) {
		return AuthEntity.builder()
				.version(data.getVersion())
				.id(data.getId())
				.email(data.getEmail())
				.password(data.getPassword())
				.advertisings(data.getAdvertisings()
						.stream()
						.map(AdvertisingMongodbMapper::toModel)
						.collect(Collectors.toList()))
				.termsConditions(data.getTermsConditions()
						.stream()
						.map(TermConditionMongodbMapper::toModel)
						.collect(Collectors.toList()))
				.status(data.getStatus())
				.rol(data.getRol())
				.creationDate(data.getCreationDate())
				.lastModifiedDate(data.getLastModifiedDate())
				.deleteDate(data.getDeleteDate())
				.build();
	}

	public static AuthMongodbEntity toEntity(AuthEntity data) {
		return AuthMongodbEntity.builder()
				.version(data.getVersion())
				.id(data.getId())
				.email(data.getEmail())
				.password(data.getPassword())
				.advertisings(data.getAdvertisings()
						.stream()
						.map(AdvertisingMongodbMapper::toEntity)
						.collect(Collectors.toList()))
				.termsConditions(data.getTermsConditions()
						.stream()
						.map(TermConditionMongodbMapper::toEntity)
						.collect(Collectors.toList()))
				.status(data.getStatus())
				.rol(data.getRol())
				.creationDate(data.getCreationDate())
				.lastModifiedDate(data.getLastModifiedDate())
				.deleteDate(data.getDeleteDate())
				.build();
	}
}
