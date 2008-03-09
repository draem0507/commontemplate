$include{"../included/encoding.gbk", "GBK"}
$include{"../included/encoding.utf8", "UTF-8"}

$include{"../included/encoding.gbk", "GBK", (testname : "梁飞")}
$include{"../included/encoding.utf8", "UTF-8", (testname : "梁飞")}

$embed{"../included/encoding.gbk", "GBK"}
$embed{"../included/encoding.utf8", "UTF-8"}

$set{testname: "梁飞"}
$embed{"../included/encoding.gbk", "GBK"}
$embed{"../included/encoding.utf8", "UTF-8"}

$display{"../included/encoding.gbk", "GBK"}
$display{"../included/encoding.utf8", "UTF-8"}