<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
                    <li>
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

            <!-- Page Content Holder -->
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
            
            <div class="generic-container4">
				<form:form class="login100-form1 validate-form" modelAttribute="expense" method="POST">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<form:input type="hidden" path="id" id="id"/>
					<div class="inline">
						With<strong><b> You</b></strong> and:
					</div>
					<div class="inline">
						<select class="selectpicker" id="friendsList"  name="friendsList" multiple data-live-search="true">
						  	<c:forEach items="${friends}" var="user">
			                    <option value="${user.friend_username}">${user.friend_username}</option>
			                </c:forEach>
						</select>
					</div>
					<hr>
					  
					
					<div style="width:350px;" class="wrap-input100 validate-input" data-validate = "Valid Email is required: ex@abc.xyz">
					<c:choose>
							<c:when test="${edit}">
								<form:input class="input100" type="text" path="description" name="description" placeholder="Description"/>
								<div class="has-error">
									<form:errors path="description" class="help-inline"/>
								</div>
							</c:when>
							<c:otherwise>
								<form:input class="input100" type="text" path="description" name="description" placeholder="Description"/>
								<div class="has-error">
									<form:errors path="description" class="help-inline"/>
								</div>
							</c:otherwise>
					</c:choose>
						<span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
					</div>
					
					<br>
					
					<div style="width:350px;" class="wrap-input100 validate-input" data-validate = "Valid Email is required: ex@abc.xyz">
					<c:choose>
							<c:when test="${edit}">
								<form:input class="input100" type="text" path="amount" name="amount" placeholder="Amount"/>
								<div class="has-error">
									<form:errors path="amount" class="help-inline"/>
								</div>
							</c:when>
							<c:otherwise>
								<form:input class="input100" type="text" path="amount" name="amount" placeholder="Amount"/>
								<div class="has-error">
									<form:errors path="amount" class="help-inline"/>
								</div>
							</c:otherwise>
					</c:choose>
						
						
						<span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
					</div>
					<hr>
					<div class="actiondiv">
						<div class="inline">Paid by</div> 
						
						<div class="inline">
							<select class="selectpicker" path="paid_by" id="paid_by" name="paid_by" data-live-search="true"  >
							  	<c:forEach items="${paidby}" var="user">
				                    <option value="${user}">YOU</option>
				                </c:forEach>
							</select>
						</div>
						<div class="inline"> and split<strong> Equally</strong></div>
						
						<div class="inline">
							<select class="selectpicker" path="split_with" id="split_with" name="split_with" multiple data-live-search="true"  >
							  	<c:forEach items="${paidby}" var="user">
				                    <option value="${user}">YOU</option>
				                </c:forEach>
							</select>
						</div>
					</div>
					
				
						
						<div class="actiondiv1">
							<select class="selectpicker" path="paid_by" id="paid_by1" name="paid_by1" data-live-search="true"  >
							  	<c:forEach items="${paidby}" var="user">
				                    
				                </c:forEach>
							</select>
						</div>
					
					<div style="width:250px;" class="container-login100-form-btn">
					<c:choose>
							<c:when test="${edit}">
								<button type = "submit" class="login100-form-btn">
									Update Expense
								</button>
							</c:when>
							<c:otherwise>
								<button type = "submit" class="login100-form-btn">
									Add Expense
								</button>
							</c:otherwise>
					</c:choose>
						
					</div>


				</form:form>	
		
   			</div>
        </div>





<%@include file="footer.jsp" %>
