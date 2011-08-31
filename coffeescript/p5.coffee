euler = require './euler.coffee'
_ = require './underscore.js'

console.log _.reduce [1...21], euler.lcm, 1
