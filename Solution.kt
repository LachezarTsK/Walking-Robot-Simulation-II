
class Robot(width: Int, height: Int) {

    private companion object {
        const val EAST = "East"
        const val WEST = "West"
        const val SOUTH = "South"
        const val NORTH = "North"
        const val DISCOUNT_FOR_THE_FOUR_CORNERS = 4
    }

    private val rows = height
    private val columns = width
    private val perimeter = 2 * (rows + columns) - DISCOUNT_FOR_THE_FOUR_CORNERS

    /*
    If the robot stops at Point (0, 0) after a certain number of moves,
    it always faces towards South. However, this point is also the start,
    and the robot faces towards East before any moves are initiated.
    Therefore, the implementation of variable “firstMoveIsMade”,
    which is applied in method “getDir()”.
     */
    private var firstMoveIsMade = false
    private var positionOnPerimeter = 0

    fun step(numberOfSteps: Int) {
        firstMoveIsMade = true
        positionOnPerimeter = (positionOnPerimeter + numberOfSteps) % perimeter
    }

    fun getPos(): IntArray {
        if (positionOnPerimeter < columns) {
            return intArrayOf(positionOnPerimeter, 0)
        }
        if (positionOnPerimeter < columns + rows - 1) {
            return intArrayOf(columns - 1, positionOnPerimeter - columns + 1)
        }
        if (positionOnPerimeter < 2 * (columns - 1) + rows) {
            return intArrayOf(2 * (columns - 1) + rows - positionOnPerimeter - 1, rows - 1)
        }
        return intArrayOf(0, perimeter - positionOnPerimeter)
    }

    fun getDir(): String {
        if (!firstMoveIsMade || (positionOnPerimeter > 0 && positionOnPerimeter < columns)) {
            return EAST
        }
        if (positionOnPerimeter >= columns && positionOnPerimeter < columns + rows - 1) {
            return NORTH
        }
        if (positionOnPerimeter >= columns + rows - 1 && positionOnPerimeter < 2 * (columns - 1) + rows) {
            return WEST
        }
        return SOUTH
    }
}
