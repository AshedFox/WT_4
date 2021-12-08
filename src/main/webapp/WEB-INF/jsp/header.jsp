<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page  contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <c:import url="${pageContext.request.contextPath}/css/header.css"/>
</style>

<c:choose>
    <c:when test="${sessionScope.locale != null}">
        <fmt:setLocale value="${sessionScope.locale}"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="locale" var="localeFile" scope="request"/>

<fmt:message bundle="${localeFile}" key="header.home" var="home"/>
<fmt:message bundle="${localeFile}" key="header.login" var="login"/>
<fmt:message bundle="${localeFile}" key="header.logout" var="logout"/>

<div class="header__container">
    <nav class="nav">
        <ul class="nav__list">
            <li class="nav__list-item"><a href="home">${home}</a></li>
            <c:choose>
                <c:when test="${empty sessionScope.user}">
                    <li class="nav__list-item additional"><a href="auth">${login}</a></li>
                </c:when>
                <c:otherwise>
                    <li class="nav__list-item additional"><a href="controller?command=logout">${logout}</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
        <div class="nav__locale">
            <form class="nav__locale-item" method="post" action="controller?command=change_locale&locale=en">
                <button type="submit">en</button>
            </form>
            <form class="nav__locale-item" method="post" action="controller?command=change_locale&locale=ru">
                <button type="submit">рус</button>
            </form>
        </div>
    </nav>
</div>