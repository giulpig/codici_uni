#ifndef ROBOT_H
#define ROBOT_H


#include "Maze.h"
#include <vector>

class Robot{

    public:

        enum Move{
            UP = 0,
            DOWN = 1,
            RIGHT = 2,
            LEFT = 3,
            INVALID = -1
        };

        virtual void move(const Maze&) = 0;
        const std::vector<Move> validMoves(const Maze&, const Coords&);
        const Coords getCoords(void) const;
        void moveDirection(Move);
        bool win(const Maze&) const;

    protected:
        Robot() {};
        Robot(const Robot&) = delete;
        Robot& operator=(const Robot&) = delete;
        Coords _coords;
        

};



#endif