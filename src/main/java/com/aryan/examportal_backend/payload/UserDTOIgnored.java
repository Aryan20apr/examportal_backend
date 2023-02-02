package com.aryan.examportal_backend.payload;


import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTOIgnored {

	private Long id;
	private String nickname;
	
	private String firstName;
	private String lastName;

	
	
}
