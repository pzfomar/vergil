package com.pzfomar.vergil.infrastructure.db.mongodb.mapper;

import com.pzfomar.vergil.domain.model.AdvertisingModel;
import com.pzfomar.vergil.infrastructure.db.mongodb.entity.AdvertisingEntity;

public class AdvertisingMapper {
	private AdvertisingMapper() {
		throw new IllegalStateException("AdvertisingMapper class");
	}

	public static AdvertisingModel toModel(AdvertisingEntity data) {
		return AdvertisingModel.builder()
				.version(data.getVersion())
				.id(data.getId())
				.content(data.getContent())
				.creationDate(data.getCreationDate())
				.lastModifiedDate(data.getLastModifiedDate())
				.build();
	}

	public static AdvertisingEntity toEntity(AdvertisingModel data) {
		return AdvertisingEntity.builder()
				.version(data.getVersion())
				.id(data.getId())
				.content(data.getContent())
				.creationDate(data.getCreationDate())
				.lastModifiedDate(data.getLastModifiedDate())
				.build();
	}
}
