class Day04 : Day {
    override fun solve() {
        val input = readInput("Day04")
//        println(part1(input))
        println(part2(input))
    }

    override fun part1(input: List<String>): Int {
        val bingoNumbers = input.first().split(",")
        var boards = getBingoBoards(input)
        bingoNumbers.forEach { drawnNumber ->
            boards = boards.map { board ->
                board.map { line ->
                    line.map { number ->
                        if (drawnNumber == number) "x" else number
                    }
                }
            }
            winningBingos(boards, drawnNumber.toInt())
        }

        return 0
    }

    private fun winningBingos(boards: List<List<List<String>>>, lastNumber: Int): List<List<List<String>>> {
        val bingos = mutableListOf<List<List<String>>>()
        boards.forEach { board ->
            if (isBingo(board)) {
                bingos.add(board)
            }
        }
        return bingos
    }

    private fun calculateResult(board: List<List<String>>, lastNumber: Int): Int {
        return board.flatten().filter { it != "x" }.sumOf { it.toInt() }.times(lastNumber)
    }

    private fun isBingo(board: List<List<String>>): Boolean {
        board.forEach { line ->
            if (line.all { it == "x" } ||
                line.indices.any { Xindex ->
                    (0..4).all { Yindex -> board[Yindex][Xindex] == "x" }
                }) return true
        }

        return false
    }

    private fun getBingoBoards(input: List<String>): List<List<List<String>>> {
        return input.drop(1) // list of bingo boards of list of bingo lines of list of bingo numbers
            .filter { it.isNotBlank() }
            .map {
                it.split(" ").filter { str -> str.isNotBlank() }
            }
            .chunked(5)

    }

    private fun List<List<List<String>>>.singleBingos(): List<List<List<String>>> {
        return this.filter { board ->
            var winningColRows = 0


            board.forEach { line -> if (line.all { it == "x" }) winningColRows++ }
            (0..4).forEach { Xindex ->
                if ((0..4).all { Yindex -> board[Yindex][Xindex] == "x" }) {
                    winningColRows++
                }
            }
            winningColRows == 1
        }
    }

    override fun part2(input: List<String>): Int {
        val bingoNumbers = input.first().split(",")
        var boards = getBingoBoards(input)

        bingoNumbers.forEach { drawnNumber ->
            boards = boards.map { board ->
                board.map { line ->
                    line.map { number ->
                        if (drawnNumber == number) "x" else number
                    }
                }
            }

            val winningBingos = winningBingos(boards, drawnNumber.toInt())
            if (winningBingos.size == boards.size) {
                winningBingos.singleBingos().forEach { board ->
                    board.forEach {
                        println(it)
                    }
                    println("With number: $drawnNumber")
                    println("Result: ${calculateResult(board, drawnNumber.toInt())}")
                    println()
                }
                return 0
            }
        }
        return 0
    }
}
