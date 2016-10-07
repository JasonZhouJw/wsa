<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test Case View</title>
</head>
<body>
   <table>
    <#list testCaseList as e>
        <tr>
            <td>${e.id?if_exists}</td>
            <td>${e.name?if_exists}</td>
            <td>${e.requestValue?if_exists}</td>
            <td>${e.verification?if_exists}</td>
        </tr>
    </#list>
    </table>
</body>
</html>
