
#include <vector>
#include <string>
using namespace std;

class Robot {

    struct DIRECTIONS {
            inline static const string EAST = "East";
            inline static const  string WEST = "West";
            inline static const  string SOUTH = "South";
            inline static const  string NORTH = "North";
    };

    static const int DISCOUNT_FOR_THE_FOUR_CORNERS = 4;

    int rows{};
    int columns{};
    int perimeter{};

    /*
    If the robot stops at Point (0, 0) after a certain number of moves,
    it always faces towards South. However, this point is also the start,
    and the robot faces towards East before any moves are initiated.
    Therefore, the implementation of variable “firstMoveIsMade”,
    which is applied in method “getDir()”.
     */
    bool firstMoveIsMade{};
    int positionOnPerimeter{};

public:
    Robot(int width, int height) :rows{ height }, columns{ width } {
        perimeter = 2 * (rows + columns) - DISCOUNT_FOR_THE_FOUR_CORNERS;
    }

    void step(int numberOfSteps) {
        firstMoveIsMade = true;
        positionOnPerimeter = (positionOnPerimeter + numberOfSteps) % perimeter;
    }

    vector<int> getPos() const {
        if (positionOnPerimeter < columns) {
            return { positionOnPerimeter, 0 };
        }
        if (positionOnPerimeter < columns + rows - 1) {
            return  { columns - 1, positionOnPerimeter - columns + 1 };
        }
        if (positionOnPerimeter < 2 * (columns - 1) + rows) {
            return { 2 * (columns - 1) + rows - positionOnPerimeter - 1, rows - 1 };
        }
        return  { 0, perimeter - positionOnPerimeter };
    }

    string getDir() const {
        if (!firstMoveIsMade || (positionOnPerimeter > 0 && positionOnPerimeter < columns)) {
            return DIRECTIONS::EAST;
        }
        if (positionOnPerimeter >= columns && positionOnPerimeter < columns + rows - 1) {
            return DIRECTIONS::NORTH;
        }
        if (positionOnPerimeter >= columns + rows - 1 && positionOnPerimeter < 2 * (columns - 1) + rows) {
            return DIRECTIONS::WEST;
        }
        return DIRECTIONS::SOUTH;
    }
};
