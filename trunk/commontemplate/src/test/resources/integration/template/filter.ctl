$set{testHtml="<b>test<b>"}
$filter{x => x.escapeHtml}
<a>xxx${testHtml}yyy</a>
$end
---------
$filterall{x => x.escapeHtml}
<a>xxx${testHtml}yyy</a>
$end