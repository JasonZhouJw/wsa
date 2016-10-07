<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Case</title>
</head>
<body>
<form method="POST" action="/TestCase/addTestCase" >
   <table>
    <tr>
        <td>Interface:</td>
        <td><select name="interfaceInfoId">
            <#list interfaceList as e>
                <option value="${e.id}">${e.methodName}</option>
            </#list>
            </select>
        </td>
    </tr>
    <tr>
        <td>Request Value:</td>
        <td><textarea name="requestValue" rows="10" cols="50"></textarea></td>
    </tr>
    <tr>
       <td>Verify Value:</td>
       <td><textarea name="verification" rows="10" cols="50"></textarea></td>
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
