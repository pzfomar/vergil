package com.pzfomar.vergil.model.mongodb.termcondition;

import com.pzfomar.vergil.model.domain.termcondition.TermConditionEntity;

public class TermConditionMongodbMapper {
	private TermConditionMongodbMapper() {
		throw new IllegalStateException("TermConditionMapper class");
	}

	public static TermConditionEntity toModel(TermConditionMongodbEntity data) {
		return TermConditionEntity.builder()
				.version(data.getVersion())
				.id(data.getId())
				.content(data.getContent())
				.creationDate(data.getCreationDate())
				.lastModifiedDate(data.getLastModifiedDate())
				.build();
	}

	public static TermConditionMongodbEntity toEntity(TermConditionEntity data) {
		return TermConditionMongodbEntity.builder()
				.version(data.getVersion())
				.id(data.getId())
				.content(data.getContent())
				.creationDate(data.getCreationDate())
				.lastModifiedDate(data.getLastModifiedDate())
				.build();
	}
}
