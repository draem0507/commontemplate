child header
$extends{"../included/super.mm"}
	$overzone{"head"}
		This is child head!
	$end
$end
$extends{"../included/super.mm"}
	$overzone{"body"}
		This is child body!
	$end
$end
$extends{"../included/middle.mm"}
	$overzone{"body"}
		This is child body!
	$end
$end
$extends{"../included/middle.mm"}
	$overzone{"head"}
		This is child head!
	$end
	$overzone{"body"}
		$superzone
		This is child body!
	$end
$end
child footer