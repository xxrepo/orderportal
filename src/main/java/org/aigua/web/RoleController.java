package org.aigua.web;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.HashMap;
import java.util.Date; 
import java.util.List;
import java.util.ArrayList;

import org.aigua.domain.Role;
import org.aigua.dao.RoleDao;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.session.Session;

import static org.aigua.common.ShiroHaiConstants.*;

import org.aigua.domain.User;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	private static final String TAG = "ROLE_CONTROLLER";
	private static final Logger log = Logger.getLogger(RoleController.class.getName());
	
	@Autowired
	private RoleDao roleDao;	
	
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(ModelMap model, 
						 HttpServletRequest request,
						 final RedirectAttributes redirect){
		
		if (!SecurityUtils.getSubject().hasRole(ADMIN_ROLE)){
			if(SecurityUtils.getSubject().isAuthenticated()){
				redirect.addFlashAttribute("message", "You must be an admin to create new roles");
				Session session = SecurityUtils.getSubject().getSession();
				User user = (User)session.getAttribute("user");
				return "redirect:/app/user/show/" + user.getId();
			}else{	
				redirect.addFlashAttribute("message", "You must be an admin to create new roles. Please login");
				return "redirect:/app/auth/login";
			}
		}
	
		model.addAttribute("title", "Create New Role");
		model.addAttribute("addRoleActive", "active");
		return "role/create";
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveRole(ModelMap model,
						   HttpServletRequest request,
					   	   final RedirectAttributes redirect){
		
		if (!SecurityUtils.getSubject().hasRole(ADMIN_ROLE)){
			
			if(SecurityUtils.getSubject().isAuthenticated()){
				redirect.addFlashAttribute("message", "You must be an admin to create new roles");
				Session session = SecurityUtils.getSubject().getSession();
				User user = (User)session.getAttribute("user");
				return "redirect:/app/user/show/" + user.getId();
			}else{	
				redirect.addFlashAttribute("message", "You must be an admin to create new roles. Please login");
				return "redirect:/app/auth/login";
			}
		}
		
		String name = request.getParameter("name");
		
		Role role = new Role();
		role.setName(name);
		
		roleDao.save(role);		
		Role savedRole = roleDao.findByName(name);
		
		redirect.addFlashAttribute("role", savedRole);
		redirect.addFlashAttribute("message", "Successfully saved role " + savedRole.getName());
		
		return "redirect:/app/role/list";
	}
	
	
	
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String edit(ModelMap model,
					   HttpServletRequest request, 
					   @PathVariable String id,
					   final RedirectAttributes redirect){
		
		if (!SecurityUtils.getSubject().hasRole(ADMIN_ROLE)){
			if(SecurityUtils.getSubject().isAuthenticated()){
				redirect.addFlashAttribute("message", "You must be an admin to edit roles");
				Session session = SecurityUtils.getSubject().getSession();
				User user = (User)session.getAttribute("user");
				return "redirect:/app/user/show/" + user.getId();
			}else{	
				redirect.addFlashAttribute("message", "You must be an admin to edit roles. Please login");
				return "redirect:/app/auth/login";
			}
		}

		Role role = roleDao.findById(Integer.parseInt(id));
		model.addAttribute("title", "Edit Role : " + id);
		model.addAttribute("role", role);
		
		return "role/edit";
	}	
	
	
	
	
	@RequestMapping(value="/show/{id}", method=RequestMethod.GET)
	public String show(ModelMap model,
					   HttpServletRequest request, 
					   @PathVariable String id){
				
		Role role = roleDao.findById(Integer.parseInt(id));
		model.addAttribute("title", "Show Role : " + id);
		model.addAttribute("role", role);
		
		return "role/show";
	}	
	

	
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String updateRole(ModelMap model, 
		 					 HttpServletRequest request,
		  					 final RedirectAttributes redirect){
			
		if (!SecurityUtils.getSubject().hasRole(ADMIN_ROLE)){
			if(SecurityUtils.getSubject().isAuthenticated()){
				redirect.addFlashAttribute("message", "You must be an admin to update roles");
				Session session = SecurityUtils.getSubject().getSession();
				User user = (User)session.getAttribute("user");
				return "redirect:/app/user/show/" + user.getId();
			}else{	
				redirect.addFlashAttribute("message", "You must be an admin to update roles. Please login");
				return "redirect:/app/auth/login";
			}
		}
		
  		String id = request.getParameter("id");
  		String name = request.getParameter("name");
		
		Role role = new Role();
		role.setId(Integer.parseInt(id));
		role.setName(name);
		roleDao.update(role);
		
		Role updatedRole = roleDao.findById(Integer.parseInt(id));
		
		redirect.addFlashAttribute("role", updatedRole);
		redirect.addFlashAttribute("message", "Successfully updated role " + id);
		
		return "redirect:/app/role/list";
	}
	
	
		
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public String deleteRole(ModelMap model, 
						     @PathVariable String id,
		  					 final RedirectAttributes redirect){
			
		if (!SecurityUtils.getSubject().hasRole(ADMIN_ROLE)){
			if(SecurityUtils.getSubject().isAuthenticated()){
				redirect.addFlashAttribute("message", "You must be an admin to delete roles");
				Session session = SecurityUtils.getSubject().getSession();
				User user = (User)session.getAttribute("user");
				return "redirect:/app/user/show/" + user.getId();
			}else{	
				redirect.addFlashAttribute("message", "You must be an admin to delete roles. Please login");
				return "redirect:/app/auth/login";
			}
		}
		
		Role role = roleDao.findById(Integer.parseInt(id));	
		roleDao.delete(role.getId());
		
		
		redirect.addFlashAttribute("role", role);
		redirect.addFlashAttribute("message", "Successfully deleted role " + id);
		
		return "redirect:/app/role/list";
		
	}	
	
	
	
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(ModelMap model, 
				       HttpServletRequest request, 
					   @RequestParam(value="offset", required = false ) String offset,
					   @RequestParam(value="max", required = false ) String max,
					   @RequestParam(value="page", required = false ) String page,
					   final RedirectAttributes redirect){
		
		Map<String, ?> flash = redirect.getFlashAttributes();
		
		if(page == null){
			page = "1";
		}						
		
		List<Role> roles;
		
		if(offset != null && !flash.isEmpty()) {
			int m = RESULTS_PER_PAGE;
			if(max != null){
				m = Integer.parseInt(max);
			}
			int o = Integer.parseInt(offset);
			roles = roleDao.findAllOffset(m, o);	
		}else{
			roles = roleDao.findAll();	
		} 
		
		int count = roleDao.count();
		
		model.addAttribute("roles", roles);
		model.addAttribute("total", count);
		
		model.addAttribute("title", "List Roles");
		model.addAttribute("resultsPerPage", RESULTS_PER_PAGE);
		model.addAttribute("activePage", page);
		
		return "role/list";
	}
	
	
}