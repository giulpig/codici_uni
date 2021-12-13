#ifndef RANDOMROBOT_CPP
#define RANDOMROBOT_CPP

#include "RandomRobot.h"
#include <iostream>


RandomRobot::RandomRobot(const Maze &m){
    _coords = m.getStart();
}

void RandomRobot::move(const Maze &m){

    std::vector<Move> valid = validMoves(m, _coords);

    if(valid.size() == 0){
        std::cerr << "Robot bloccato" << std::endl;
        return;
    }

    switch (valid[rand()%valid.size()]){

        case UP:
            _coords._x--;
            break;
        
        case DOWN:
            _coords._x++;
            break;
        
        case RIGHT:
            _coords._y++;
            break;
        
        case LEFT:
            _coords._y--;
            break;

    };
}



#endif