child header
$extends{"../included/super.mm"}
	$zone{"head"}
		This is child head!
	$end
$end
$extends{"../included/super.mm"}
	$zone{"body"}
		This is child body!
	$end
$end
$extends{"../included/middle.mm"}
	$zone{"body"}
		This is child body!
	$end
$end
$extends{"../included/middle.mm"}
	$zone{"head"}
		This is child head!
	$end
	$zone{"body"}
		$superzone
		This is child body!
	$end
$end
child footer