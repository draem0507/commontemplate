$macro{"button"}
<input type="button" name="${name}" value="${value}" />
$end
$macro{"text"}
<input type="text" name="${name}" value="${value}" />
$end
$macro{"form"}
<form action="${action}">
$text{name: name, value: value}
$button{name:"submit",value:"submit"}
</form>
$end