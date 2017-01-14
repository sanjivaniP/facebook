<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>People Who Liked This</title>
</head>
<body>
	<table>
		<s:iterator value="peopleLiked">
			<tr>

				<td width="80"><a href=""><img
						src="image?photoId=<s:property value="photoId" />" height="60" width="60"></a></td>
				<td ><a href="" class="h2"><span class="hover"><s:property
								value="userName" /></span></a></td>

			</tr>
		</s:iterator>
	</table>
</body>
</html>