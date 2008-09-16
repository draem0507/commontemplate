<html>
	<body>
		${null}
		vvvvv
		$for{num : (1..3)}
			${num}中文
		$end

		users size: ${users.size}

		$time{"for_run_time"}
		<!--$if{users != null && users.size > 0}-->
		<table>
			<!--$for{user : users[0..2]}-->
			<tr>
				<td>
					${for.index + 1}
				</td>
				<td>
					<a href="detail.jsp?userid=${user.id}">
						${user['name']}
					</a>
				</td>
				<td>
					${user.creationDate}
				</td>
			</tr>
			<!--$end-->
		</table>
		<!--$end-->
		$end

		for run time: ${for_run_time}ns

		$# line comment!${for.index + 1/////

		<!--$!-->
		$if{users != null && users.size > 0}
		${user.creationDate ~ 'yyyy-MM-dd'}
		<!--!$-->

		<!--$*-->
		comment:
		${for.index + 1///
		$end
		<!--*$-->

		<br/>aaaaaa<br/>

		xxxx
		$keyword{"liangfei", "bobo"}
		<table m:if="users != null && users.size > 0">
			<tr m:for="user : users">
				<td>
					<span m:out="for.index + 1">1</span>
				</td>
				<td>
					<a href="detail.jsp?userid=${user.id}">
						<span m:out="user.name">james</span>
					</a>
				</td>
				<td>
					$msg{"title", user.name}
				</td>
				<td>
					<span m:out="user.creationDate.toDateString">2007-01-01</span>
				</td>
			</tr>
		</table>
		$end
		<!--$if{true}-->
		if
			<!--$if{false}-->
			2if
			<!--$else-->
			2else
			<!--$end-->
		<!--$elseif{false}-->
		elseif1
		<!--$elseif{false}-->
		elseif2
		<!--$else-->
		else1
		<!--$end-->

		${-2..9}
		${'a'..'d'}
		${'September'..'February'}

		${context["session"].allow}
		${true && context["session"].allow}
		${context["session"].allow && true}

		$* $stop *$

		$compress
		cc
		   dd

		$escape{"html"}
			<table>
			</table>
		$end

		     ee
		   ff
		$end

		$cycle{color: ("#00FFEE", "#DDCC77", "#22EE99")}
		$for{row : (1..9)}
			${color}
		$end

		$# ${null.xxx}

		$# ${null.xxx.yyy + "aaa"}
		$# ${"bbb" + null.xxx.yyy}


		${.now}
		${"2007-02-01".toDate > .now}
	</body>
</html>
