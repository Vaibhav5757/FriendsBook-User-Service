package com.friendsbook.user.util;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JustEmailBody {
	
	@NotBlank
	private String email;
}
