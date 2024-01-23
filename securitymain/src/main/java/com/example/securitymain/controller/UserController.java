
package com.example.securitymain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.securitymain.entities.Users;
import com.example.securitymain.exception.UserException;
import com.example.securitymain.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private AuthenticationService authenticationService;
	@GetMapping("/helloUser")
	public String hello() {
		return "Hello User";
	}
	
	@GetMapping("/profile")
	public ResponseEntity<Users> getUserProfileHandler(@RequestHeader("Authorization") String jwt)
			throws UserException {

		Users user = authenticationService.findUserProfileByJwt(jwt);

		return new ResponseEntity<Users>(user, HttpStatus.ACCEPTED);
	}
}