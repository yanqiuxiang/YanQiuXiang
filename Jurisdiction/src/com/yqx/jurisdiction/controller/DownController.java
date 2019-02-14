package com.yqx.jurisdiction.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownController extends CotrollerBase{
	
	
	@RequestMapping(value="/down", method=RequestMethod.GET)
	public ModelAndView adminNodeView(HttpServletRequest request,HttpSession session){
		
		ModelAndView mav = new ModelAndView("/frame/down");
		return mav;
	}
	
}
