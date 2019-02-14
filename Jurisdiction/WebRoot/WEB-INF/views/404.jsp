<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<TITLE>Sorry! 你访问的页没找到</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<STYLE type=text/css>
TD {
	FONT-SIZE: 9pt;
	FONT-FAMILY: "Arial", "Helvetica, " sans-serif "
}

BODY {
	FONT-SIZE: 9pt;
	FONT-FAMILY: "Arial", "Helvetica", "sans-serif"
}

.tbl1 {
	BORDER-RIGHT: #3f5294 1px solid;
	BORDER-TOP: #3f5294 1px solid;
	FONT-SIZE: 9pt;
	BORDER-LEFT: #3f5294 1px solid;
	BORDER-BOTTOM: #3f5294 1px solid
}

.td1 {
	BORDER-RIGHT: #ffffff 0px solid;
	BORDER-TOP: #ffffff 1px solid;
	BORDER-LEFT: #ffffff 1px solid;
	BORDER-BOTTOM: #ffffff 0px solid
}
</STYLE>

<STYLE type=text/css>
A {
	COLOR: #000000;
	TEXT-DECORATION: none
}

A:hover {
	COLOR: #ff0000;
	TEXT-DECORATION: none
}
</STYLE>

<STYLE type=text/css>
.style6 {
	FONT-FAMILY: "Courier New", Courier, mono
}
</STYLE>

</HEAD>
<BODY bgColor=#ffffff>
	<P>&nbsp;</P>
	<TABLE height=382 cellSpacing=0 cellPadding=0 width=470 align=center
		border=0>
		<TBODY>
			<TR>
				<TD vAlign=top
					background="<c:out value="${pageContext.request.contextPath}"/>/images/bg.gif"><BR>
					<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
						<TBODY>
							<TR>
								<TD width="14%">&nbsp;</TD>
								<TD width="86%">
									<TABLE style="FILTER: glow(color=#ffffff, strength=5)"
										width="100%" align=center>
										<TBODY>
											<TR>
												<TD align=middle height=14><SPAN class=style6><FONT
														color=#ff0000 size=6>Sorry!</FONT>
												</SPAN>
												</TD>
											</TR>
										</TBODY>
									</TABLE>
								</TD>
							</TR>
							<TR>
								<TD>&nbsp;</TD>
								<TD>
									<DIV align=center>
										<TABLE cellSpacing=2 cellPadding=0 width="100%" align=center
											border=0>
											<TBODY>
												<tr>
													<td>Sorry! 你访问的页没找到</td>
												</tr>
												<tr>
													<td><span style="font-size: 20;color:red;text-align: center;">${error}</span></td>
												</tr>
											</TBODY>
										</TABLE>
										<BR>
									</DIV>
								</TD>
							</TR>
						</TBODY>
					</TABLE>
					<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
						<TBODY>
							<TR>
								<TD width="38%">&nbsp;</TD>
								<TD width="17%">
									<DIV align=right>
										<A href="http://www.yelangsem.com/"><IMG height=38
											src="<c:out value="${pageContext.request.contextPath}"/>/images/jt.gif"
											width=56 border=0>
										</A>
									</DIV>
								</TD>
								<TD width="45%">
									<DIV align=center>
										<A href="<c:out value="${pageContext.request.contextPath}"/>/main"><FONT color=#ff0000>[由此返回主页]</FONT>
										</A>
									</DIV>
								</TD>
							</TR>
						</TBODY>
					</TABLE>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
	<P>&nbsp;</P>
	<CENTER></CENTER>
</object></layer></span></div></table></body></html><!-- adsok -->


</BODY></HTML>
