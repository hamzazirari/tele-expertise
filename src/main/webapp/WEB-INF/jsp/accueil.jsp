<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Accueil</title>
</head>
<body>
    <h2>Bienvenue ${sessionScope.utilisateur.prenom} ${sessionScope.utilisateur.nom}</h2>
    <p>Rôle : ${sessionScope.utilisateur.role}</p>

    <c:if test="${sessionScope.utilisateur.role == 'INFIRMIER'}">
        <p>Espace infirmier (à venir)</p>
    </c:if>

    <c:if test="${sessionScope.utilisateur.role == 'GENERALISTE'}">
        <p>Espace généraliste (à venir)</p>
    </c:if>

    <c:if test="${sessionScope.utilisateur.role == 'SPECIALISTE'}">
        <p>Espace spécialiste (à venir)</p>
    </c:if>

    <p><a href="${pageContext.request.contextPath}/logout">Se déconnecter</a></p>
</body>
</html>