_ = require './underscore.js'

exports.fibs = (max) ->
  if max == 0 then return [0]
  output = [0, 1]
  while (x = output[output.length - 1] + output[output.length - 2]) <= max
    output.push x
  output

exports.gcd = (x, y) ->
  if y == 0 then x
  else exports.gcd y, x % y

exports.lcm = (x, y) ->
  (Math.abs (x * y)) / (exports.gcd x, y)

exports.isPalindrome = (x) ->
  s = x.toString()
  for i in [0...s.length / 2]
    if s[i] != s[s.length - 1 - i]
      return false
  true

exports.factors = (x) ->
  output = []
  root = Math.sqrt x
  for y in [0...root]
    if x % y == 0
      output.push y
      output.push x / y
  if x % root == 0 then output.push root
  output

exports.isPrime = (x) ->
  (exports.factors x).length == 2

exports.nthPrime = (n) ->
  primes = []
  x = 2
  if n < 1 then return null
  while primes.length < n
    if exports.isPrime x then primes.push x
    x = x + 1
  primes.pop()

exports.primeSieve = (n) ->
  xs = (x for x in [0...n] by 1)
  xs[0] = xs[1] = null
  for x in [2..Math.sqrt n] by 1
    if xs[x] != null
      for p in [(x * x)..n] by x
        xs[p] = null
  _.reject xs, _.isNull

exports.sum = (xs) ->
  add = (x, y) -> x + y
  _.reduce xs, add, 0

exports.product = (xs) ->
  multiply = (x, y) -> x * y
  _.reduce xs, multiply, 1

exports.partition = (n, step, coll) ->
  partitions = []
  xs = coll
  while xs.length >= n
    partitions.push xs.slice 0, n
    xs = xs.slice step
  partitions
