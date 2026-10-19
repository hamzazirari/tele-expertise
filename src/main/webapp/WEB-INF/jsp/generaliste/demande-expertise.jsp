<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Demande d'expertise</title>
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

        <a href="${pageContext.request.contextPath}/generaliste/choix-specialiste?id=${consultation.id}"
           class="text-sm text-blue-600 hover:text-blue-700 font-medium">← Retour</a>

        <h1 class="text-2xl font-bold text-gray-800 mt-2 mb-4">Demande d'expertise</h1>

        <c:if test="${param.erreur == '1'}">
            <div class="bg-red-50 border border-red-200 text-red-700 text-sm rounded-lg px-4 py-3 mb-4">
                Choisis un créneau disponible.
            </div>
        </c:if>

        <%-- Rappel patient + spécialiste --%>
        <div class="bg-white rounded-2xl shadow p-6 mb-6">
            <div class="grid gap-3 sm:grid-cols-2 text-sm">
                <div>
                    <span class="text-gray-500">Patient</span>
                    <p class="font-medium text-gray-800">${consultation.patient.nom} ${consultation.patient.prenom}</p>
                </div>
                <div>
                    <span class="text-gray-500">Spécialiste</span>
                    <p class="font-medium text-gray-800">
                        ${specialiste.prenom} ${specialiste.nom} (${specialiste.specialite}) - ${specialiste.tarif} DH
                    </p>
                </div>
            </div>
        </div>

        <form action="${pageContext.request.contextPath}/generaliste/demande-expertise" method="post" class="space-y-6">
            <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
            <input type="hidden" name="consultationId" value="${consultation.id}">
            <input type="hidden" name="specialisteId" value="${specialiste.id}">

            <%-- Créneaux --%>
            <div class="bg-white rounded-2xl shadow overflow-x-auto">
                <h2 class="text-lg font-semibold text-gray-800 p-6 pb-3">Créneaux</h2>
                <table class="w-full text-sm text-left">
                    <thead class="bg-blue-100 text-blue-800">
                        <tr>
                            <th class="px-4 py-3">Choix</th>
                            <th class="px-4 py-3">Date</th>
                            <th class="px-4 py-3">Horaire</th>
                            <th class="px-4 py-3">Statut</th>
                        </tr>
                    </thead>
                    <tbody class="divide-y divide-gray-100">
                        <c:forEach var="c" items="${creneaux}">
                            <tr class="hover:bg-blue-50">
                                <td class="px-4 py-3">
                                    <c:if test="${c.statut == 'DISPONIBLE'}">
                                        <input type="radio" name="creneauId" value="${c.id}"
                                               class="h-4 w-4 text-blue-500">
                                    </c:if>
                                </td>
                                <td class="px-4 py-3 font-medium text-gray-800">${c.date}</td>
                                <td class="px-4 py-3">${c.heureDebut} - ${c.heureFin}</td>
                                <td class="px-4 py-3">
                                    <c:if test="${c.statut == 'DISPONIBLE'}">
                                        <span class="bg-green-100 text-green-700 text-xs font-semibold px-3 py-1 rounded-full">${c.statut}</span>
                                    </c:if>
                                    <c:if test="${c.statut != 'DISPONIBLE'}">
                                        <span class="bg-gray-100 text-gray-500 text-xs font-semibold px-3 py-1 rounded-full">${c.statut}</span>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <%-- Demande --%>
            <div class="bg-white rounded-2xl shadow p-6">
                <h2 class="text-lg font-semibold text-gray-800 mb-4">Demande</h2>
                <div class="space-y-4">
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Question au spécialiste</label>
                        <textarea name="question" rows="4" required
                                  class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400"></textarea>
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Données et analyses <span class="text-gray-400">(optionnel)</span></label>
                        <textarea name="donneesAnalyses" rows="4"
                                  class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400"></textarea>
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Priorité</label>
                        <select name="priorite"
                                class="w-full border border-gray-300 rounded-lg px-4 py-2 bg-white focus:outline-none focus:ring-2 focus:ring-blue-400">
                            <c:forEach var="p" items="${priorites}">
                                <option value="${p}">${p}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>

            <button type="submit"
                    class="w-full bg-blue-500 hover:bg-blue-600 text-white font-semibold rounded-lg py-3 transition">
                Envoyer la demande
            </button>
        </form>
    </main>

</body>
</html>