package presentation.ui

import kotlin.math.abs

class ConsoleInterface {

    /**
     *  Prints the given message to the standard output stream.
     */
    fun print(message: String) = println(message)

    /**
     *  Reads a line of input from the standard input stream and checks for valid strings
     */
    fun readString(message: String): String {
        println(message)
        val value = readLine()
        return if (value.isNullOrBlank()) readString(message) else value
    }

    /**
     * Reads a line of input from the standard input stream and converts it to an integer value
     */
    fun readInt(message: String): Int {
        println(message)
        val value = readLine()
        return try {
            abs(value!!.toInt())
        } catch (e: Exception) {
            readInt(message)
        }
    }

    /**
     * Reads a line of input from the standard input stream and converts it to a Double value
     */
    fun readDouble(message: String): Double {
        println(message)
        val value = readLine()
        return try {
            abs(value!!.toDouble())
        } catch (e: Exception) {
            readDouble(message)
        }
    }
}