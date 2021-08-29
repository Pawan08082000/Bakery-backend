package com.bakery.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection="roles")
public class Role {
	@Id
	private String roleid;
	RoleType roleType;
	public Role(RoleType roleType) {
		super();
		this.roleType = roleType;
	}
}
