package com.friendsbook.user.util;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PasswordChangeBody {
	
	@NotBlank
	private String email, password;
}
