<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Patient existant</title>
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

        <a href="${pageContext.request.contextPath}/infirmier/accueil-patient"
           class="text-sm text-blue-600 hover:text-blue-700 font-medium">← Retour</a>

        <h1 class="text-2xl font-bold text-gray-800 mt-2 mb-4"> Patient existant</h1>

        <%-- Informations du patient --%>
        <div class="bg-white rounded-2xl shadow p-6 mb-6">
            <h2 class="text-lg font-semibold text-gray-800 mb-4">Informations du patient</h2>
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
                    <span class="text-gray-500">N° sécurité sociale</span>
                    <p class="font-medium text-gray-800">${patient.numeroSecuriteSociale}</p>
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
                <div>
                    <span class="text-gray-500">Traitements en cours</span>
                    <p class="font-medium text-gray-800">${patient.traitementsEnCours}</p>
                </div>
            </div>
        </div>

        <%-- Nouveaux signes vitaux --%>
        <form action="${pageContext.request.contextPath}/infirmier/patient-existant" method="post" class="space-y-6">
            <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
            <input type="hidden" name="id" value="${patient.id}">

            <div class="bg-white rounded-2xl shadow p-6">
                <h2 class="text-lg font-semibold text-gray-800 mb-4">Nouveaux signes vitaux</h2>
                <div class="grid gap-4 sm:grid-cols-2">
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Tension artérielle <span class="text-gray-400">(ex : 12/8)</span></label>
                        <input type="text" name="tension" required
                               class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Fréquence cardiaque</label>
                        <input type="number" name="frequenceCardiaque" required
                               class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Température (°C)</label>
                        <input type="number" step="0.1" name="temperature" required
                               class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Fréquence respiratoire</label>
                        <input type="number" name="frequenceRespiratoire" required
                               class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Poids en kg <span class="text-gray-400">(optionnel)</span></label>
                        <input type="number" step="0.1" name="poids"
                               class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Taille en cm <span class="text-gray-400">(optionnel)</span></label>
                        <input type="number" step="0.1" name="taille"
                               class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400">
                    </div>
                </div>
            </div>

            <button type="submit"
                    class="w-full bg-blue-500 hover:bg-blue-600 text-white font-semibold rounded-lg py-3 transition">
                Ajouter à la file d'attente
            </button>
        </form>
    </main>

</body>
</html>