fun main() {
    fun part1(input: List<String>): Int {
        val games = input.map { parseGame(it) }
        val bag = Bag(14, 12, 13)
        return solutionPart1(games, bag)
    }

    fun part2(input: List<String>): Int {
        val games = input.map { parseGame(it) }
        return solutionPart2(games)
    }

    val input = readFileLines("day02-2.txt")
    part2(input).println()
}

data class GameSet(val blueBalls: Int, val redBalls: Int, val greenBalls: Int)

data class Game(val id: Int, val sets: List<GameSet>)

data class Bag(val blueBalls: Int, val redBalls: Int, val greenBalls: Int)

fun solutionPart1(
    games: List<Game>,
    bag: Bag,
): Int {
    return games.filter { game ->
        game.sets.all { set ->
            set.blueBalls <= bag.blueBalls &&
                set.redBalls <= bag.redBalls &&
                set.greenBalls <= bag.greenBalls
        }
    }.map { it.id }.sum()
}

fun parseGame(line: String): Game {
    val parts = line.split(":")
    val id = parseGameId(parts[0])
    val sets = parseGameSets(parts[1])
    return Game(id, sets)
}

fun parseGameId(part: String): Int {
    return part.removePrefix("Game ").toInt()
}

fun parseGameSets(part: String): List<GameSet> {
    val sets = part.split(";")
    return sets.map { set ->
        val setPart = set.trim().split(",")
        var blueBalls = 0
        var redBalls = 0
        var greenBalls = 0
        setPart.map { sp ->
            val part = sp.trim().split(" ")
            when (part[1]) {
                "blue" -> blueBalls += part[0].toInt()
                "red" -> redBalls += part[0].toInt()
                "green" -> greenBalls += part[0].toInt()
            }
        }
        GameSet(blueBalls, redBalls, greenBalls)
    }
}

fun solutionPart2(games: List<Game>): Int {
    return games.sumOf { game ->
        val bluesRequired = game.sets.map { it.blueBalls }.max() ?: 0
        val redsRequired = game.sets.map { it.redBalls }.max() ?: 0
        val greensRequired = game.sets.map { it.greenBalls }.max() ?: 0
        bluesRequired * redsRequired * greensRequired
    }
}
