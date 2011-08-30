factors = (x) ->
  output = []
  root = Math.sqrt x
  for y in [0...root]
    if x % y == 0
      output.push y
      output.push x / y
  if x % root == 0 then output.push root
  output

isPrime = (x) ->
  2 == (factors x).length

alert Math.max (x for x in factors 600851475143 when isPrime x)...
