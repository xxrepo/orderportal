package org.aigua.web;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;

import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.AuthenticationException;
import java.io.IOException;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import sun.misc.BASE64Decoder;

import org.springframework.ui.ModelMap;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.HashMap;
import java.util.Date; 
import java.util.List;

import org.aigua.domain.User;
import org.aigua.dao.UserDao;

import org.aigua.domain.Role;
import org.aigua.dao.RoleDao;

import static org.aigua.common.ShiroHaiConstants.*;

@Controller
@RequestMapping("/auth")
public class AuthController{

	private static final String TAG = "AUTH_CONTROLLER";
	private static final Logger log = Logger.getLogger(AuthController.class.getName());

	@Autowired
	private UserDao userDao;
		
	@Autowired
	private RoleDao roleDao;	
	
	
	@RequestMapping(value="/registration", method=RequestMethod.GET)
	public String login(ModelMap model,
		  				final RedirectAttributes redirect){
			
		model.addAttribute("title", "Register");
		return "auth/registration";
	}		
	

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String login(ModelMap model,
					    HttpServletRequest request,
		  				final RedirectAttributes redirect){
		
			

		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("passwordHash");
		
		// RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		// Object salt = rng.nextBytes();
		//Now hash the plain-text password with the random salt and multiple
		//iterations and then Base64-encode the value (requires less space than Hex):
		// Sha256Hash p = new Sha256Hash(password, username, 1024);
		// String encodedPassword = p.toBase64();
		
		
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setUsername(username);
		user.setPasswordHash(password);
		// user.setPasswordSalt(salt);
		
				
		userDao.save(user);
		User savedUser = userDao.findByUsername(username);
		
		Role defaultRole = roleDao.findByName(CUSTOMER_ROLE);
		userDao.saveUserRole(savedUser.getId(), defaultRole.getId());
		
		userDao.saveUserPermission(savedUser.getId(), USER_EDIT + DELIM + savedUser.getId());
		userDao.saveUserPermission(savedUser.getId(), USER_UPDATE + DELIM + savedUser.getId());
		
		redirect.addFlashAttribute("user", user);
		redirect.addFlashAttribute("message", "You have successfully registered!  Log in with you new account credentials");

		return "redirect:/app/auth/login";
	}	
	
	
		
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(ModelMap model){
			
		model.addAttribute("title", "Login");
		
		return "auth/login";
	}	
	
	
	
	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public String authenticate(ModelMap model,	
							   HttpServletRequest request, 
							   final RedirectAttributes redirect, 
							   @RequestBody String credsString){
		
		Map<String, String> creds = parse(credsString);
		model.addAttribute("username", creds.get("username"));
		
		try{
			
			
			UsernamePasswordToken token = new UsernamePasswordToken( creds.get("username"), creds.get("password"));
			// token.setRememberMe(true);

			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			
			User user = userDao.findByUsername(creds.get("username"));
			
			Session session = subject.getSession();
			session.setAttribute( "user", user );

			
		} catch ( UnknownAccountException uae ) { 
			uae.printStackTrace();
		} catch ( IncorrectCredentialsException ice ) {
			ice.printStackTrace();
		} catch ( LockedAccountException lae ) { 
			lae.printStackTrace();
		} catch ( ExcessiveAttemptsException eae ) { 
			eae.printStackTrace();
		} catch ( AuthenticationException ae ) {
			ae.printStackTrace();
		} 

		return "auth/success";
	}	
	
	
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(ModelMap model,	
						 HttpServletRequest request, 
						 final RedirectAttributes redirect){
	
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();		
		model.addAttribute("message", "Successfully logged out");
		
		return "redirect:/app/auth/login";
	}
	
	
	private Map<String, String> parse(String text){
		Map<String, String> map = new HashMap<String, String>();
		for(String keyValue : text.split(" *& *")) {
		   String[] pairs = keyValue.split(" *= *", 2);
		   map.put(pairs[0], pairs.length == 1 ? "" : pairs[1]);
		}
		return map;
	}
	
}


















