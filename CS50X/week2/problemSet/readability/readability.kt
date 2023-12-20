import kotlin.math.roundToInt

fun main() {
    val string = get_string("Text: ")

    val letter = count_letters(string)
    val word = count_words(string)
    val sentence = count_sentences(string)

    val L = (letter / word.toFloat()) * 100
    val S = (sentence / word.toFloat()) * 100

    var index = 0.0588 * L - 0.296 * S - 15.8
    val grade = index.roundToInt()

    if (grade < 1) {
        println("Before Grade 1")
    }

    else if (grade > 16) {
        println("Grade 16+")
    }

    else {
        println("Grade ${grade}")
    }

}

fun get_string(prompt: String) : String {
    print(prompt)
    val input = readln()

    return input
}

fun count_letters(string: String) : Int {
    var len = 0
    for (item in string) {
        if (item.isLetter()) {
            len += 1
        }
    }

    return len
}

fun count_words(string: String) : Int {
    var space = true
    var len = 0

    for (item in string) {
        if (item.isWhitespace()) {
            space = true
        }

        if (item.isLetter() && space) {
            len += 1
            space = false
        }
    }

    return len
}

fun count_sentences(string: String) : Int {
    var word = count_words(string)
    val punctuations = arrayOf('.', '!', '?')
    var len = 0

    if (word < 1) {
        return 0
    }

    for (item in string) {
        if (punctuations.contains(item)) {
            len += 1
        }
    }

    return len
}