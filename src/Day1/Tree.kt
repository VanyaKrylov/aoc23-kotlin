package Day1

class Tree(){
    val tree: HashMap<Char, Tree> = HashMap()
    var isLeaf: Boolean = false

    constructor(contents: List<String>) : this() {
        for (word in contents) {
            insert(tree, word.toCharArray(), ::toDigit)
        }
    }

    constructor(contents: List<String>, inverted: Boolean = false) : this() {
        val fn = if (inverted) ::toDigitCharInverted else ::toDigit
        for (word in contents) {
            insert(tree, word.toCharArray(), fn)
        }
    }

    constructor(isLeaf: Boolean) : this() {
        this.isLeaf = isLeaf
    }

    fun getLeaf(): Char {
        if (!isLeaf) {
            throw IllegalArgumentException()
        }

        return tree.keys.first()
    }
}

fun find(line: String, t: Tree = Tree(digits)): Int {
    var vocab = t
    for (c in line) {
        vocab = vocab.tree[c] ?: t.tree[c] ?: t
        if (vocab.isLeaf) {
            return vocab.getLeaf().digitToInt()
        }
    }

    throw IllegalArgumentException()
}

fun findReversed(line: String, iterators: List<Tree> = listOf(Tree(digits.map { it.reversed() }, true))): Int {
    var iters = iterators
    for (c in line) {
        val newIters = listOf(iterators[0]).toMutableList()
        for (iter in iters) {
            val vocab = iter.tree[c] ?: continue
            if (vocab.isLeaf) {
                return vocab.getLeaf().digitToInt()
            }
            newIters.add(vocab)
        }
        iters = newIters
    }

    throw IllegalArgumentException()
}

private fun insert(tree: HashMap<Char, Tree>, word: CharArray, digitChar: (CharArray) -> Char) {
    if (word.size == 1 && word[0].isDigit()) {
        val t = Tree(true)
        t.tree[word[0]] = Tree()
        tree[word[0]] = t
        return
    }

    var vertice = tree
    for ((i, c) in word.withIndex()) {
        if (vertice[c] == null) {
            vertice[c] = when(i) {
                word.size - 1 -> Tree(true)
                else -> Tree()
            }
        }

        vertice = vertice[c]!!.tree
    }

    vertice[digitChar(word)] = Tree()
}

private fun toDigit(word: CharArray): Char {
    return when (String(word)) {
        one -> '1'
        two -> '2'
        three -> '3'
        four -> '4'
        five -> '5'
        six -> '6'
        seven -> '7'
        eight -> '8'
        nine -> '9'
        else -> throw IllegalArgumentException()
    }
}

private fun toDigitCharInverted(word: CharArray): Char {
    return when (String(word).reversed()) {
        one -> '1'
        two -> '2'
        three -> '3'
        four -> '4'
        five -> '5'
        six -> '6'
        seven -> '7'
        eight -> '8'
        nine -> '9'
        else -> throw IllegalArgumentException()
    }
}

const val one = "one"
const val two = "two"
const val three = "three"
const val four = "four"
const val five = "five"
const val six = "six"
const val seven = "seven"
const val eight = "eight"
const val nine = "nine"

val digits = listOf(
    one, two, three, four, five, six, seven, eight, nine,
    "1", "2", "3", "4", "5", "6", "7", "8", "9"
)