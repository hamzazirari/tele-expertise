<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Patients du jour</title>
</head>
<body>
    <h2>Patients du jour</h2>
    <p><a href="${pageContext.request.contextPath}/accueil">Retour</a></p>

    <c:if test="${empty liste}">
        <p>Aucun patient enregistré aujourd'hui.</p>
    </c:if>

    <c:if test="${not empty liste}">
        <table border="1" cellpadding="5">
            <tr>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Heure d'arrivée</th>
                <th>N° sécurité sociale</th>
                <th>Tension</th>
                <th>Fréq. cardiaque</th>
                <th>Température</th>
                <th>Fréq. respiratoire</th>
                <th>Poids</th>
                <th>Taille</th>
            </tr>
            <c:forEach var="f" items="${liste}">
                <tr>
                    <td>${f.patient.nom}</td>
                    <td>${f.patient.prenom}</td>
                    <td>${f.heureArrivee.toLocalTime().withNano(0)}</td>
                    <td>${f.patient.numeroSecuriteSociale}</td>
                    <td>${f.signesVitaux.tensionArterielle}</td>
                    <td>${f.signesVitaux.frequenceCardiaque}</td>
                    <td>${f.signesVitaux.temperature}</td>
                    <td>${f.signesVitaux.frequenceRespiratoire}</td>
                    <td>${f.signesVitaux.poids}</td>
                    <td>${f.signesVitaux.taille}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>