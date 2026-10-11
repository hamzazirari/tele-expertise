<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>File d'attente</title>
</head>
<body>
    <h2>File d'attente</h2>
    <p><a href="${pageContext.request.contextPath}/accueil">Retour</a></p>

    <c:if test="${empty liste}">
        <p>Aucun patient en attente.</p>
    </c:if>

    <c:if test="${not empty liste}">
        <table border="1" cellpadding="5">
            <tr>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Heure d'arrivée</th>
                <th>Action</th>
            </tr>
            <c:forEach var="f" items="${liste}">
                <tr>
                    <td>${f.patient.nom}</td>
                    <td>${f.patient.prenom}</td>
                    <td>${f.heureArrivee.toLocalTime().withNano(0)}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/generaliste/nouvelle-consultation?fileId=${f.id}">
                            Prendre en charge</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>