package com.aryan.examportal_backend.payload;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.aryan.examportal_backend.model.Role;
import com.aryan.examportal_backend.model.User;

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
