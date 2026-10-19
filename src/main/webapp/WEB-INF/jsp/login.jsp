<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Connexion</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-blue-50 min-h-screen flex items-center justify-center p-4">

    <div class="bg-white w-full max-w-md rounded-2xl shadow-lg p-8">

        <div class="text-center mb-6">
            <div class="text-5xl mb-2"></div>
            <h1 class="text-2xl font-bold text-gray-800">Télé-expertise médicale</h1>
            <p class="text-gray-500 text-sm mt-1">Connectez-vous pour continuer</p>
        </div>

        <%-- Message d'erreur (affiché seulement s'il existe) --%>
        <c:if test="${not empty erreur}">
            <div class="bg-red-50 border border-red-200 text-red-700 text-sm rounded-lg px-4 py-3 mb-4">
                ${erreur}
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/login" method="post" class="space-y-4">
            <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">

            <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">Email</label>
                <input type="email" name="email" required
                       class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400">
            </div>

            <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">Mot de passe</label>
                <input type="password" name="motDePasse" required
                       class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400">
            </div>

            <button type="submit"
                    class="w-full bg-blue-500 hover:bg-blue-600 text-white font-semibold rounded-lg py-2 transition">
                Se connecter
            </button>
        </form>
    </div>

</body>
</html>