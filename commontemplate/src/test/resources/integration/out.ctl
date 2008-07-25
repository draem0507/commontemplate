${"abc" ^~ "abc"}
${"abcdef" ^~ "abc"}
${"AbC" ^~ "abc"}
${"AbCdEf" ^~ "abc"}
${"xAbC" ^~ "abc"}
${"xAbCdEf" ^~ "abc"}

${"abc" $~ "abc"}
${"abcdef" $~ "abc"}
${"AbC" $~ "abc"}
${"AbCdEf" $~ "abc"}
${"xAbC" $~ "abc"}
${"xAbCdEf" $~ "abc"}

${"abc" *~ "abc"}
${"abcdef" *~ "abc"}
${"AbC" *~ "abc"}
${"AbCdEf" *~ "abc"}
${"xAbC" *~ "abc"}
${"xAbCdEf" *~ "abc"}
${"xAbyCdEf" *~ "abc"}
