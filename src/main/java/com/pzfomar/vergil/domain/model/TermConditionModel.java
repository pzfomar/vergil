package com.pzfomar.vergil.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TermConditionModel {
	private Long version;

	private String id;

	private String content;

	private LocalDateTime creationDate;

	private LocalDateTime lastModifiedDate;

	public boolean isNew() {
		return id == null;
	}
}
