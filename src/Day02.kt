fun solveDay02() {
    // test if implementation meets criteria from the description, like:
    val input = readInput("Day02")
    println(part1Day02(input))
    println(part2Day02(input))
}

fun part1Day02(input: List<String>): Int {
    var pos = 0 to 0 // x, y ,z / forward, sidewards, depth
    input.forEach { line ->
        val (axis, amount) = line.split(" ").take(2)
        when (axis) {
            "forward" -> pos = pos.copy(first = (pos.first + amount.toInt()))
            "down" -> pos = pos.copy(second = (pos.second + amount.toInt()))
            "up" -> pos = pos.copy(second = (pos.second - amount.toInt()))
        }
    }
    return pos.second * pos.first
}

fun part2Day02(input: List<String>): Int {
    var pos = Triple(0, 0, 0) // x, aim, depth / forward, aim
    input.forEach { line ->
        val (axis, amount) = line.split(" ").take(2)
        when (axis) {
            "forward" -> {
                pos = pos.copy(
                    first = (pos.first + amount.toInt()),
                    third = (pos.third + (pos.second * amount.toInt()))
                )
            }
            "down" -> pos = pos.copy(second = (pos.second + amount.toInt()))
            "up" -> pos = pos.copy(second = (pos.second - amount.toInt()))
        }
    }
    return pos.third * pos.first
}
