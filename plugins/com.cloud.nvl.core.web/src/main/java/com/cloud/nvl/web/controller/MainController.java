/**
 * 
 */
package com.cloud.nvl.web.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cloud.nvl.core.service.inter.IOperateService;
import com.cloud.nvl.core.service.inter.IRequestService;
 
/**
 * @author Murphy
 * 
 */
@Controller
//@RequestMapping("/hello")
public class MainController {

	@Autowired
//	private IOperateService operateService;
	private IRequestService requestService;
 
	@RequestMapping(value = {"/hello", "/test"})
//	@RequestMapping("/test")
	public ModelAndView hello() {
//		BundleContext context = FrameworkUtil.getBundle(this.getClass())
//				.getBundleContext();
//		ServiceReference<?> serviceReference = context
//				.getServiceReference(IOperateService.class.getName());
//		IOperateService operateService = (IOperateService) context
//				.getService(serviceReference);
//		String str = operateService.getDate();
//		String str = requestService.getRequestPara();
		ArrayList<String> str =(ArrayList)requestService.getArrayPara();
		String out="";
		 StringBuffer sb=new StringBuffer();
		for(int i=0;i<str.size();i++){
		 out=str.get(i);
		
		 sb.append("<font color='red'><li>"+out+"</li></font>"+"  ");
		 
		}
//		return new ModelAndView("hello", "date", str);
		return new ModelAndView("hello", "flag", sb==null?"nobody":sb);
	}
	
//	@RequestMapping()
//	public @ResponseBody String test() {
//		return "test/hello";
//		
//	}
}
