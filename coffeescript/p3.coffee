euler = require './euler.coffee'
_ = require './underscore.js'

console.log _.max _.select (euler.factors 600851475143), euler.isPrime
