<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<!--$zone{"head"}-->
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>$zone{"title"}$msg{"app.title"}$end</title>
		<script type="text/javascript">
		<!--$zone{"js"}-->
		<!--$end-->
		</script>
	<!--$end-->
	</head>
	<body>
	<!--$zone{"body"}-->
		<!--$if{locale.language != "en"}--><a href="change.action?language=en&country=US"><!--$end-->
		<!--$message{"language.en"}-->English<!--$end-->
		<!--$if{locale.language != "en"}--></a><!--$end-->
		|
		<!--$if{locale.language != "zh"}--><a href="change.action?language=zh&country=CN"><!--$end-->
		<!--$message{"language.zh"}-->Chinese<!--$end-->
		<!--$if{locale.language != "zh"}--></a><!--$end-->
		<br/>
		<br/>
		<br/>
		<!--$zone{"content"}-->
		<!--$end-->
		<br/>
		<br/>
		<br/>
		${request.requestURL}<br/>
	<!--$end-->
	</body>
<html>