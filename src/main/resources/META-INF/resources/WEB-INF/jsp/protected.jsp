<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
    if (request.getParameter("logoff") != null) {
        session.invalidate();
        response.sendRedirect("index.jsp");
        return;
    }
%>
<html>
<head>
    <title>Protected Page for Examples</title>
    <!--<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>-->
    <spring:url value="/css/main.css" var="springCss" />
    <link href="${springCss}" rel="stylesheet" />
</head>
<body>
You are logged in as remote user <b><%= request.getRemoteUser() %></b> in session <b><%= session.getId() %></b>.
<br><br>
<%
    if (request.getUserPrincipal() != null) {
%>
Your user principal name is <b><%= request.getUserPrincipal().getName() %></b>.
<br><br>
<%
} else {
%>
No user principal could be identified.
<br><br>
<%
    }
%>
<%
    String role = request.getParameter("role");
    if (role == null)
        role = "";
    if (role.length() > 0) {
        if (request.isUserInRole(role)) {
%>
You have been granted role <b><%= role %></b>.
<br><br>
<%
} else {
%>
You have <i>not</i> been granted role <b><%= role %></b>.
<br><br>
<%
        }
    }
%>
</body>
</html>
