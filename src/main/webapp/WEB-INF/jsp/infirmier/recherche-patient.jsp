<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Accueil du patient</title>
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

        <h1 class="text-2xl font-bold text-gray-800 mt-2 mb-4">️ Accueil du patient</h1>

        <%-- Formulaire de recherche --%>
        <div class="bg-white rounded-2xl shadow p-6 mb-6">
            <form action="${pageContext.request.contextPath}/infirmier/accueil-patient" method="post"
                  class="flex flex-col sm:flex-row gap-3 sm:items-end">
                <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">

                <div class="flex-1">
                    <label class="block text-sm font-medium text-gray-700 mb-1">Numéro de sécurité sociale</label>
                    <input type="text" name="numeroSecu" value="${numeroSecu}" required
                           class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400">
                </div>

                <button type="submit"
                        class="bg-blue-500 hover:bg-blue-600 text-white font-semibold rounded-lg px-6 py-2 transition">
                    Rechercher
                </button>
            </form>
        </div>

        <%-- Patient trouvé --%>
        <c:if test="${trouve == true}">
            <div class="bg-white rounded-2xl shadow p-6">
                <div class="flex items-center gap-2 mb-4">
                    <span class="bg-green-100 text-green-700 text-xs font-semibold px-3 py-1 rounded-full">
                        ✓ Patient trouvé
                    </span>
                </div>

                <div class="grid gap-3 sm:grid-cols-2 text-sm">
                    <div>
                        <span class="text-gray-500">Nom</span>
                        <p class="font-medium text-gray-800">${patient.nom}</p>
                    </div>
                    <div>
                        <span class="text-gray-500">Prénom</span>
                        <p class="font-medium text-gray-800">${patient.prenom}</p>
                    </div>
                    <div>
                        <span class="text-gray-500">Date de naissance</span>
                        <p class="font-medium text-gray-800">${patient.dateNaissance}</p>
                    </div>
                    <div>
                        <span class="text-gray-500">Mutuelle</span>
                        <p class="font-medium text-gray-800">${patient.mutuelle}</p>
                    </div>
                    <div>
                        <span class="text-gray-500">Allergies</span>
                        <p class="font-medium text-gray-800">${patient.allergies}</p>
                    </div>
                    <div>
                        <span class="text-gray-500">Antécédents</span>
                        <p class="font-medium text-gray-800">${patient.antecedents}</p>
                    </div>
                    <div class="sm:col-span-2">
                        <span class="text-gray-500">Traitements en cours</span>
                        <p class="font-medium text-gray-800">${patient.traitementsEnCours}</p>
                    </div>
                </div>

                <a href="${pageContext.request.contextPath}/infirmier/patient-existant?id=${patient.id}"
                   class="inline-block mt-6 bg-blue-500 hover:bg-blue-600 text-white font-semibold rounded-lg px-6 py-2 transition">
                    Saisir les signes vitaux et ajouter à la file d'attente
                </a>
            </div>
        </c:if>

        <%-- Patient inconnu --%>
        <c:if test="${trouve == false}">
            <div class="bg-orange-50 border border-orange-200 rounded-2xl p-6">
                <p class="text-orange-700 font-medium">Aucun patient avec ce numéro.</p>
                <a href="${pageContext.request.contextPath}/infirmier/nouveau-patient?numeroSecu=${numeroSecu}"
                   class="inline-block mt-4 bg-orange-500 hover:bg-orange-600 text-white font-semibold rounded-lg px-6 py-2 transition">
                    Créer un nouveau patient
                </a>
            </div>
        </c:if>
    </main>

</body>
</html>