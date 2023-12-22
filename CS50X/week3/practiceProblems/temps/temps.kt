val NUM_CITIES = 10

data class avg_temp(
    var city: String,
    var temp: Int
)

val temps = Array<avg_temp>(NUM_CITIES){avg_temp(" ", 0)}

fun main() {

    temps[0].city = "Austin"
    temps[0].temp = 97

    temps[1].city = "Boston"
    temps[1].temp = 82

    temps[2].city = "Chicago"
    temps[2].temp = 85

    temps[3].city = "Denver"
    temps[3].temp = 90

    temps[4].city = "Las Vegas"
    temps[4].temp = 105

    temps[5].city = "Los Angeles"
    temps[5].temp = 82

    temps[6].city = "Miami"
    temps[6].temp = 97

    temps[7].city = "New York"
    temps[7].temp = 85

    temps[8].city = "Phoenix"
    temps[8].temp = 107

    temps[9].city = "San Francisco"
    temps[9].temp = 66

    sort_cities()

    println("\nAvergae July Temperatures by City\n")

    temps.forEach { struct -> println("${struct.city}: ${struct.temp}") }
}

fun sort_cities() {
    for (i in 0 until NUM_CITIES - 1) {

        var j = i - 1
        var current = temps[i]

        while (j > -1  && current.temp > temps[j].temp) {
            temps[j + 1] = temps[j]
            j -= 1
        }

        temps[j + 1] = current
    }
}
