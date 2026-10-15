<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Détail de la consultation</title>
</head>
<body>
    <h2>Consultation</h2>
    <p><a href="${pageContext.request.contextPath}/generaliste/consultations">Retour</a></p>

    <p>Patient : ${consultation.patient.nom} ${consultation.patient.prenom}</p>
    <p>Motif : ${consultation.motif}</p>
    <p>Observations : ${consultation.observations}</p>
    <p>Coût : ${consultation.cout} DH</p>
    <p>Statut : ${consultation.statut}</p>

    <h3>Prise en charge directe</h3>
    <form action="${pageContext.request.contextPath}/generaliste/detail-consultation" method="post">
        <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
        <input type="hidden" name="id" value="${consultation.id}">

        <p>Diagnostic :<br>
            <input type="text" name="diagnostic" size="50" required></p>
        <p>Traitement (ex : Paracétamol 1g, 3 fois/jour) :<br>
            <textarea name="traitement" rows="4" cols="50" required></textarea></p>

        <button type="submit">Clôturer</button>
    </form>

<h3>Besoin d'un spécialiste ?</h3>
<form action="${pageContext.request.contextPath}/generaliste/choix-specialiste" method="post">
    <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
    <input type="hidden" name="id" value="${consultation.id}">
    <button type="submit">Demander avis spécialiste</button>
</form>
</body>
</html>