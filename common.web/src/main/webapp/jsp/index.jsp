<%@ page contentType="text/html;charset=UTF-8"%>

<%
 if (session.getAttribute("user") == null) {
 	    pageContext.forward("jsp/login.jsp");
 	    return;
 	}
%>