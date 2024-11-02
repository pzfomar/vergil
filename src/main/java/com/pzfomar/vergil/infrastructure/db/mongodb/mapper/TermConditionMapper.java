package com.pzfomar.vergil.infrastructure.db.mongodb.mapper;

import com.pzfomar.vergil.domain.model.TermConditionModel;
import com.pzfomar.vergil.infrastructure.db.mongodb.entity.TermConditionEntity;

public class TermConditionMapper {
	private TermConditionMapper() {
		throw new IllegalStateException("TermConditionMapper class");
	}

	public static TermConditionModel toModel(TermConditionEntity data) {
		return TermConditionModel.builder()
				.version(data.getVersion())
				.id(data.getId())
				.content(data.getContent())
				.creationDate(data.getCreationDate())
				.lastModifiedDate(data.getLastModifiedDate())
				.build();
	}

	public static TermConditionEntity toEntity(TermConditionModel data) {
		return TermConditionEntity.builder()
				.version(data.getVersion())
				.id(data.getId())
				.content(data.getContent())
				.creationDate(data.getCreationDate())
				.lastModifiedDate(data.getLastModifiedDate())
				.build();
	}
}
