<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@include file="header.jsp" %>



        <div class="wrapper">
            <!-- Sidebar Holder -->
            <nav id="sidebar">
                <div class="sidebar-header">
                    <img class="home" src="<c:url value="/static/images/splitwiselogin.jpg"/>"/>
                </div>

                <ul class="list-unstyled components">
                    
                    <li>
                        <a href="/SplitwiseApp/friends">Friends</a>
                        
                    </li>
                    <li>
                        <a href="/SplitwiseApp/list" >People you may know</a>
                        
                    </li>
                    <li class="active">
                        <a href="/SplitwiseApp/invitefriends">Invite Friends</a>
                    </li>
                    <li>
                        <a href="/SplitwiseApp/notifications">Friend Requests</a>
                    </li>
                    <li>
                        <a href="/SplitwiseApp/transactions">My Transactions</a>
                    </li>
                    <li>
                        <a href="/SplitwiseApp/expenselist">My Expenses</a>
                    </li>
                    
                    
                </ul>

                
            </nav>

            <div id="content">

                <nav class="navbar navbar-default">
                    <div class="container-fluid">

                        <div class="navbar-header">
                                <h4>Dear <strong>${loggedinuser}</strong>, Welcome to SplitWise App
                        </div>

                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav navbar-right">
                                <h4><a href="<c:url value="/logout" />">Logout</a></h4>
                            </ul>
                        </div>
                    </div>
                </nav>

            
            </div>
            
            <div class="generic-container">
		
                        

				<form class="login100-form validate-form"  method="POST">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<span class="login100-form-title">
						Enter Email to invite friends
					</span>
					
					<div class="wrap-input100 validate-input" data-validate = "Valid Email is required: ex@abc.xyz">
						<input style="background-color: white;" class="input100" type="text" name="email" placeholder="Email">
						<!--<span class="focus-input100"></span>-->
						<span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
					</div>

					
					
					<div class="container-login100-form-btn">
						<button type = "submit" class="login100-form-btn">
							Invite
						</button>
					</div>


				</form>	
		<%-- <sec:authorize access="hasRole('ADMIN')">
		 	<div class="well">
		 		<a href="<c:url value='/newuser' />">Add New User</a>
		 	</div>
	 	</sec:authorize> --%>
   	</div>
        </div>





<%@include file="footer.jsp" %>
