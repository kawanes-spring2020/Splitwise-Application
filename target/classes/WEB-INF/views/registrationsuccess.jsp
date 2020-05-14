<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<%@include file="header.jsp" %>
	        <div class="wrapper">
            <!-- Sidebar Holder -->
            <nav id="sidebar">
                <div class="sidebar-header">
                    <img class="home" src="<c:url value="/static/images/splitwiselogin.jpg"/>"/>
                </div>

                <ul class="list-unstyled components">
                    <li>
                        <a href="/SplitwiseApp/register">About</a>
                        
                    </li>
                   
                    
                    
                </ul>

                
            </nav>

            <!-- Page Content Holder -->
            <div id="content">

                <nav class="navbar navbar-default">
                    <div class="container-fluid">

                        <div class="navbar-header">
                                <h4>Welcome to SplitWise App!! Your Registration is successful.</h4>
                        </div>

                    </div>
                </nav>

            
            </div>
	<div class="generic-container">
		
		<div class="alert alert-success lead">
	    	${success}
		</div>
		
		<span class="well floatRight">
			Go to <a href="<c:url value='/login' />">Login Page</a>
		</span>
	</div>
 </div>
<%@include file="footer.jsp" %>