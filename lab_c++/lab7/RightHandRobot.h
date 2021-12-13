#ifndef RIGHTHANDROBOT_H
#define RIGHTHANDROBOT_H

#include "Robot.h"
#include <random>



class BlockedRobot{
    public:
        BlockedRobot(){};
};



class RightHandRobot : public Robot{

    public:
        RightHandRobot(const Maze &m);
        void move(const Maze &m) override;
        Move wallPos(const Maze &m) const;

        enum State{
            STARTED,
            SERCHINGWALL,
            NEXTTOWALL
        };

    private:
        State _state;
        Move _initial;
        Move _lastmove;
};


#endif