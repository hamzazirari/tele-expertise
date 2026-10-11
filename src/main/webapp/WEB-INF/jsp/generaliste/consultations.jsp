<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mes consultations</title>
</head>
<body>
    <h2>Mes consultations en cours</h2>
    <p><a href="${pageContext.request.contextPath}/accueil">Retour</a></p>

    <c:if test="${empty liste}">
        <p>Aucune consultation en cours.</p>
    </c:if>

    <c:if test="${not empty liste}">
        <table border="1" cellpadding="5">
            <tr>
                <th>Patient</th>
                <th>Motif</th>
                <th>Statut</th>
                <th>Action</th>
            </tr>
            <c:forEach var="c" items="${liste}">
                <tr>
                    <td>${c.patient.nom} ${c.patient.prenom}</td>
                    <td>${c.motif}</td>
                    <td>${c.statut}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/generaliste/detail-consultation?id=${c.id}">
                            Ouvrir</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>