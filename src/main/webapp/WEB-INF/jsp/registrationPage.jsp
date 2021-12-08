<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<c:choose>
	<c:when test="${sessionScope.locale != null}">
		<fmt:setLocale value="${sessionScope.locale}"/>
	</c:when>
	<c:otherwise>
		<fmt:setLocale value="en"/>
	</c:otherwise>
</c:choose>
<fmt:setBundle basename="locale" var="localeFile" scope="request"/>

<fmt:message bundle="${localeFile}" key="registrationPage.title" var="title"/>
<fmt:message bundle="${localeFile}" key="registrationPage.email.label" var="emailLabel"/>
<fmt:message bundle="${localeFile}" key="registrationPage.password.label" var="passwordLabel"/>
<fmt:message bundle="${localeFile}" key="registrationPage.submit" var="submit"/>
<fmt:message bundle="${localeFile}" key="registrationPage.go_to_auth" var="goToAuth"/>

<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
	<style>
		<c:import url="${pageContext.request.contextPath}/css/reset.css"/>
	</style>
	<style>
		<c:import url="${pageContext.request.contextPath}/css/authPage.css"/>
	</style>
	<title>${title}</title>
</head>
<body>
<c:import url="header.jsp"/>
<div class="login__container">
	<div class="login__content">
		<form class="login__form" method="post">
			<label>${emailLabel}
				<input class="form__input" type="email" name="email" value="${email}"/>
			</label>
			<label>${passwordLabel}
				<input class="form__input" type="password" name="password" value="${password}" />
			</label>
			<c:if test="${not empty error}">
				<fmt:message bundle="${locale}" key="error.registration" var="regError"/>
				<small>${regError}</small>
			</c:if>
			<c:if test="${not empty message}">
				<fmt:message bundle="${locale}" key="message.registration_success" var="regSuccess"/>
				<small>${regSuccess}</small>
			</c:if>
			<button class="form__button" type="submit" name="submit">${submit}</button>
		</form>
		<a href="<c:url value="/auth"/>">${goToAuth}</a>
	</div>
</div>
</body>
</html>