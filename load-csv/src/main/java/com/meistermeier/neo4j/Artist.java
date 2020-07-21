package com.meistermeier.neo4j;

import jdk.nashorn.internal.runtime.logging.Logger;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * @author Gerrit Meier
 */
@NodeEntity
public class Artist {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private Integer year;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override public String toString() {
		return "Artist{" +
			"id=" + id +
			", name='" + name + '\'' +
			", year=" + year +
			'}';
	}
}
