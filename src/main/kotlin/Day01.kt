import java.lang.StringBuilder

fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.map { calculate(it) }.sum()
    }

    val input = readFileLines("day01-2.txt")
    part2(input).println()

    // test if implementation meets criteria from the description, like:
//    check(part1(testInput) == 1)
//
//    val input = readInput("Day01")
//    part1(input).println()
//    part2(input).println()
}

fun calculate(line: String): Int {
    val firstDigit = getDigit(line, possibleNumberFirstLetter, numbersAsLetters)
    val lastDigit = getDigit(line.reversed(), possibleNumberFirstLetterWhenReversed, numbersAsLettersReversed)

    println("firstDigit: $firstDigit, lastDigit: $lastDigit")

    return StringBuilder().append(firstDigit).append(lastDigit).toString().toInt()
}

fun getDigit(line: String, possibleDigitLetters: List<Char>, numberAsLettersMap: Map<String, Int>): Int {
    var i = 0
    var j = line.length - 1

    var digit = -1

    while (i <= j) {
        if (line[i].isDigit()) {
            digit = line[i].digitToInt()
            break
        }

        if (isPossibleDigit(line[i], possibleDigitLetters)) {
            val maybeDigit = possibleDigit(line, i, numberAsLettersMap)
            if (maybeDigit != null) {
                digit = maybeDigit
                break
            }
        }
        i++
    }
    return digit
}

val possibleNumberFirstLetter =
    listOf(
        'o',
        't',
        'f',
        's',
        'e',
        'n',
    )

val possibleNumberFirstLetterWhenReversed =
    listOf(
        'e',
        'o',
        'e',
        'r',
        'x',
        't',
        'n',
    )

val numbersAsLetters =
    mapOf(
        "one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9,
    )

val numbersAsLettersReversed = numbersAsLetters.entries.associate { (key, value) -> key.reversed() to value }

fun isPossibleDigit(c: Char, possibleDigitLetters: List<Char>): Boolean {
    return possibleDigitLetters.contains(c)
}

fun possibleDigit(
    line: String,
    index: Int,
    numberAsLetters: Map<String, Int>,
): Int? {
    val limit = if (index + 5 > line.length) line.length else index + 5
    val slice = line.substring(index, limit)
    return getDigit(slice, slice.length, numberAsLetters)
}

fun getDigit(
    slice: String,
    length: Int,
    numbersAsLetters: Map<String, Int>,
): Int? {
    if (length < 3) {
        return null
    }

    val maybeNumber = numbersAsLetters[slice]
    return maybeNumber ?: getDigit(slice.substring(0, length - 1), length - 1, numbersAsLetters)

}
