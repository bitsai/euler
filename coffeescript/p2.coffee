euler = require './euler.coffee'
_ = require './underscore.js'

console.log euler.sum _.select (euler.fibs 4000000), (x) -> x % 2 == 0
