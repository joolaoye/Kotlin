val NUM_ITEMS = 10

data class menu_item(
    var item: String,
    var price: Float
)

var menu = Array<menu_item>(NUM_ITEMS){menu_item("", 0f)}

fun main() {
    add_items()

    print("\nWelcome to Beach Burger Shack!\n")
    print("Choose from the following menu to order. Press enter when done.\n\n")

    for (i in 0 until NUM_ITEMS) {
        print("${menu[i].item}: $${"%.2f".format(menu[i].price)}\n")
    }

    print("\n")

    var total: Float = 0f

    while (true) {
        val item: String = get_string("Enter a food item: ")

        if (item.length == 0) {
            print("\n")
            break
        }

        total += get_cost(item)
    }

    print("Your total cost is: $${"%.2f".format(total)}\n")
}

fun get_string(prompt: String) : String {
    print(prompt)
    val input = readln()

    return input
}

fun add_items() {
    menu[0] = menu_item("Burger", 9.5f)
    menu[1] = menu_item("Vegan Burger", 11f)
    menu[2] = menu_item("Hot Dog", 5f)
    menu[3] = menu_item("Cheese Dog" ,7f)
    menu[4] = menu_item("Fries",5f)
    menu[5] = menu_item("Cheese Fries", 6f)
    menu[6] = menu_item("Cold Pressed Juice", 7f)
    menu[7] = menu_item("Cold Brew", 3f)
    menu[8] = menu_item("Water", 2f)
    menu[9] = menu_item("Soda" ,2f)
}

fun get_cost(item: String) : Float {
    var index = 0

    for (i in 0 until NUM_ITEMS) {
        if (item.lowercase() == menu[i].item.lowercase()) {
            index = i
            break
        }
    }

    return menu[index].price
}