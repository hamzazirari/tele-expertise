<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Choix du spécialiste</title>
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

        <a href="${pageContext.request.contextPath}/generaliste/consultations"
           class="text-sm text-blue-600 hover:text-blue-700 font-medium">← Retour</a>

        <h1 class="text-2xl font-bold text-gray-800 mt-2 mb-4"> Demander l'avis d'un spécialiste</h1>

        <%-- Rappel de la consultation --%>
        <div class="bg-white rounded-2xl shadow p-6 mb-6">
            <div class="grid gap-3 sm:grid-cols-2 text-sm">
                <div>
                    <span class="text-gray-500">Patient</span>
                    <p class="font-medium text-gray-800">${consultation.patient.nom} ${consultation.patient.prenom}</p>
                </div>
                <div>
                    <span class="text-gray-500">Statut</span>
                    <p class="mt-1">
                        <span class="bg-yellow-100 text-yellow-700 text-xs font-semibold px-3 py-1 rounded-full">
                            ${consultation.statut}
                        </span>
                    </p>
                </div>
            </div>
        </div>

        <%-- Choix de la spécialité --%>
        <div class="bg-white rounded-2xl shadow p-6 mb-6">
            <form action="${pageContext.request.contextPath}/generaliste/choix-specialiste" method="get"
                  class="flex flex-col sm:flex-row gap-3 sm:items-end">
                <input type="hidden" name="id" value="${consultation.id}">

                <div class="flex-1">
                    <label class="block text-sm font-medium text-gray-700 mb-1">Spécialité</label>
                    <select name="specialite"
                            class="w-full border border-gray-300 rounded-lg px-4 py-2 bg-white focus:outline-none focus:ring-2 focus:ring-blue-400">
                        <c:forEach var="s" items="${specialites}">
                            <option value="${s}" ${s == specialiteChoisie ? 'selected' : ''}>${s}</option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit"
                        class="bg-blue-500 hover:bg-blue-600 text-white font-semibold rounded-lg px-6 py-2 transition">
                    Rechercher
                </button>
            </form>
        </div>

        <%-- Liste des spécialistes --%>
        <c:if test="${specialiteChoisie != null}">
            <h2 class="text-lg font-semibold text-gray-800 mb-3">Spécialistes : ${specialiteChoisie}</h2>

            <c:if test="${empty liste}">
                <div class="bg-white rounded-2xl shadow p-8 text-center text-gray-500">
                    Aucun spécialiste pour cette spécialité.
                </div>
            </c:if>

            <c:if test="${not empty liste}">
                <div class="bg-white rounded-2xl shadow overflow-x-auto">
                    <table class="w-full text-sm text-left">
                        <thead class="bg-blue-100 text-blue-800">
                            <tr>
                                <th class="px-4 py-3">Nom</th>
                                <th class="px-4 py-3">Tarif</th>
                                <th class="px-4 py-3">Disponibilité</th>
                                <th class="px-4 py-3">Action</th>
                            </tr>
                        </thead>
                        <tbody class="divide-y divide-gray-100">
                            <c:forEach var="sp" items="${liste}">
                                <tr class="hover:bg-blue-50">
                                    <td class="px-4 py-3 font-medium text-gray-800">${sp.prenom} ${sp.nom}</td>
                                    <td class="px-4 py-3">${sp.tarif} DH</td>
                                    <td class="px-4 py-3 text-gray-500">Voir les créneaux</td>
                                    <td class="px-4 py-3">
                                        <a href="${pageContext.request.contextPath}/generaliste/demande-expertise?consultationId=${consultation.id}&specialisteId=${sp.id}"
                                           class="inline-block bg-blue-500 hover:bg-blue-600 text-white font-semibold rounded-lg px-4 py-1.5 transition">
                                            Choisir
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </c:if>
    </main>

</body>
</html>