package parking
import java.util.*

class Car(val reg: String, val color: String)

class Park(size: Int) {
    val spots = Array<Car?>(size) { null }
    
    fun create(spots: Int): Park {
        if (spots >= 0) { 
            println("Created a parking lot with $spots spots.")
            return Park(spots)
        }
        else return Park(0)
    }
    
    fun park(reg: String, color: String) {
        val index = spots.indexOf(null)
        if (index >= 0) {
            spots[index] = Car(reg, color)
            println("$color car parked in spot ${index + 1}.")
        }
        else println("Sorry, parking lot is full.")
    }
    
    fun leave (num: Int) {
            if (spots[num - 1] != null) {
                println("Spot $num is free.")
                spots[num - 1] = null
            }
            else println("There is no car in the spot ${num}.")
    }
    
    fun status() {
        var result = ""
        for (i in spots.indices) {
            if (spots[i] != null) result += "${i + 1} ${spots[i]?.reg} ${spots[i]?.color}\n"
        }
            if (result.isEmpty()) println("Parking lot is empty.")
    }
    
    fun regByColor(color: String) {
            var list = mutableListOf<String>()
            for (i in spots.indices) {
                if (spots[i]?.color.equals(color, true)) list.add("${spots[i]?.reg}")
            }
            if (list.isNotEmpty()) println(list.joinToString(", "))
            else println("No cars with color $color were found.")
    }
    
    fun spotsByColor(color: String) {
            var list = mutableListOf<String>()
            for (i in spots.indices) {
                if (spots[i]?.color.equals(color, true)) list.add("${i + 1}")
            }
            if (list.isNotEmpty()) println(list.joinToString(", "))
            else println("No cars with color $color were found.")
    }
    
    fun spotsByReg(reg: String) {
            var list = mutableListOf<String>()
            for (i in spots.indices) {
                if (spots[i]?.reg.equals(reg, true)) list.add("${i + 1}")
            }
             if (list.isNotEmpty()) println(list.joinToString(", "))
            else println("No cars with registration number $reg were found.")
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    var parking = Park(0)
    
    outter@ while (scanner.hasNext()) {
        var command = scanner.next().toLowerCase()

        when (command) {
            "create" -> parking = parking.create(scanner.nextInt())
            "exit" -> break@outter
            else -> if (parking.spots.isEmpty()) { 
                    println("Sorry, a parking lot has not been created.")
                    scanner.nextLine()
                    continue@outter
                }
                else when (command) {
                "park" -> parking.park(scanner.next(), scanner.next())
                "leave" -> parking.leave(scanner.nextInt())
                "status" -> parking.status()
                "reg_by_color" -> parking.regByColor(scanner.next())
                "spot_by_color" -> parking.spotsByColor(scanner.next())
                "spot_by_reg" -> parking.spotsByReg(scanner.next())
                else -> "error"
                }
        }
    }
}
