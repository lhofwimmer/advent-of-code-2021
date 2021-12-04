class Day03 : Day {
    override fun solve() {
        val input = readInput("Day03")
        println(part1(input))
        println(part2(input))
    }

    override fun part1(input: List<String>): Int {
        var gamma = ""
        for (i in input.first().indices) {
            gamma += input.reduceDigits(i)
                .maxByOrNull { it.value }?.key ?: 1
        }
        val epsilon = "1".repeat(input.first().length).toInt(2) xor gamma.toInt(2)

        return gamma.toInt(2) * epsilon
    }

    override fun part2(input: List<String>): Int {

        var inputOxygen = input
        var inputCO2 = input
        for (i in input.first().indices) {
            if (inputOxygen.size > 1) {
                val digits = inputOxygen.reduceDigits(i)
                val mostCommonDigit = if (digits[1]!! >= digits[0]!!) 1 else 0
                inputOxygen = inputOxygen.filterCommonDigit(mostCommonDigit, i)
            }

            if (inputCO2.size > 1) {
                val digits = inputCO2.reduceDigits(i)
                val leastCommonDigit = if (digits[0]!! <= digits[1]!!) 0 else 1
                inputCO2 = inputCO2.filterCommonDigit(leastCommonDigit, i)
            }
        }
        return inputOxygen.first().toInt(2) * inputCO2.first().toInt(2)
    }

    private fun List<String>.reduceDigits(index: Int): Map<Int, Int> {
        return this.map { it.toCharArray()[index].digitToInt() }
            .groupBy { it }
            .mapValues { it.value.size }
    }

    private fun List<String>.filterCommonDigit(commonDigit: Int, index: Int) =
        this.filter { number -> number.toCharArray()[index].digitToInt() == commonDigit }
}
