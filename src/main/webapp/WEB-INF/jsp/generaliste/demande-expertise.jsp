<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Demande d'expertise</title>
</head>
<body>
    <h2>Demande d'expertise</h2>
    <p><a href="${pageContext.request.contextPath}/generaliste/choix-specialiste?id=${consultation.id}">Retour</a></p>

    <c:if test="${param.erreur == '1'}">
        <p style="color: red;">Choisis un créneau disponible.</p>
    </c:if>

    <p>Patient : ${consultation.patient.nom} ${consultation.patient.prenom}</p>
    <p>Spécialiste : ${specialiste.prenom} ${specialiste.nom} (${specialiste.specialite}) - ${specialiste.tarif} DH</p>

    <form action="${pageContext.request.contextPath}/generaliste/demande-expertise" method="post">
        <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
        <input type="hidden" name="consultationId" value="${consultation.id}">
        <input type="hidden" name="specialisteId" value="${specialiste.id}">

        <h3>Créneaux</h3>
        <table border="1" cellpadding="5">
            <tr><th>Choix</th><th>Date</th><th>Horaire</th><th>Statut</th></tr>
            <c:forEach var="c" items="${creneaux}">
                <tr>
                    <td>
                        <c:if test="${c.statut == 'DISPONIBLE'}">
                            <input type="radio" name="creneauId" value="${c.id}">
                        </c:if>
                    </td>
                    <td>${c.date}</td>
                    <td>${c.heureDebut} - ${c.heureFin}</td>
                    <td>${c.statut}</td>
                </tr>
            </c:forEach>
        </table>

        <h3>Demande</h3>
        <p>Question au spécialiste :<br>
            <textarea name="question" rows="4" cols="50" required></textarea></p>
        <p>Données et analyses :<br>
            <textarea name="donneesAnalyses" rows="4" cols="50"></textarea></p>
        <p>Priorité :
            <select name="priorite">
                <c:forEach var="p" items="${priorites}">
                    <option value="${p}">${p}</option>
                </c:forEach>
            </select>
        </p>

        <button type="submit">Envoyer la demande</button>
    </form>
</body>
</html>