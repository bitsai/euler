sum = (xs...) ->
  output = 0
  output += x for x in xs
  output

fibs = (max) ->
  if max < 0 then return []
  if max == 0 then return [0]
  output = [0, 1]
  while (n = output[output.length - 2] + output[output.length - 1]) <= max
    output.push n
  output

alert sum (x for x in fibs 4000000 when x % 2 == 0)...
