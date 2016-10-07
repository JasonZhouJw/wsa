<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Case</title>
</head>
<body>
<form method="POST" action="/TestCase/editTestCase" >
   <table>
    <tr>
        <td>Interface:</td>
        <td>${testCase.id}</td>
    </tr>
    <tr>
        <td>Request Value:</td>
        <td><textarea name="requestValue" rows="10" cols="50" value="${testCase.requestValue}"></textarea></td>
    </tr>
    <tr>
       <td>Verify Value:</td>
       <td><textarea name="verification" rows="10" cols="50" value="${testCase.verification}"></textarea></td>
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
