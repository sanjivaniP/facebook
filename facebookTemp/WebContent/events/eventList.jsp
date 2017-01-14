<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/facebook.css">
<script>
	function handle(e) {
		if (e.keyCode === 13) {
			$(this).submit();
		}

		return false;
	}
</script>
</head>


<body>
	<div class="panel panel-default">
		<table>
			<tr>
				<td width="93%"><h3>
						<b>&nbsp;&nbsp;&nbsp;Events</b>
					</h3></td>
				<td>
					<form action="eventCreateTemp">
						<!-- 						<input type="submit" value="+ Create event"
							onclick="javascript:window.open('eventCreateEnter.action','CreateEvent');" />
 -->
						<input type="submit" value="+ Create event" />
					</form>
				</td>
			</tr>
		</table>
		<hr>
		<h3>
			<b>&nbsp;&nbsp;&nbsp;Upcoming Events</b>
		</h3>
		<br>

		<s:iterator var="parent" value="eventD">

			<table>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td bgcolor="#E3E4FA"><s:property value="dt" /></td>
				</tr>

			</table>
			<s:iterator var="child1" value="#parent.birthday"><!-- Birthday List -->

				<s:if test="%{#child1!=null}">
					<%!int var1 = 0;%>
					<table>
						<tr>
							<td>

								<table>

									<tr>

										<td>
											<%
												var1 = var1 + 1;
											%> <%
 	if (var1 != 0) {
 %>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Birthdays
											<%
												} else {
											%>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<%
												}
											%>


										</td>

										<td><s:if test="%{dt=='Today' }">
												<table>
													<tr>
														<td><s:property value="#child1.profilePicAlbumId" />
														</td>
														<td>
															<table>
																<tr>
																	<td rowspan="2"><img
																		src="image?photoId=<s:property value="picId" />"
																		height="60" width="60"></td>
																	<td><a
																		href="<s:url action='profileTempAction_hype' >  
    <s:param name="profileId"> <s:property value="#child1.profileId"/> </s:param>
    </s:url>"
																		target="dynamic" class="commentNameFont"><font
																			size=3><span class="hover"> <s:property
																						value="#child1.firstName" /> <s:property
																						value="#child1.lastName" /></span></font></a></td>
																</tr>

																<tr>
																	<td><s:if test="%{#child1.flagB==false }">

																			<form action="postWishEvent">
																				<input type="hidden" name="profileId"
																					value="<s:property value="#child1.profileId"/>" />
																				<input type="hidden" name="flagEventWished"
																					value="true" />
																				<s:if
																					test="%{#child1.gender=='Male' || #child1.gender=='male' || #child1.gender=='MALE'}">

																					<input type="text" name="wish" style="width: 150%;"
																						placeholder="Write a birthday wish on his timeline..."
																						onkeypress="handle" />
																				</s:if>
																				<s:elseif
																					test="%{#child1.gender=='Female' || #child1.gender=='female' || #child1.gender=='FEMALE'}">

																					<input type="text" name="wish" style="width: 150%;"
																						placeholder="Write a birthday wish on her timeline..."
																						onkeypress="handle" />
																				</s:elseif>

																			</form>

																		</s:if> <s:else>
																						You wrote on <s:property
																				value="#child1.firstName " />'s timeline.
					
					</s:else></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</s:if> <s:else>
												<table>
													<tr>
														<td><a
															href="<s:url action="profileTempAction_hype" ><s:param name="profileId"> <s:property value="#child1.profileId"/> </s:param>
    </s:url>"
															target="dynamic"
															title="<s:property value="#child1.firstName" /> <s:property
									value="#child1.lastName" /> 's birthday"><img
																src="image?photoId=<s:property value="picId" />"
																height="60" width="60"></a></td>
													</tr>
												</table>

											</s:else></td>


									</tr>

								</table>

							</td>
						</tr>
					</table>

				</s:if>
			</s:iterator>

			<s:iterator var="child2" value="#parent.event"><!-- Event List -->
				<s:if test="%{#child2!=null}">
					<table>
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td><s:property value="#child2.startT" /></td>

							<td>

								<table>
									<tr>
										<td>
											<table>
												<tr>

													<td><a
														href=" <s:url action="eventPageTemp">
		  <s:param name="eventId"><s:property value="#child2.profileEventID"/> 
		  </s:param>
    	  </s:url>"
														target="dynamic"> <img src="Images/event-default.png"
															height="60" width="60" /></a></td>
													<td>

														<table>

															<tr>
																<td><a
																	href="<s:url action="eventPageTemp">
		  <s:param name="eventId"><s:property value="#child2.profileEventID"/> 
		  </s:param>
    	  </s:url>"
																	target="dynamic"> <s:property
																			value="#child2.eventName" /></a></td>
															</tr>
															<tr>
																<td><s:property value="#child2.location" /></td>
															</tr>
															<tr>
																<td><s:if test="%{#child2.rsvpStatusId==4 }">

																		<s:property value="#child2.host" /> invited you. </s:if></td>
															</tr>
														</table>
													</td>

												</tr>

											</table>
										</td>
									</tr>

								</table>
							</td>
						</tr>
					</table>
				</s:if>
			</s:iterator>
		</s:iterator>
	</div>
</body>
</html>
