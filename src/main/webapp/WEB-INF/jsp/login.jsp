<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
</head>
<body>
    <h2>Connexion</h2>

    <%-- Message d'erreur (affiché seulement s'il existe) --%>
    <c:if test="${not empty erreur}">
        <p style="color: red;">${erreur}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <p>
            <label>Email :</label><br>
            <input type="email" name="email" required>
        </p>
        <p>
            <label>Mot de passe :</label><br>
            <input type="password" name="motDePasse" required>
        </p>
        <button type="submit">Se connecter</button>
    </form>
</body>
</html>