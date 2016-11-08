<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="POST" action="/ServicesInfo/create" enctype="multipart/form-data">
   <table>
    <tr>
        <td>Service:</td>
        <td><input name="service" /></td>
    </tr>
    <tr>
        <td>Name:</td>
        <td><input name="aliasName" /></td>
    </tr>
    <tr>
        <td>Environment:</td>
        <td>
            <select name="environment">
                <#list envTypes as e>
                <option value="${e.getType()}">${e.getDescription()}</option>
                </#list>
            </select>
        </td>
    </tr>
    <tr>
        <td>Protocol:</td>
        <td>
            <select name="protocol">
                <#list protocolTypes as e>
                <option value="${e.getType()}">${e.getDescription()}</option>
                </#list>
            </select>
        </td>
    </tr>
    <tr>
        <td>File:</td>
        <td><input type="file" name="file" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
    </table>
</form>
</body>
</html>
