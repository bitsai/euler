euler = require './euler.coffee'

sumOfSquares = (xs) ->
  euler.sum (x * x for x in xs)

squareOfSum = (xs) ->
  sum = euler.sum xs
  sum * sum

console.log Math.abs ((sumOfSquares [1..100]) - (squareOfSum [1..100]))
