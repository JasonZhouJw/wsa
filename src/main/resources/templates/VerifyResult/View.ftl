<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Verify Result View</title>
</head>
<body>
   <table>
    <#list verifyResultList as e>
        <tr>
            <td>${e.id?if_exists}</td>
            <td>${e.message?if_exists}</td>
        </tr>
    </#list>
    </table>
</body>
</html>
