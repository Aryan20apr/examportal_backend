package com.aryan.examportal_backend.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserRoleDTO {

	private Long userRoleId;

	private RoleDTO role;
}
