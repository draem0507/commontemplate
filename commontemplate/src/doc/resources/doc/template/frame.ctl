<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=gb2312" />
		<title>$msg{'frame.title'}</title>
		<meta name="keywords" content="$msg{'frame.keywords'}" />
		<style type="text/css">
			body {
				margin: 0px;
				padding: 0px;
				font-family: Arial, Helvetica, sans-serif;
				font-size: 12px;
				color: #000000;
				cursor: url('../images/cursor.cur');
			}

			td {
				font-size: 12px;
				height: 26;
			}

			img {
				border: 0px;
				clear: right;
			}

			input {
				font-family: Arial, Helvetica, sans-serif;
				font-size: 12px;
				color: #000000;
			}

			a:link {
				font-size: 12px;
				color: #003399;
				text-decoration: none;
			}
			a:visited {
				font-size: 12px;
				color: #003399;
				text-decoration: none;
			}
			a:active {
				font-size: 12px;
				color: #003399;
				text-decoration: none;
			}
			a:hover {
				font-size: 12px;
				color: #003399;
				text-decoration: underline;
			}
		</style>
	</head>
	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="66">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td rowspan="2" height="60"><a href="http://www.commontemplate.org"><img src="../images/banner.gif" alt="Common Template Engine" border="0" width="400" height="50" /></a></td>
							<td height="40" valign="top" align="right">
								<!--$if{locale.language == 'en'}-->
								English
								<!--$else-->
								<a href="../en/${pageUrl}">English</a>
								<!--$end-->
								|
								<!--$if{locale.language == 'zh'}-->
								&#20013;&#25991;
								<!--$else-->
								<a href="../zh/${pageUrl}">&#20013;&#25991;</a>
								<!--$end-->
								--
								<!--$if{release}-->
								$msg{"frame.release"}
								|
								<a href="../build/${locale.language}/${pageUrl}">$msg{"frame.build"}</a>
								<!--$else-->
								<a href="../../${locale.language}/${pageUrl}">$msg{"frame.release"}</a>
								|
								$msg{"frame.build"}
								<!--$end-->
								&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td height="20" valign="bottom" align="right">
								<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; border-style: solid; border-color: #CCCCCC;">
									<tr align="center">
										<!--$for{menu : topMenus}-->
										<td width="90" height="20" style="font-weight: bold;"><a href="${menu.url}" target="${menu.remoteUrl ? '_blank' : '_self'}">$msg{menu.key}</a></td>
										<!--$end-->
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="2" height="6" style="background-color: #005B88;"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="200" valign="top">
								<table width="100%" border="1" style="border-collapse: collapse; border-style: solid; border-color: #CCCCCC;" cellspacing="1" cellpadding="2">
									<!--$for{menu : leftMenus}-->
									<tr>
										<td height="24" style="background-color: #F4F4F4;">
											<table width="100%" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td width="4" height="22" style="background-color: #0055CC;"></td>
													<td width="4"></td>
													<td style="font-weight: bold;">$msg{menu.key}</td>
												</tr>
											</table>
										</td>
									</tr>
									<!--$for{child : menu.children}-->
									<tr>
										<td height="24" style="background-color: #F4F4F4;">
											<table width="100%" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td width="16"></td>
													<td><a href="${child.url}" target="${child.remoteUrl ? '_blank' : '_self'}">$msg{child.key}</a></td>
												</tr>
											</table>
										</td>
									</tr>
									<!--$end-->
									<!--$end-->
								</table>
							</td>
							<td width="20">&nbsp;</td>
							<td align="left" valign="top">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td height="32" align="left" valign="bottom"><b><font size="3">$msg{"page." + pageName + ".title"}</font></b></td>
									<tr>
									<tr>
										<td align="left"><hr width="30%" align="left" /></td>
									<tr>
									<tr>
										<td align="left">
											<!--$zone{"content"}-->
											<!--$end-->
										</td>
									<tr>
								</table>
							</td>
							<td width="20">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="6" align="center"><hr width="94%" align="center"/></td>
			</tr>
			<tr>
				<td height="24" align="center">
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								$msg{'frame.copyright'} <a href="about.html">$msg{'frame.commontemplate.team'}</a>
							</td>
							<td width="100" align="right">
								<a href="http://www.commontemplate.org"><img src="../images/logo.gif" border="0" /></a>
							</td>
							<td width="100" align="right">
								<a target="_blank" href="http://sourceforge.net" target="_blank"><img src="../images/sourceforge.jpg" border="0" /></a>
							</td>
						<tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>