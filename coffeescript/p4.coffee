euler = require './euler.coffee'
_ = require './underscore.js'

products = () ->
  output = []
  for x in [100..999]
    for y in [x..999]
      output.push (x * y)
  output

console.log _.max _.select products(), euler.isPalindrome
