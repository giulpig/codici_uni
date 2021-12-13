#ifndef RANDOMROBOT_H
#define RANDOMROBOT_H

#include "Robot.h"
#include <random>

class RandomRobot : public Robot{

    public:
        RandomRobot(const Maze &m);
        void move(const Maze &m) override;

};


#endif