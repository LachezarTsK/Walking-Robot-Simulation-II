
package main

const EAST = "East"
const WEST = "West"
const SOUTH = "South"
const NORTH = "North"
const DISCOUNT_FOR_THE_FOUR_CORNERS = 4

type Robot struct {
    rows      int
    columns   int
    perimeter int

    /*
    If the robot stops at Point (0, 0) after a certain number of moves,
    it always faces towards South. However, this point is also the start,
    and the robot faces towards East before any moves are initiated.
    Therefore, the implementation of variable “firstMoveIsMade”,
    which is applied in method “getDir()”.
    */
    firstMoveIsMade     bool
    positionOnPerimeter int
}

func Constructor(width int, height int) Robot {
    robot := Robot{
        rows:      height,
        columns:   width,
        perimeter: 2 * (height + width) - DISCOUNT_FOR_THE_FOUR_CORNERS,
    }
    return robot
}

func (this *Robot) Step(numberOfSteps int) {
    this.firstMoveIsMade = true
    this.positionOnPerimeter = (this.positionOnPerimeter + numberOfSteps) % this.perimeter
}

func (this *Robot) GetPos() []int {
    if this.positionOnPerimeter < this.columns {
        return []int{this.positionOnPerimeter, 0}
    }
    if this.positionOnPerimeter < this.columns + this.rows - 1 {
        return []int{this.columns - 1, this.positionOnPerimeter - this.columns + 1}
    }
    if this.positionOnPerimeter < 2 * (this.columns - 1) + this.rows {
        return []int{2 * (this.columns - 1) + this.rows - this.positionOnPerimeter - 1, this.rows - 1}
    }
    return []int{0, this.perimeter - this.positionOnPerimeter}
}

func (this *Robot) GetDir() string {
    if !this.firstMoveIsMade || (this.positionOnPerimeter > 0 && this.positionOnPerimeter < this.columns) {
        return EAST
    }
    if this.positionOnPerimeter >= this.columns && this.positionOnPerimeter < this.columns + this.rows - 1 {
        return NORTH
    }
    if this.positionOnPerimeter >= this.columns + this.rows - 1 && this.positionOnPerimeter < 2 * (this.columns - 1) + this.rows {
        return WEST
    }
    return SOUTH
}
