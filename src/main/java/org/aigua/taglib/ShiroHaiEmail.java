package org.aigua.taglib;
 
import java.io.IOException;
 
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import org.apache.log4j.Logger;
 
import org.aigua.domain.User;

public class ShiroHaiEmail extends TagSupport {
     
	private static final Logger log = Logger.getLogger(ShiroHaiUserId.class.getName());
	
    @Override
    public int doStartTag() throws JspException {
         
        try {
 
           	JspWriter out = pageContext.getOut();
			
			Subject subject = SecurityUtils.getSubject();
 			Session session = subject.getSession();
			
			User user = (User) session.getAttribute("user");
			
			if(user != null) out.println(user.getEmail());	
			
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

}