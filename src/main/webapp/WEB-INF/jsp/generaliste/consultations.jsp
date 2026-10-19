<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Mes consultations</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-blue-50 min-h-screen">

    <%-- Barre du haut --%>
    <nav class="bg-white shadow-sm">
        <div class="max-w-4xl mx-auto px-4 py-3 flex items-center justify-between">
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

    <main class="max-w-4xl mx-auto p-4 mt-6">

        <a href="${pageContext.request.contextPath}/accueil"
           class="text-sm text-blue-600 hover:text-blue-700 font-medium">← Retour</a>

        <h1 class="text-2xl font-bold text-gray-800 mt-2 mb-4"> Mes consultations ouvertes</h1>

        <%-- Liste vide --%>
        <c:if test="${empty liste}">
            <div class="bg-white rounded-2xl shadow p-8 text-center text-gray-500">
                Aucune consultation en cours.
            </div>
        </c:if>

        <%-- Tableau --%>
        <c:if test="${not empty liste}">
            <div class="bg-white rounded-2xl shadow overflow-x-auto">
                <table class="w-full text-sm text-left">
                    <thead class="bg-blue-100 text-blue-800">
                        <tr>
                            <th class="px-4 py-3">Patient</th>
                            <th class="px-4 py-3">Motif</th>
                            <th class="px-4 py-3">Statut</th>
                            <th class="px-4 py-3">Action</th>
                        </tr>
                    </thead>
                    <tbody class="divide-y divide-gray-100">
                        <c:forEach var="c" items="${liste}">
                            <tr class="hover:bg-blue-50">
                                <td class="px-4 py-3 font-medium text-gray-800">${c.patient.nom} ${c.patient.prenom}</td>
                                <td class="px-4 py-3">${c.motif}</td>
                                <td class="px-4 py-3">
                                    <c:if test="${c.statut == 'EN_COURS'}">
                                        <span class="bg-blue-100 text-blue-700 text-xs font-semibold px-3 py-1 rounded-full">${c.statut}</span>
                                    </c:if>
                                    <c:if test="${c.statut != 'EN_COURS'}">
                                        <span class="bg-yellow-100 text-yellow-700 text-xs font-semibold px-3 py-1 rounded-full">${c.statut}</span>
                                    </c:if>
                                </td>
                                <td class="px-4 py-3">
                                    <a href="${pageContext.request.contextPath}/generaliste/detail-consultation?id=${c.id}"
                                       class="inline-block bg-blue-500 hover:bg-blue-600 text-white font-semibold rounded-lg px-4 py-1.5 transition">
                                        Ouvrir
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </main>

</body>
</html>