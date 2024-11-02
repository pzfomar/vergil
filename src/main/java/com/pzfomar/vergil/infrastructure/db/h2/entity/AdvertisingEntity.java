package com.pzfomar.vergil.infrastructure.db.h2.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

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
@Table(name = "advertisings")
public class AdvertisingEntity implements Persistable<String> {
	@Version
	private Long version;

	@Id
	private String id;

	private String content;

	@CreatedDate
	private LocalDateTime creationDate;

	@LastModifiedDate
	private LocalDateTime lastModifiedDate;

	@Override
	public boolean isNew() {
		return id == null;
	}
}
