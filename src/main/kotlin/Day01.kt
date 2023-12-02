import java.lang.StringBuilder

fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.map { calculate(it) }.sum()
    }

    val input = listOf("two1nine",
    "eightwothree",
    "abcone2threexyz",
    "xtwone3four",
    "4nineeightseven2",
            "zoneight234",
    "7pqrstsixteen")

    part2(input).println()

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("day01-2")
//    check(part1(testInput) == 1)
//
//    val input = readInput("Day01")
//    part1(input).println()
//    part2(input).println()
}

fun calculate(line: String): Int {
    var i = 0
    var j = line.length - 1

    var firstDigit = '0'
    var lastDigit = '0'

    while (i < j) {
        if (line[i].isDigit()) {
            firstDigit = line[i]
            break
        }

        if (isPossibleDigit(line[i])) {
            var maybeDigit = possibleDigit(line, i)
            if (maybeDigit != null) {
                firstDigit = maybeDigit
                break
            }
        }
        i++
    }

    while (j > 0) {
        if (line[j].isDigit()) {
            lastDigit = line[j]
            break
        }

        if (isPossibleLastDigit(line[j])) {
            var maybeDigit = possibleLastDigit(line, j)
            if (maybeDigit != null) {
                lastDigit = maybeDigit
                break
            }
        }
        j--
    }

    println("firstDigit: $firstDigit, lastDigit: $lastDigit")

    return StringBuilder().append(firstDigit).append(lastDigit).toString().toInt()
}

fun possibleLastDigit(line: String, index: Int): Char {
    TODO()
}

val possibleNumberFirstLetter = listOf(
    'o', 't', 't', 'f', 's', 'e', 'n'
)

val possibleLastNumberLastLetter = listOf(
    'e', 'o', 'e', 'r', 'x', 't', 'n'
)

val numbersAsLetters = mapOf(
    "one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9
)

fun isPossibleDigit(c: Char): Boolean {
    return possibleNumberFirstLetter.contains(c)
}

fun isPossibleLastDigit(c: Char): Boolean {
    return possibleLastNumberLastLetter.contains(c)
}

fun possibleDigit(line: String, index: Int): Char? {
    val limit = if (index + 5 > line.length) line.length else index + 5
    val slice = line.substring(index, limit)
    return getDigit(slice, slice.length)
}

fun getDigit(slice: String, length: Int): Char? {
    if (length < 3) {
        return null
    }

    val maybeNumber = numbersAsLetters[slice]
    if (maybeNumber != null) {
        return maybeNumber.toChar()
    }

    return getDigit(slice.substring(0, length - 1), length - 1)
}
