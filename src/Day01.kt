fun solveDay01() {
    // test if implementation meets criteria from the description, like:
    val input = readInput("Day01")
    println(part1Day01(input))
    println(part2Day01(input))
}

fun part1Day01(input: List<String>): Int {
    var numIncreases = 0
    for(i in 1 until input.size) {
        if(input[i].toInt() > input[i-1].toInt()) numIncreases++;
    }
    return numIncreases
}

fun part2Day01(input: List<String>): Int {
    var numIncreases = 0
    val toInt = input.map { it.toInt() }
    for(i in 3 until input.size) {
        val sum1 = toInt[i-3] + toInt[i-2] + toInt[i-1]
        val sum2 = toInt[i-2] + toInt[i-1] + toInt[i]
        if(sum2 > sum1) numIncreases++
    }
    return numIncreases
}
