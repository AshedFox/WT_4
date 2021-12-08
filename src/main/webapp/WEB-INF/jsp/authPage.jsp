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

<fmt:message bundle="${localeFile}" key="authPage.title" var="title"/>
<fmt:message bundle="${localeFile}" key="authPage.email.label" var="emailLabel"/>
<fmt:message bundle="${localeFile}" key="authPage.password.label" var="passwordLabel"/>
<fmt:message bundle="${localeFile}" key="authPage.submit" var="submit"/>
<fmt:message bundle="${localeFile}" key="authPage.go_to_registration" var="goToRegistration"/>

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
                <fmt:message bundle="${locale}" key="error.auth" var="authError"/>
                <small>${authError}</small>
            </c:if>
            <button class="form__button" type="submit" name="submit">${submit}</button>
        </form>
        <a href="<c:url value="/registration"/>">${goToRegistration}</a>
    </div>
</div>
</body>
</html>