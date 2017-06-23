<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
    <head>
        <title>Kerberos Demo page</title>
        <spring:url value="/css/main.css" var="springCss" />
        <link href="${springCss}" rel="stylesheet" />
    </head>
    <body>
        <h1>Kerberos Demo page</h1>
        <h2>You are un-authorized on this page</h2>
        <p>
            Goto the
            <a href="/protected">protected</a> page
        </p>
    </body>
</html>