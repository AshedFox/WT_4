<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:choose>
    <c:when test="${sessionScope.locale != null}">
        <fmt:setLocale value="${sessionScope.locale}"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="locale" var="localeFile" scope="request"/>

<fmt:message bundle="${localeFile}" key="roomPage.title" var="title"/>
<fmt:message bundle="${localeFile}" key="roomsList.number" var="number"/>
<fmt:message bundle="${localeFile}" key="roomsList.description" var="description"/>
<fmt:message bundle="${localeFile}" key="roomsList.price" var="price"/>
<fmt:message bundle="${localeFile}" key="roomsList.free" var="free"/>
<fmt:message bundle="${localeFile}" key="roomsList.reserved" var="reserved"/>
<fmt:message bundle="${localeFile}" key="roomPage.room" var="roomText"/>
<fmt:message bundle="${localeFile}" key="roomPage.book" var="bookButton"/>

<html lang="${sessionScope.locale}">
<head>
    <title>${title}</title>
    <style>
        <c:import url="${pageContext.request.contextPath}/css/roomPage.css"/>
        <c:import url="${pageContext.request.contextPath}/css/reset.css"/>
    </style>
</head>
<body>
<c:import url="header.jsp"/>
<div class="room__container">
    <div class="room__content">
        <jsp:useBean id="room" scope="request" type="bsuir.wt.lab4.entity.Room"/>
        <c:choose>
            <c:when test="${empty room}">
                <fmt:message bundle="${localeFile}" key="${error}" var="roomError"/>
                <div>${roomError}</div>
            </c:when>
            <c:otherwise>
                <div class="room__title">${roomText} ${room.number}</div>
                <div class="room__attribute">${description}: ${room.description}</div>
                <div class="room__attribute">${price}: ${room.price}</div>
                <div class="room__attribute">
                    <c:choose>
                        <c:when test="${room.free}">${free}</c:when>
                        <c:otherwise>${reserved}</c:otherwise>
                    </c:choose>
                </div>
                <c:if test="${not empty sessionScope.user}">
                    <form method="post">
                        <input hidden name="id" value="${room.id}">
                        <c:choose>
                            <c:when test="${room.free}">
                                <button class="room__book-button" type="submit">${bookButton}</button>
                            </c:when>
                            <c:otherwise>
                                <button class="room__book-button" type="submit" disabled>${bookButton}</button>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </c:if>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
