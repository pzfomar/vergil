package com.pzfomar.vergil.infrastructure.mongo.mapper;

import com.pzfomar.vergil.domain.model.TermConditionModel;
import com.pzfomar.vergil.infrastructure.mongo.entity.TermConditionEntity;

public class TermConditionMapper {
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
