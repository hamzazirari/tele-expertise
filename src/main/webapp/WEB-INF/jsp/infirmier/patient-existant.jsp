<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Patient existant</title>
</head>
<body>
    <h2>Patient existant</h2>
    <p><a href="${pageContext.request.contextPath}/infirmier/accueil-patient">Retour</a></p>

    <h3>Informations du patient</h3>
    <p>Nom : ${patient.nom}</p>
    <p>Prénom : ${patient.prenom}</p>
    <p>Date de naissance : ${patient.dateNaissance}</p>
    <p>N° sécurité sociale : ${patient.numeroSecuriteSociale}</p>
    <p>Mutuelle : ${patient.mutuelle}</p>
    <p>Allergies : ${patient.allergies}</p>
    <p>Antécédents : ${patient.antecedents}</p>
    <p>Traitements en cours : ${patient.traitementsEnCours}</p>

    <h3>Nouveaux signes vitaux</h3>
    <form action="${pageContext.request.contextPath}/infirmier/patient-existant" method="post">
        <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
        <input type="hidden" name="id" value="${patient.id}">

        <p>Tension artérielle (ex : 12/8) : <input type="text" name="tension" required></p>
        <p>Fréquence cardiaque : <input type="number" name="frequenceCardiaque" required></p>
        <p>Température (°C) : <input type="number" step="0.1" name="temperature" required></p>
        <p>Fréquence respiratoire : <input type="number" name="frequenceRespiratoire" required></p>
        <p>Poids en kg (optionnel) : <input type="number" step="0.1" name="poids"></p>
        <p>Taille en cm (optionnel) : <input type="number" step="0.1" name="taille"></p>

        <button type="submit">Ajouter à la file d'attente</button>
    </form>
</body>
</html>