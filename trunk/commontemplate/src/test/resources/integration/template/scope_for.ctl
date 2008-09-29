$for{i : 1..2}
	$if{i == 1}
		$for{j : 5..7}
			$if{j == 6}
				$for{k : 10..13}
					$if{k == 12}
						-${for}
						--${super.for}
						---${super.super.for}
						----${super.super.super.for}
					$end{if}
				$end{for}
			$end{if}
		$end{for}
	$end{if}
$end{for}