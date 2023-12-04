import kotlin.io.path.Path
import kotlin.io.path.forEachLine

fun main() {
    // Part 1
    // Could use a more fitting RE from Part 2, but too lazy for this :P
    val wordRe = Regex("\\w+")
    var sum = 0
    Path("src/input/Day2.txt").forEachLine { line ->
        val matches = wordRe.findAll(line).toList()
        var gameID = matches[1].value.toInt()
        for (m in matches) {
            val word = m.value
            val number = word.toIntOrNull() ?: continue
            when {
                number == 13 && (m.next()?.value?.contains(Regex(red)) == true) -> {
                    gameID = 0
                    break
                }
                number == 14 && (m.next()?.value?.contains(Regex("$red|$green")) == true) -> {
                    gameID = 0
                    break
                }
                number > 14 &&  (m.next()?.value?.contains(Regex("$red|$green|$blue")) == true) -> {
                    gameID = 0
                    break
                }
                else -> continue
            }
        }
        sum += gameID
    }
    println(sum)

    //Part 2
    val numToColorRe = Regex("([0-9]+)\\s+(red|green|blue)")
    var sum2 = 0

    Path("src/input/Day2.txt").forEachLine { line ->
        val colorToNum = mapOf(green to 0, red to 0, blue to 0).toMutableMap()
        for (match in numToColorRe.findAll(line)) {
            val (numString, color) = match.destructured
            val num = numString.toInt()

            if (num > colorToNum[color]!!) {
                colorToNum[color] = num
            }
        }
        sum2 += colorToNum.values.reduce { acc, i -> acc * i }
    }
    println(sum2)
}

const val green = "green"
const val red = "red"
const val blue = "blue"