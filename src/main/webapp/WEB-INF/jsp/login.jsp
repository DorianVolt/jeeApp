<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/header.jsp"%>

<c:url var="login"  value="/web/login" />

<form name="login" action="login" method="POST" >
    <ul>
        <li><label>User:</label> <input type='text' name='email_address' /></li>
        <li><label>Password:</label> <input type='text' name='password' /></li>
        <li><label>&nbsp;</label> <input type="submit" value="OK" class="btn"></li>
    </ul>
</form>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>