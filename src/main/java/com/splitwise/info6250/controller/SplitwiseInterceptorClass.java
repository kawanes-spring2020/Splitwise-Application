package com.splitwise.info6250.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SplitwiseInterceptorClass extends HandlerInterceptorAdapter {

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		if(request.getMethod().equals("POST")) {
			System.out.println("Insidepost");
			Map<String, String[]> paramMap = request.getParameterMap();
            for(String param : paramMap.keySet()){
               String[] paramValues = paramMap.get(param);
               for(int i=0;i<paramValues.length;i++){
                   System.out.println(paramValues[i]);
                   if(checkScripting(paramValues[i]) || checkSQLInjection(paramValues[i])){
                	   response.getWriter().append("Input values are not permitted for this operation!!");
                	   return false;  
                   }
               }
               
            }
			
		}
		return true;
	}
	
	
	private boolean checkScripting(String value) {
        if (value != null) {
            return (value.contains("<script>") || value.contains("alert") || value.contains("</script>")  || value.matches("<script>(.*?)</script>") || value.matches("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'") || value.matches("\"<script(.*?)>\""));
            
        }
        return false;
    }  
    
    private boolean checkSQLInjection(String sql) {
        if (sql != null) {
            return (sql.contains(";") || sql.contains("'") || sql.contains("--") || sql.contains("/*") || sql.contains("*/") || sql.contains("xp_"));
        }
        return false;
    }
}
