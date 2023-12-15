fun main()
{
    print("What's your name? ")
    val name: String? = readLine()

    print("Where do you live? ")
    val address: String? = readLine()

    println("Hello, ${name}, from ${address}!")
}
