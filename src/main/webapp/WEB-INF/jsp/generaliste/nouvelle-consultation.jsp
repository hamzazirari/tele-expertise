<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nouvelle consultation</title>
</head>
<body>
    <h2>Nouvelle consultation</h2>
    <p><a href="${pageContext.request.contextPath}/generaliste/file-attente">Retour</a></p>

    <h3>Dossier du patient</h3>
    <p>Nom : ${file.patient.nom} ${file.patient.prenom}</p>
    <p>Date de naissance : ${file.patient.dateNaissance}</p>
    <p>Mutuelle : ${file.patient.mutuelle}</p>
    <p>Antécédents : ${file.patient.antecedents}</p>
    <p>Allergies : ${file.patient.allergies}</p>
    <p>Traitements en cours : ${file.patient.traitementsEnCours}</p>

    <h3>Signes vitaux</h3>
    <p>Tension : ${file.signesVitaux.tensionArterielle}</p>
    <p>Fréquence cardiaque : ${file.signesVitaux.frequenceCardiaque}</p>
    <p>Température : ${file.signesVitaux.temperature}</p>
    <p>Fréquence respiratoire : ${file.signesVitaux.frequenceRespiratoire}</p>
    <p>Poids : ${file.signesVitaux.poids} | Taille : ${file.signesVitaux.taille}</p>

    <h3>Consultation</h3>
    <form action="${pageContext.request.contextPath}/generaliste/nouvelle-consultation" method="post">
        <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
        <input type="hidden" name="fileId" value="${file.id}">

        <p>Motif :<br>
            <input type="text" name="motif" size="50" required></p>
        <p>Observations (examen clinique, symptômes) :<br>
            <textarea name="observations" rows="5" cols="50" required></textarea></p>
        <p>Coût de la consultation : 150 DH</p>

        <button type="submit">Créer la consultation</button>
    </form>
</body>
</html>