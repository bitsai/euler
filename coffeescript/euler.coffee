_ = require './underscore.js'

exports.factors = (x) ->
  output = []
  root = Math.sqrt x
  for y in [0...root]
    if x % y == 0
      output.push y
      output.push x / y
  if x % root == 0 then output.push root
  output

exports.fibs = (max) ->
  if max == 0 then return [0]
  output = [0, 1]
  while (x = output[output.length - 1] + output[output.length - 2]) <= max
    output.push x
  output

exports.gcd = (x, y) ->
  if y == 0 then x
  else exports.gcd y, x % y

exports.isPalindrome = (x) ->
  s = x.toString()
  for i in [0...s.length / 2]
    if s[i] != s[s.length - 1 - i]
      return false
  true

exports.isPrime = (x) ->
  (exports.factors x).length == 2

exports.lcm = (x, y) ->
  (Math.abs (x * y)) / (exports.gcd x, y)

exports.sum = (xs) ->
  add = (x, y) -> x + y
  _.reduce xs, add, 0
