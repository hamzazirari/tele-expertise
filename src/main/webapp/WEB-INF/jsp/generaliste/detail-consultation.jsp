<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Détail de la consultation</title>
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

        <h1 class="text-2xl font-bold text-gray-800 mt-2 mb-4"> Consultation</h1>

        <%-- Informations de la consultation --%>
        <div class="bg-white rounded-2xl shadow p-6 mb-6">
            <div class="grid gap-3 sm:grid-cols-2 text-sm">
                <div>
                    <span class="text-gray-500">Patient</span>
                    <p class="font-medium text-gray-800">${consultation.patient.nom} ${consultation.patient.prenom}</p>
                </div>
                <div>
                    <span class="text-gray-500">Statut</span>
                    <p class="mt-1">
                        <c:if test="${consultation.statut == 'EN_COURS'}">
                            <span class="bg-blue-100 text-blue-700 text-xs font-semibold px-3 py-1 rounded-full">${consultation.statut}</span>
                        </c:if>
                        <c:if test="${consultation.statut != 'EN_COURS'}">
                            <span class="bg-yellow-100 text-yellow-700 text-xs font-semibold px-3 py-1 rounded-full">${consultation.statut}</span>
                        </c:if>
                    </p>
                </div>
                <div>
                    <span class="text-gray-500">Motif</span>
                    <p class="font-medium text-gray-800">${consultation.motif}</p>
                </div>
                <div>
                    <span class="text-gray-500">Coût</span>
                    <p class="font-medium text-gray-800">${consultation.cout} DH</p>
                </div>
                <div class="sm:col-span-2">
                    <span class="text-gray-500">Observations</span>
                    <p class="font-medium text-gray-800">${consultation.observations}</p>
                </div>
            </div>
        </div>

        <%-- Scénario A : prise en charge directe --%>
        <div class="bg-white rounded-2xl shadow p-6 mb-6">
            <h2 class="text-lg font-semibold text-gray-800 mb-4">Prise en charge directe</h2>
            <form action="${pageContext.request.contextPath}/generaliste/detail-consultation" method="post" class="space-y-4">
                <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
                <input type="hidden" name="id" value="${consultation.id}">

                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-1">Diagnostic</label>
                    <input type="text" name="diagnostic" required
                           class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400">
                </div>
                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-1">Traitement <span class="text-gray-400">(ex : Paracétamol 1g, 3 fois/jour)</span></label>
                    <textarea name="traitement" rows="4" required
                              class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400"></textarea>
                </div>

                <button type="submit"
                        class="w-full bg-green-500 hover:bg-green-600 text-white font-semibold rounded-lg py-3 transition">
                    Clôturer
                </button>
            </form>
        </div>

        <%-- Scénario B : télé-expertise --%>
        <div class="bg-white rounded-2xl shadow p-6">
            <h2 class="text-lg font-semibold text-gray-800 mb-2">Besoin d'un spécialiste ?</h2>
            <p class="text-sm text-gray-500 mb-4">La consultation restera ouverte jusqu'à réception de l'avis.</p>
            <form action="${pageContext.request.contextPath}/generaliste/choix-specialiste" method="post">
                <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
                <input type="hidden" name="id" value="${consultation.id}">
                <button type="submit"
                        class="w-full bg-blue-500 hover:bg-blue-600 text-white font-semibold rounded-lg py-3 transition">
                    Demander avis spécialiste
                </button>
            </form>
        </div>
    </main>

</body>
</html>