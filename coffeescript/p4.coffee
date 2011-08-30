isPalindrome = (x) ->
  s = x.toString()
  for i in [0...s.length / 2]
    if s[i] != s[s.length - 1 - i]
      return false
  return true

products = () ->
  output = []
  for x in [100...1000]
    for y in [x...1000]
      output.push (x * y)
  output

alert Math.max (x for x in products() when isPalindrome x)...
