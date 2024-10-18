package com.pzfomar.vergil.infrastructure.mongo.mapper;

import java.util.stream.Collectors;

import com.pzfomar.vergil.domain.model.AuthModel;
import com.pzfomar.vergil.infrastructure.mongo.entity.AuthEntity;

public class AuthMapper {
	public static AuthModel toModel(AuthEntity data) {
		return AuthModel.builder()
				.version(data.getVersion())
				.id(data.getId())
				.email(data.getEmail())
				.password(data.getPassword())
				.advertisings(data.getAdvertisings()
						.stream()
						.map(AdvertisingMapper::toModel)
						.collect(Collectors.toList()))
				.termsConditions(data.getTermsConditions()
						.stream()
						.map(TermConditionMapper::toModel)
						.collect(Collectors.toList()))
				.status(data.getStatus())
				.rol(data.getRol())
				.creationDate(data.getCreationDate())
				.lastModifiedDate(data.getLastModifiedDate())
				.deleteDate(data.getDeleteDate())
				.build();
	}

	public static AuthEntity toEntity(AuthModel data) {
		return AuthEntity.builder()
				.version(data.getVersion())
				.id(data.getId())
				.email(data.getEmail())
				.password(data.getPassword())
				.advertisings(data.getAdvertisings()
						.stream()
						.map(AdvertisingMapper::toEntity)
						.collect(Collectors.toList()))
				.termsConditions(data.getTermsConditions()
						.stream()
						.map(TermConditionMapper::toEntity)
						.collect(Collectors.toList()))
				.status(data.getStatus())
				.rol(data.getRol())
				.creationDate(data.getCreationDate())
				.lastModifiedDate(data.getLastModifiedDate())
				.deleteDate(data.getDeleteDate())
				.build();
	}
}
