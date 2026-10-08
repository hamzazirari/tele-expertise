<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Accueil du patient</title>
</head>
<body>
    <h2>Accueil du patient</h2>
    <p><a href="${pageContext.request.contextPath}/accueil">Retour</a></p>

    <form action="${pageContext.request.contextPath}/infirmier/accueil-patient" method="post">
        <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
        <label>Numéro de sécurité sociale :</label>
        <input type="text" name="numeroSecu" value="${numeroSecu}" required>
        <button type="submit">Rechercher</button>
    </form>

    <%-- Patient trouvé --%>
    <c:if test="${trouve == true}">
        <h3>Patient trouvé</h3>
        <p>Nom : ${patient.nom}</p>
        <p>Prénom : ${patient.prenom}</p>
        <p>Date de naissance : ${patient.dateNaissance}</p>
        <p>Mutuelle : ${patient.mutuelle}</p>
        <p>Allergies : ${patient.allergies}</p>
        <p>Antécédents : ${patient.antecedents}</p>
        <p>Traitements en cours : ${patient.traitementsEnCours}</p>
        <p>(Saisie des signes vitaux : étape suivante)</p>
    </c:if>

    <%-- Patient inconnu --%>
    <c:if test="${trouve == false}">
        <p style="color: orange;">Aucun patient avec ce numéro.</p>
        <p>(Création du nouveau patient : étape suivante)</p>
    </c:if>
</body>
</html>