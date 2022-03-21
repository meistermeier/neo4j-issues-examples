package com.example.gh2505;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class User 
{
	@Id
	@GeneratedValue
	private Long userId;
	
	private final Boolean enabled;

	public User(Boolean enabled) 
	{
		super();
		this.enabled = enabled;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public Long getUserId() {
		return userId;
	}
}
