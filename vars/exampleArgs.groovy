def call(Map argsMap, Object block) {
    
    // Handle defaults in arguments
    def defaultMap = [ namedArg1: 'foos', namedArg2: 'ball', namedArg3: 'table' ]
    def args = defaultMap << argsMap

    println "Arguments: " + args.toMapString()
    println "Block:" + block
}
