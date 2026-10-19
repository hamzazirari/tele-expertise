<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Accueil</title>
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

        <%-- Bienvenue --%>
        <div class="bg-white rounded-2xl shadow p-6 mb-6">
            <h1 class="text-2xl font-bold text-gray-800">
                Bienvenue ${sessionScope.utilisateur.prenom} ${sessionScope.utilisateur.nom}
            </h1>
            <span class="inline-block mt-2 bg-blue-100 text-blue-700 text-xs font-semibold px-3 py-1 rounded-full">
                ${sessionScope.utilisateur.role}
            </span>
        </div>

        <div class="grid gap-4 sm:grid-cols-2">

            <%-- Infirmier --%>
            <c:if test="${sessionScope.utilisateur.role == 'INFIRMIER'}">
                <a href="${pageContext.request.contextPath}/infirmier/accueil-patient"
                   class="bg-white rounded-2xl shadow p-6 hover:shadow-md hover:-translate-y-0.5 transition block">
                    <div class="text-3xl mb-2">️</div>
                    <h2 class="font-semibold text-gray-800">Accueillir un patient</h2>
                    <p class="text-sm text-gray-500">Rechercher ou créer un dossier</p>
                </a>
                <a href="${pageContext.request.contextPath}/infirmier/liste-patients"
                   class="bg-white rounded-2xl shadow p-6 hover:shadow-md hover:-translate-y-0.5 transition block">
                    <div class="text-3xl mb-2"></div>
                    <h2 class="font-semibold text-gray-800">Patients du jour</h2>
                    <p class="text-sm text-gray-500">Voir la liste des patients enregistrés</p>
                </a>
            </c:if>

            <%-- Généraliste --%>
            <c:if test="${sessionScope.utilisateur.role == 'GENERALISTE'}">
                <a href="${pageContext.request.contextPath}/generaliste/file-attente"
                   class="bg-white rounded-2xl shadow p-6 hover:shadow-md hover:-translate-y-0.5 transition block">
                    <div class="text-3xl mb-2"></div>
                    <h2 class="font-semibold text-gray-800">File d'attente</h2>
                    <p class="text-sm text-gray-500">Prendre en charge un patient</p>
                </a>
                <a href="${pageContext.request.contextPath}/generaliste/consultations"
                   class="bg-white rounded-2xl shadow p-6 hover:shadow-md hover:-translate-y-0.5 transition block">
                    <div class="text-3xl mb-2"></div>
                    <h2 class="font-semibold text-gray-800">Mes consultations</h2>
                    <p class="text-sm text-gray-500">Consultations ouvertes</p>
                </a>
            </c:if>

            <%-- Spécialiste --%>
            <c:if test="${sessionScope.utilisateur.role == 'SPECIALISTE'}">
                <div class="bg-white rounded-2xl shadow p-6 sm:col-span-2">
                    <div class="text-3xl mb-2"></div>
                    <h2 class="font-semibold text-gray-800">Espace spécialiste</h2>
                    <p class="text-sm text-gray-500">À venir</p>
                </div>
            </c:if>

        </div>
    </main>

</body>
</html>