<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Patients du jour</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-blue-50 min-h-screen">

    <%-- Barre du haut --%>
    <nav class="bg-white shadow-sm">
        <div class="max-w-6xl mx-auto px-4 py-3 flex items-center justify-between">
            <div class="flex items-center gap-2">
                <span class="text-2xl"></span>
                <span class="font-bold text-gray-800">Télé-expertise</span>
            </div>
            <a href="${pageContext.request.contextPath}/logout"
               class="text-sm text-red-500 hover:text-red-600 font-medium">
                Se déconnecter
            </a>
        </div>
    </nav>

    <main class="max-w-6xl mx-auto p-4 mt-6">

        <a href="${pageContext.request.contextPath}/accueil"
           class="text-sm text-blue-600 hover:text-blue-700 font-medium">← Retour</a>

        <h1 class="text-2xl font-bold text-gray-800 mt-2 mb-4"> Patients du jour</h1>

        <%-- Liste vide --%>
        <c:if test="${empty liste}">
            <div class="bg-white rounded-2xl shadow p-8 text-center text-gray-500">
                Aucun patient enregistré aujourd'hui.
            </div>
        </c:if>

        <%-- Tableau --%>
        <c:if test="${not empty liste}">
            <div class="bg-white rounded-2xl shadow overflow-x-auto">
                <table class="w-full text-sm text-left">
                    <thead class="bg-blue-100 text-blue-800">
                        <tr>
                            <th class="px-4 py-3">Nom</th>
                            <th class="px-4 py-3">Prénom</th>
                            <th class="px-4 py-3">Heure d'arrivée</th>
                            <th class="px-4 py-3">N° sécurité sociale</th>
                            <th class="px-4 py-3">Tension</th>
                            <th class="px-4 py-3">Fréq. cardiaque</th>
                            <th class="px-4 py-3">Température</th>
                            <th class="px-4 py-3">Fréq. respiratoire</th>
                            <th class="px-4 py-3">Poids</th>
                            <th class="px-4 py-3">Taille</th>
                        </tr>
                    </thead>
                    <tbody class="divide-y divide-gray-100">
                        <c:forEach var="f" items="${liste}">
                            <tr class="hover:bg-blue-50">
                                <td class="px-4 py-3 font-medium text-gray-800">${f.patient.nom}</td>
                                <td class="px-4 py-3">${f.patient.prenom}</td>
                                <td class="px-4 py-3">${f.heureArrivee.toLocalTime().withNano(0)}</td>
                                <td class="px-4 py-3">${f.patient.numeroSecuriteSociale}</td>
                                <td class="px-4 py-3">${f.signesVitaux.tensionArterielle}</td>
                                <td class="px-4 py-3">${f.signesVitaux.frequenceCardiaque}</td>
                                <td class="px-4 py-3">${f.signesVitaux.temperature}</td>
                                <td class="px-4 py-3">${f.signesVitaux.frequenceRespiratoire}</td>
                                <td class="px-4 py-3">${f.signesVitaux.poids}</td>
                                <td class="px-4 py-3">${f.signesVitaux.taille}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </main>

</body>
</html>