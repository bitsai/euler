gcd = (x, y) ->
  if y == 0 then x
  else gcd y, x % y

lcm = (x, y) ->
  (Math.abs (x * y)) / (gcd x, y)

lcmAll = (x, y, zs...) ->
  output = lcm x, y
  for z in zs
    output = lcm output, z
  output

alert lcmAll [1...21]...
