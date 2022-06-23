package com.example.community57088;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;

@Data
public abstract class AbstractEntity {
	@Id
	private long id;
	private long companyId;
	private String name;
}