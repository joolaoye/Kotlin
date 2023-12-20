val POINTS: IntArray = intArrayOf(1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10)

fun main() {
    val word1 = get_string("Player 1: ")
    val word2 = get_string("Player 2: ")

    val score1 = compute_score(word1)
    val score2 = compute_score(word2)

    if (score1 > score2) {
        println("Player 1 wins!")
    }

    else if (score1 < score2) {
        println("Player 2 wins!")
    }

    else{
        println("Tie!")
    }
}

fun get_string(prompt: String) : String {
    print(prompt)
    val input = readln()

    return input
}

fun compute_score(word: String) : Int {
    var score = 0

    for (item in word) {
        if (item.isLetter()) {
            val asciiValue: Int = (item.toInt() % 32) - 1
            score += POINTS[asciiValue]
        }
    }

    return score
}
