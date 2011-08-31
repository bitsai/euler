euler = require './euler.coffee'
_ = require './underscore.js'

console.log euler.sum _.select [0...1000], (x) -> x % 3 == 0 or x % 5 == 0
