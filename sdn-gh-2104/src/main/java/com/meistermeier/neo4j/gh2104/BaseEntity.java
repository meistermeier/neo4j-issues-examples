package com.meistermeier.neo4j.gh2104;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

/**
 * @author Gerrit Meier
 */
public abstract class BaseEntity {

	@Id @GeneratedValue
	private Long id;

	private String code;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
