<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <c:import url="${pageContext.request.contextPath}/css/roomsList.css"/>
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

<fmt:message bundle="${localeFile}" key="roomsList.title" var="title"/>
<fmt:message bundle="${localeFile}" key="roomsList.number" var="number"/>
<fmt:message bundle="${localeFile}" key="roomsList.price" var="price"/>
<fmt:message bundle="${localeFile}" key="roomsList.free" var="free"/>
<fmt:message bundle="${localeFile}" key="roomsList.reserved" var="reserved"/>
<fmt:message bundle="${localeFile}" key="roomsList.goto" var="go_to"/>

<div class="rooms__container">
    <div class="rooms__content">
        <div class="rooms__title">${title}</div>
        <ul class="rooms__list">
            <jsp:useBean id="rooms" scope="request" type="java.util.List<bsuir.wt.lab4.entity.Room>"/>
            <c:forEach items="${rooms}" var="room">
                <li class="rooms__list-item">
                    <div class="attribute">${number}: ${room.number}</div>
                    <div class="attribute">${price}: ${room.price}</div>
                    <div class="attribute">
                        <c:choose>
                            <c:when test="${room.free}">${free}</c:when>
                            <c:otherwise>${reserved}</c:otherwise>
                        </c:choose>
                    </div>
                    <a  href="room?id=${room.id}">${go_to} ${room.number} &#x27A1;</a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
