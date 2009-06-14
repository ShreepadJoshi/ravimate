package src.springapp.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class HelloController implements Controller{
	private final Log _log = LogFactory.getLog(this.getClass().getName());
	
	public ModelAndView handleRequest(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		_log.info("rendering View");
		return new ModelAndView("hello.jsp");
	}
}
