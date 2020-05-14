<%-- 
    Document   : signup
    Created on : Mar 29, 2020, 12:20:07 AM
    Author     : Shubham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@include file="header.jsp" %>

<div class="limiter">
    		<div class="container-login100">
                        <div class="wrap-login100">
                            
				<div class="login100-pic js-tilt" data-tilt>
                                    <img class="img" src="<c:url value="/resources/images/splitwiselogin.jpg"/>" alt="IMG">
				</div>

				<form class="login100-form validate-form" method="post" modelAttribute="user">
					<span class="login100-form-title">
						Splitwise Sign up Form
					</span>
					
					<form:hidden path="id" />
					
					<div class="wrap-input100 validate-input" data-validate = "First Name is required: ex@abc.xyz">
						<input class="input100" type="text" name="firstName" placeholder="First Name">
						<!--<span class="focus-input100"></span>-->
						<span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
					</div>
                                        
                                        <div class="wrap-input100 validate-input" data-validate = "Last Name is required: ex@abc.xyz">
						<input class="input100" type="text" name="lastName" placeholder="Last Name">
						<!--<span class="focus-input100"></span>-->
						<span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
					</div>
                                    
                                        <div class="wrap-input100 validate-input" data-validate = "Valid Email is required: ex@abc.xyz">
						<input class="input100" type="text" name="email" placeholder="Email">
						<!--<span class="focus-input100"></span>-->
						<span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
					</div>

					<div class="wrap-input100 validate-input" data-validate = "Password is required">
						<input class="input100" type="password" name="password" placeholder="Password">
						<!--<span class="focus-input100"></span>-->
						<span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
					</div>
					
					<div class="wrap-input100 validate-input" data-validate = "Password is required">
						<input class="input100" type="text" name="ssoId" placeholder="Username">
						<!--<span class="focus-input100"></span>-->
						<span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
					</div>
					
					<div class="container-login100-form-btn">
						<button type = "submit" class="login100-form-btn">
							Sign Up
						</button>
					</div>


				</form>
			</div>
		</div>
	</div>
	
<%@include file="footer.jsp" %>
