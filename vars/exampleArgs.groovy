def call(Map argsMap, Object block) {
    def defaultMap = [ namedArg1: 'foos', namedArg2: 'ball' ]
    def args = defaultMap << argsMap
    println "Arguments:"
    args.each { key, val -> println "$key = $val" }
    println "Block:"
    println block
}