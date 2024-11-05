package com.pzfomar.vergil.model.h2.advertising;

import com.pzfomar.vergil.model.domain.advertising.AdvertisingEntity;

public class AdvertisingH2Mapper {
	private AdvertisingH2Mapper() {
		throw new IllegalStateException("AdvertisingMapper class");
	}

	public static AdvertisingEntity toModel(AdvertisingH2Entity data) {
		return AdvertisingEntity.builder()
				.version(data.getVersion())
				.id(data.getId())
				.content(data.getContent())
				.creationDate(data.getCreationDate())
				.lastModifiedDate(data.getLastModifiedDate())
				.build();
	}

	public static AdvertisingH2Entity toEntity(AdvertisingEntity data) {
		return AdvertisingH2Entity.builder()
				.version(data.getVersion())
				.id(data.getId())
				.content(data.getContent())
				.creationDate(data.getCreationDate())
				.lastModifiedDate(data.getLastModifiedDate())
				.build();
	}
}
