<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nouveau patient</title>
</head>
<body>
    <h2>Nouveau patient</h2>
    <p><a href="${pageContext.request.contextPath}/infirmier/accueil-patient">Retour</a></p>

    <c:if test="${not empty erreur}">
        <p style="color: red;">${erreur}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/infirmier/nouveau-patient" method="post">
        <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">

        <h3>Données administratives</h3>
        <p>Nom : <input type="text" name="nom" required></p>
        <p>Prénom : <input type="text" name="prenom" required></p>
        <p>Date de naissance : <input type="date" name="dateNaissance" required></p>
        <p>N° sécurité sociale : <input type="text" name="numeroSecu" value="${numeroSecu}" required></p>
        <p>Téléphone (optionnel) : <input type="text" name="telephone"></p>
        <p>Adresse (optionnel) : <input type="text" name="adresse"></p>
        <p>Mutuelle : <input type="text" name="mutuelle"></p>

        <h3>Données médicales</h3>
        <p>Antécédents : <input type="text" name="antecedents"></p>
        <p>Allergies : <input type="text" name="allergies"></p>
        <p>Traitements en cours : <input type="text" name="traitementsEnCours"></p>

        <h3>Signes vitaux</h3>
        <p>Tension artérielle (ex : 12/8) : <input type="text" name="tension" required></p>
        <p>Fréquence cardiaque : <input type="number" name="frequenceCardiaque" required></p>
        <p>Température (°C) : <input type="number" step="0.1" name="temperature" required></p>
        <p>Fréquence respiratoire : <input type="number" name="frequenceRespiratoire" required></p>
        <p>Poids en kg (optionnel) : <input type="number" step="0.1" name="poids"></p>
        <p>Taille en cm (optionnel) : <input type="number" step="0.1" name="taille"></p>

        <button type="submit">Créer le dossier et ajouter à la file d'attente</button>
    </form>
</body>
</html>