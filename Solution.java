
public class Robot {

    private static class DIRECTIONS {

        static final String EAST = "East";
        static final String WEST = "West";
        static final String SOUTH = "South";
        static final String NORTH = "North";
    }

    private static final int DISCOUNT_FOR_THE_FOUR_CORNERS = 4;

    private final int rows;
    private final int columns;
    private final int perimeter;

    /*
    If the robot stops at Point (0, 0) after a certain number of moves, 
    it always faces towards South. However, this point is also the start, 
    and the robot faces towards East before any moves are initiated. 
    Therefore, the implementation of variable “firstMoveIsMade”, 
    which is applied in method “getDir()”.
     */
    private boolean firstMoveIsMade;
    private int positionOnPerimeter;

    public Robot(int width, int height) {
        rows = height;
        columns = width;
        perimeter = 2 * (rows + columns) - DISCOUNT_FOR_THE_FOUR_CORNERS;
    }

    public void step(int numberOfSteps) {
        firstMoveIsMade = true;
        positionOnPerimeter = (positionOnPerimeter + numberOfSteps) % perimeter;
    }

    public int[] getPos() {
        if (positionOnPerimeter < columns) {
            return new int[]{positionOnPerimeter, 0};
        }
        if (positionOnPerimeter < columns + rows - 1) {
            return new int[]{columns - 1, positionOnPerimeter - columns + 1};
        }
        if (positionOnPerimeter < 2 * (columns - 1) + rows) {
            return new int[]{2 * (columns - 1) + rows - positionOnPerimeter - 1, rows - 1};
        }
        return new int[]{0, perimeter - positionOnPerimeter};
    }

    public String getDir() {
        if (!firstMoveIsMade || (positionOnPerimeter > 0 && positionOnPerimeter < columns)) {
            return DIRECTIONS.EAST;
        }
        if (positionOnPerimeter >= columns && positionOnPerimeter < columns + rows - 1) {
            return DIRECTIONS.NORTH;
        }
        if (positionOnPerimeter >= columns + rows - 1 && positionOnPerimeter < 2 * (columns - 1) + rows) {
            return DIRECTIONS.WEST;
        }
        return DIRECTIONS.SOUTH;
    }
}
