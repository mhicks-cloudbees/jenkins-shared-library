def call(Map args, Object block) {
    println "Arguments:"
    args.each { key, val -> println "$key = $val" }
    println "Block:"
    println block
}