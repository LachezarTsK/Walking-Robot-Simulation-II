
class Robot {

    static DIRECTIONS = {
        EAST: "East",
        WEST: "West",
        SOUTH: "South",
        NORTH: "North"
    }

    static DISCOUNT_FOR_THE_FOUR_CORNERS = 4;

    /**
     * @param {number} width
     * @param {number} height
     */
    constructor(width, height) {
        this.rows = height;
        this.columns = width;
        this.perimeter = 2 * (this.rows + this.columns) - Robot.DISCOUNT_FOR_THE_FOUR_CORNERS;

        /*
         If the robot stops at Point (0, 0) after a certain number of moves, 
         it always faces towards South. However, this point is also the start, 
         and the robot faces towards East before any moves are initiated. 
         Therefore, the implementation of variable “firstMoveIsMade”, 
         which is applied in method “getDir()”.
         */
        this.firstMoveIsMade = false;
        this.positionOnPerimeter = 0;
    }

    /** 
     * @param {number} numberOfSteps
     * @return {void}
     */
    step(numberOfSteps) {
        this.firstMoveIsMade = true;
        this.positionOnPerimeter = (this.positionOnPerimeter + numberOfSteps) % this.perimeter;
    }

    /**
     * @return {number[]}
     */
    getPos() {
        if (this.positionOnPerimeter < this.columns) {
            return [this.positionOnPerimeter, 0];
        }
        if (this.positionOnPerimeter < this.columns + this.rows - 1) {
            return [this.columns - 1, this.positionOnPerimeter - this.columns + 1];
        }
        if (this.positionOnPerimeter < 2 * (this.columns - 1) + this.rows) {
            return [2 * (this.columns - 1) + this.rows - this.positionOnPerimeter - 1, this.rows - 1];
        }
        return [0, this.perimeter - this.positionOnPerimeter];
    }

    /**
     * @return {string}
     */
    getDir() {
        if (!this.firstMoveIsMade || (this.positionOnPerimeter > 0 && this.positionOnPerimeter < this.columns)) {
            return Robot.DIRECTIONS.EAST;
        }
        if (this.positionOnPerimeter >= this.columns && this.positionOnPerimeter < this.columns + this.rows - 1) {
            return Robot.DIRECTIONS.NORTH;
        }
        if (this.positionOnPerimeter >= this.columns + this.rows - 1 && this.positionOnPerimeter < 2 * (this.columns - 1) + this.rows) {
            return Robot.DIRECTIONS.WEST;
        }
        return Robot.DIRECTIONS.SOUTH;
    }
}
