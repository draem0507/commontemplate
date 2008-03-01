$if{false}
	xxx
$else
	yyy
$end

$if{allow}
	xxx
$elseif{users[0].name == "liangfei"}
	yyy
$else
	zzz
$end

$if{allow}
	xxx
	$if{users[0].name == "liangfei"}
		yyy
	$end
$else
	zzz
$end

$if{users[0].name=="zhangyou"}
	xxx
$elseif{users[0].name == "liangfei"}
	yyy
$elseif{users[0].name == "james"}
	zzz
$end
