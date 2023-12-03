import day1.*
import kotlin.IllegalArgumentException
import kotlin.io.path.Path
import kotlin.io.path.forEachLine

fun main() {
    // Part 1
    var sum = 0
    Path("src/input/Day1.txt").forEachLine { line ->
        val startNumber = parseNumber(line)
        val endNumber = parseNumberInverse(line)
        sum += startNumber * 10 + endNumber
    }

    println(sum)

    // Part 2, method 1
    var sum2 = 0
    Path("src/input/Day1.txt").forEachLine { line ->
        val startNumber = VocabularyTreeIterator.find(line)
        val endNumber = VocabularyTreeIterator.findReversed(line.reversed())
        sum2 += startNumber * 10 + endNumber
    }

    println(sum2)

    // Part 2, method 2
    var sum3 = 0
    Path("src/input/Day1.txt").forEachLine { line ->
        var startNumber = -1
        var startNumberIndex = line.length + 1
        for (d in digits) {
            val index = line.indexOf(d)
            if (index != -1 && index < startNumberIndex) {
                startNumberIndex = index
                startNumber = toDigit(d)
            }
        }

        var endNumber = -1
        var endNumberIndex = -1
        for (d in digits) {
            val index = line.lastIndexOf(d)
            if (index > endNumberIndex) {
                endNumberIndex = index
                endNumber = toDigit(d)
            }
        }
        sum3 += startNumber * 10 + endNumber
    }
    println(sum3)
}

fun parseNumber(s: String): Int {
    for (c in s) {
        if (c.isDigit()) {
            return c.digitToInt()
        }
    }

    throw IllegalArgumentException()
}

fun parseNumberInverse(s : String): Int {
    for (i in s.length-1 downTo 0 step 1) {
        if (s[i].isDigit()) {
            return s[i].digitToInt()
        }
    }

    throw IllegalArgumentException()
}

private fun toDigit(word: String): Int {
    return when (word) {
        one  -> 1
        two -> 2
        three -> 3
        four -> 4
        five -> 5
        six -> 6
        seven -> 7
        eight -> 8
        nine -> 9
        "1"  -> 1
        "2" -> 2
        "3" -> 3
        "4" -> 4
        "5" -> 5
        "6" -> 6
        "7" -> 7
        "8" -> 8
        "9" -> 9
        else -> throw IllegalArgumentException()
    }
}
