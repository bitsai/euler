euler = require './euler.coffee'
_ = require './underscore.js'

products = () ->
  output = []
  for x in [100...1000]
    for y in [x...1000]
      output.push (x * y)
  output

console.log _.max _.select products(), euler.isPalindrome
