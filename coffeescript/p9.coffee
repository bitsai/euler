for a in [1...1000]
  for b in [a + 1...1000]
    c = Math.sqrt (a * a + b * b)
    if a + b + c == 1000
      console.log a * b * c
      return
