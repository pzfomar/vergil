package com.pzfomar.vergil.model.h2.termcondition;

import com.pzfomar.vergil.model.domain.termcondition.TermConditionEntity;

public class TermConditionH2Mapper {
	private TermConditionH2Mapper() {
		throw new IllegalStateException("TermConditionMapper class");
	}

	public static TermConditionEntity toModel(TermConditionH2Entity data) {
		return TermConditionEntity.builder()
				.version(data.getVersion())
				.id(data.getId())
				.content(data.getContent())
				.creationDate(data.getCreationDate())
				.lastModifiedDate(data.getLastModifiedDate())
				.build();
	}

	public static TermConditionH2Entity toEntity(TermConditionEntity data) {
		return TermConditionH2Entity.builder()
				.version(data.getVersion())
				.id(data.getId())
				.content(data.getContent())
				.creationDate(data.getCreationDate())
				.lastModifiedDate(data.getLastModifiedDate())
				.build();
	}
}
