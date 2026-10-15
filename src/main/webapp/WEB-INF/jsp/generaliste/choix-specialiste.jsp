<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Choix du spécialiste</title>
</head>
<body>
    <h2>Demander l'avis d'un spécialiste</h2>
    <p><a href="${pageContext.request.contextPath}/generaliste/consultations">Retour</a></p>

    <p>Patient : ${consultation.patient.nom} ${consultation.patient.prenom}</p>
    <p>Statut : ${consultation.statut}</p>

    <%-- Choix de la spécialité --%>
    <form action="${pageContext.request.contextPath}/generaliste/choix-specialiste" method="get">
        <input type="hidden" name="id" value="${consultation.id}">
        <label>Spécialité :</label>
        <select name="specialite">
            <c:forEach var="s" items="${specialites}">
                <option value="${s}" ${s == specialiteChoisie ? 'selected' : ''}>${s}</option>
            </c:forEach>
        </select>
        <button type="submit">Rechercher</button>
    </form>

    <%-- Liste des spécialistes --%>
    <c:if test="${specialiteChoisie != null}">
        <h3>Spécialistes : ${specialiteChoisie}</h3>

        <c:if test="${empty liste}">
            <p>Aucun spécialiste pour cette spécialité.</p>
        </c:if>

        <c:if test="${not empty liste}">
            <table border="1" cellpadding="5">
                <tr>
                    <th>Nom</th>
                    <th>Tarif</th>
                    <th>Disponibilité</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="sp" items="${liste}">
                    <tr>
                        <td>${sp.prenom} ${sp.nom}</td>
                        <td>${sp.tarif} DH</td>
                        <td>Voir les créneaux</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/generaliste/demande-expertise?consultationId=${consultation.id}&specialisteId=${sp.id}">
                                Choisir</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </c:if>
</body>
</html>