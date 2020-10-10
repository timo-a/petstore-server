package com.example.petstore.backend.api.implementation;

import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.util.MultiValueMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.example.petstore.Session;
import com.example.petstore.backend.api.ApiUtil;
import com.example.petstore.backend.api.UserApi;
import com.example.petstore.backend.api.model.User;
import com.example.petstore.backend.db.UserBE;
import com.example.petstore.backend.db.UserRepository;
import com.example.petstore.backend.db.mappers.UserMapper;
import com.example.petstore.backend.db.mappers.UserMapperImpl;

@RestController
public class UserController implements UserApi {

	@Autowired
	UserRepository ur;
	
	@Autowired
	com.example.petstore.backend.auth.UserAuthRepository authR;
	
	@Resource(name = "getSession")
	Session session;
	
	private final UserMapper userMapper = new UserMapperImpl();
	
	//TODO find out how to get the true value
	private String sessionTimeout = "30m";
	
	@Override
	public ResponseEntity<User> createUser(@Valid User user) {
		
        //return new ResponseEntity<User>(HttpStatus.OK);

		//TODO no response defined in spec for executing request without being logged in - does logged in refer to api key?
		if(!session.getLoggedIn()) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}

		UserBE userBE = userMapper.toBE(user);
		ur.save(userBE);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	@Override
	public ResponseEntity<User> createUsersWithListInput(@Valid List<User> user) {
		for(User u : user) {//TODO return type is wrong, better return list of added usernames/number of added users
			ur.save(userMapper.toBE(u));
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<String> loginUser(@Valid String username, @Valid String password) {
		Optional<String> oEncryptedPassword = authR.getEncryptedPassword(username);
		PasswordEncoder pe = new BCryptPasswordEncoder();
		ResponseEntity<String> r;
		if(oEncryptedPassword.isEmpty()) {
			r = new ResponseEntity<String>("Invalid username/password supplied", HttpStatus.BAD_REQUEST);
		} else if(pe.matches(password, oEncryptedPassword.get())) {
			session.setLoggedIn();
			MultiValueMap<String, String> headers = new HttpHeaders();
			headers.add("X-Rate-Limit", Integer.MAX_VALUE + "");
			
			//this assumes that session timeout is due to inactivity and is therefore reset by this request
			Duration d = getSessionTimeout();			
			Temporal timeouttime = d.addTo(Instant.now());
			String timeoutstring = DateTimeFormatter.ISO_INSTANT.format(timeouttime);
			
			headers.add("X-Expires-After", timeoutstring);

			r = new ResponseEntity<String>("Log in successful", HttpStatus.OK);			
		} else {
			r = new ResponseEntity<String>("Invalid username/password supplied", HttpStatus.BAD_REQUEST);			
		}

		return r;
	}
	
	private Duration getSessionTimeout() {
		Pattern r = Pattern.compile("(\\d+)([hms]?)");
		Matcher m = r.matcher(sessionTimeout);
		Long t = Long.valueOf(m.group(1));
		if(m.find()) {
			switch(m.group(2)) {
			case "h":
				return Duration.ofHours(t);
			case "m":
				return Duration.ofMinutes(t);
			default:
				return Duration.ofSeconds(t);
			}
		} else {
			throw new InternalError("sessionTimeout cannot be parsed");
		}
	}
	
 	@Override
	public ResponseEntity<Void> logoutUser() {
		session.setLoggedOut();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<User> getUserByName(String username) {
		if(!validateUsername(username)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} 
		
		Optional<UserBE> results = ur.findFirstByUsername(username);
		
		if (results.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(userMapper.fromBE(results.get()), HttpStatus.OK);
	}
	
	private boolean validateUsername(String userName) {
		Pattern p = Pattern.compile("\\w+");//
		return p.matcher(userName).find();
	}
	
	@Override
	public ResponseEntity<Void> updateUser(String username, @Valid User user) {
		
		if(!session.getLoggedIn()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return ur.updateExistingByUsername(username, userMapper.toBE(user))
				? new ResponseEntity<>(HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@Override
	public ResponseEntity<Void> deleteUser(String username) {
		if(!validateUsername(username)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} 
				
		return ur.deleteExistingByUsername(username) 
				? new ResponseEntity<Void>(HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
}
