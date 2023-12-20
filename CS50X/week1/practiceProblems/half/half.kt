fun half(bill: Float, tax: Float, tip: Int): Float {
    var result: Float = bill * (tax / 100)
    var tipPercent: Float = (bill + result) * (tip.toFloat() / 100)
    result = ((bill + result + tipPercent) / 2)

    return result
}

fun getFloat(prompt: String): Float {
    print(prompt)
    val temp: String? = readLine()
    val number: Float = temp?.toFloat() ?: 0.0f

    return number
}

fun getInt(prompt: String): Int {
    print(prompt)
    val temp: String? = readLine()
    val number: Int = temp?.toInt() ?: 0

    return number
}

fun main() {
    val billAmount: Float = getFloat("Bill before tax and tip: ")
    val taxPercent: Float = getFloat("Sale Tax Percent: ")
    val tipPercent: Int = getInt("Tip percent: ")

    val totalAmount: Float = half(billAmount, taxPercent, tipPercent)

    println("You will owe $${"%.2f".format(totalAmount)} each")
}
