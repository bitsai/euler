_ = require './underscore.js'

exports.fibs = (n) ->
  fibs = [0, 1]
  while (f = fibs[fibs.length - 1] + fibs[fibs.length - 2]) < n
    fibs.push f
  _.select fibs, (f) -> f < n

exports.gcd = (x, y) ->
  if y == 0 then x else exports.gcd y, x % y

exports.lcm = (x, y) ->
  (Math.abs x * y) / (exports.gcd x, y)

exports.isPalindrome = (x) ->
  s = x.toString()
  for i in [0...s.length / 2]
    if s[i] != s[s.length - 1 - i]
      return false
  true

exports.factors = (x) ->
  facts = []
  root = Math.sqrt x
  for y in [1...root]
    if x % y == 0
      facts.push y
      facts.push x / y
  if x % root == 0 then facts.push root
  facts

exports.isPrime = (x) ->
  (exports.factors x).length == 2

exports.nthPrime = (n) ->
  primes = []
  x = 2
  while primes.length < n
    if exports.isPrime x then primes.push x
    x = x + 1
  primes.pop()

exports.primeSieve = (n) ->
  if n <= 2 then return []
  primes = [2].concat (x for x in [3...n] by 2)
  root = Math.sqrt n
  i = 1
  while (p = primes[i]) <= root
    if p != null
      for x in [p * p...n] by 2 * p
        primes[Math.floor (x / 2)] = null
    i = i + 1
  _.reject primes, _.isNull

exports.sum = (xs) ->
  add = (x, y) -> x + y
  _.reduce xs, add, 0

exports.product = (xs) ->
  mult = (x, y) -> x * y
  _.reduce xs, mult, 1

exports.partition = (n, step, coll) ->
  parts = []
  xs = coll
  while xs.length >= n
    parts.push xs.slice 0, n
    xs = xs.slice step
  parts
