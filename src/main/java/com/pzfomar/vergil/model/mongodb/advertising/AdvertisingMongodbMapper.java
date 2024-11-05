package com.pzfomar.vergil.model.mongodb.advertising;

import com.pzfomar.vergil.model.domain.advertising.AdvertisingEntity;

public class AdvertisingMongodbMapper {
	private AdvertisingMongodbMapper() {
		throw new IllegalStateException("AdvertisingMapper class");
	}

	public static AdvertisingEntity toModel(AdvertisingMongodbEntity data) {
		return AdvertisingEntity.builder()
				.version(data.getVersion())
				.id(data.getId())
				.content(data.getContent())
				.creationDate(data.getCreationDate())
				.lastModifiedDate(data.getLastModifiedDate())
				.build();
	}

	public static AdvertisingMongodbEntity toEntity(AdvertisingEntity data) {
		return AdvertisingMongodbEntity.builder()
				.version(data.getVersion())
				.id(data.getId())
				.content(data.getContent())
				.creationDate(data.getCreationDate())
				.lastModifiedDate(data.getLastModifiedDate())
				.build();
	}
}
