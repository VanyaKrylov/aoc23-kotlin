import kotlin.IllegalArgumentException
import kotlin.io.path.Path
import kotlin.io.path.forEachLine

fun main() {
    var sum = 0
    Path("src/input/Day1.txt").forEachLine { line ->
        val startNumber = parseNumber(line)
        val endNumber = parseNumberInverse(line)
        sum += startNumber * 10 + endNumber
    }

    println(sum)
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