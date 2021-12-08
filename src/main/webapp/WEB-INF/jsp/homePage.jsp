<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
	<c:when test="${sessionScope.locale != null}">
		<fmt:setLocale value="${sessionScope.locale}"/>
	</c:when>
	<c:otherwise>
		<fmt:setLocale value="en"/>
	</c:otherwise>
</c:choose>
<fmt:setBundle basename="locale" var="localeFile" scope="request"/>

<fmt:message bundle="${localeFile}" key="homePage.title" var="title"/>

<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
	<style>
		<c:import url="${pageContext.request.contextPath}/css/reset.css"/>
	</style>
	<title>${title}</title>
</head>
<body>
<c:import url="header.jsp"/>
<c:import url="roomsList.jsp"/>
</body>
</html>