<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Nouvelle consultation</title>
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

        <a href="${pageContext.request.contextPath}/generaliste/file-attente"
           class="text-sm text-blue-600 hover:text-blue-700 font-medium">← Retour</a>

        <h1 class="text-2xl font-bold text-gray-800 mt-2 mb-4"> Nouvelle consultation</h1>

        <%-- Dossier du patient --%>
        <div class="bg-white rounded-2xl shadow p-6 mb-6">
            <h2 class="text-lg font-semibold text-gray-800 mb-4">Dossier du patient</h2>
            <div class="grid gap-3 sm:grid-cols-2 text-sm">
                <div>
                    <span class="text-gray-500">Nom</span>
                    <p class="font-medium text-gray-800">${file.patient.nom} ${file.patient.prenom}</p>
                </div>
                <div>
                    <span class="text-gray-500">Date de naissance</span>
                    <p class="font-medium text-gray-800">${file.patient.dateNaissance}</p>
                </div>
                <div>
                    <span class="text-gray-500">Mutuelle</span>
                    <p class="font-medium text-gray-800">${file.patient.mutuelle}</p>
                </div>
                <div>
                    <span class="text-gray-500">Allergies</span>
                    <p class="font-medium text-gray-800">${file.patient.allergies}</p>
                </div>
                <div>
                    <span class="text-gray-500">Antécédents</span>
                    <p class="font-medium text-gray-800">${file.patient.antecedents}</p>
                </div>
                <div>
                    <span class="text-gray-500">Traitements en cours</span>
                    <p class="font-medium text-gray-800">${file.patient.traitementsEnCours}</p>
                </div>
            </div>
        </div>

        <%-- Signes vitaux --%>
        <div class="bg-white rounded-2xl shadow p-6 mb-6">
            <h2 class="text-lg font-semibold text-gray-800 mb-4">Signes vitaux</h2>
            <div class="grid gap-3 grid-cols-2 sm:grid-cols-3 text-sm">
                <div class="bg-blue-50 rounded-lg p-3">
                    <span class="text-gray-500">Tension</span>
                    <p class="font-semibold text-gray-800">${file.signesVitaux.tensionArterielle}</p>
                </div>
                <div class="bg-blue-50 rounded-lg p-3">
                    <span class="text-gray-500">Fréq. cardiaque</span>
                    <p class="font-semibold text-gray-800">${file.signesVitaux.frequenceCardiaque}</p>
                </div>
                <div class="bg-blue-50 rounded-lg p-3">
                    <span class="text-gray-500">Température</span>
                    <p class="font-semibold text-gray-800">${file.signesVitaux.temperature}</p>
                </div>
                <div class="bg-blue-50 rounded-lg p-3">
                    <span class="text-gray-500">Fréq. respiratoire</span>
                    <p class="font-semibold text-gray-800">${file.signesVitaux.frequenceRespiratoire}</p>
                </div>
                <div class="bg-blue-50 rounded-lg p-3">
                    <span class="text-gray-500">Poids</span>
                    <p class="font-semibold text-gray-800">${file.signesVitaux.poids}</p>
                </div>
                <div class="bg-blue-50 rounded-lg p-3">
                    <span class="text-gray-500">Taille</span>
                    <p class="font-semibold text-gray-800">${file.signesVitaux.taille}</p>
                </div>
            </div>
        </div>

        <%-- Formulaire de consultation --%>
        <form action="${pageContext.request.contextPath}/generaliste/nouvelle-consultation" method="post" class="space-y-6">
            <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
            <input type="hidden" name="fileId" value="${file.id}">

            <div class="bg-white rounded-2xl shadow p-6">
                <h2 class="text-lg font-semibold text-gray-800 mb-4">Consultation</h2>
                <div class="space-y-4">
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Motif</label>
                        <input type="text" name="motif" required
                               class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400">
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-1">Observations <span class="text-gray-400">(examen clinique, symptômes)</span></label>
                        <textarea name="observations" rows="5" required
                                  class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400"></textarea>
                    </div>
                    <p class="text-sm text-gray-600">
                        Coût de la consultation :
                        <span class="font-semibold text-gray-800">150 DH</span>
                    </p>
                </div>
            </div>

            <button type="submit"
                    class="w-full bg-blue-500 hover:bg-blue-600 text-white font-semibold rounded-lg py-3 transition">
                Créer la consultation
            </button>
        </form>
    </main>

</body>
</html>