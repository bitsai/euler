sum = (xs...) ->
  output = 0
  output += x for x in xs
  output

alert sum (x for x in [0...1000] when x % 3 == 0 or x % 5 == 0)...
