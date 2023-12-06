import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Int {
        val games = parseInput(input)
        return solutionPart1(games)
    }

    fun part2(input: List<String>): Int {
        return input.map { calculate(it) }.sum()
    }

    val input = readFileLines("day04-1.txt")
    part1(input).println()
}

object Day04 {
    data class Game(val winNumbers: Set<Int>, val myNumbers: Set<Int>)
}


fun parseInput(input: List<String>): List<Day04.Game> {
    return input.map { parseLine(it)  }
}

// TODO refactor using regex
// TODO understand used regex \\s+
fun parseLine(line: String): Day04.Game {
    val removedCardNumber = line.split(":")[1]
    val parts = removedCardNumber.split("|")
    val winNumbers = parts[0].trim().split("\\s+".toRegex()).map { it.toInt() }.toSet()
    val myNumbers = parts[1].trim().split("\\s+".toRegex()).map { it.toInt() }.toSet()
    return Day04.Game(winNumbers, myNumbers)
}

fun solutionPart1(games: List<Day04.Game>): Int {
    return games.map { game ->
        game.myNumbers.intersect(game.winNumbers).size
    }.sumOf { matches ->
        2.0.pow(matches.toDouble() - 1).toInt()
    }
}