$for{user: users}
  $if{for.index == 2}
    $break
  $end
  ${for.count}. ${user.name}
$end
