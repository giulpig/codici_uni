#ifndef RIGHTHANDROBOT_CPP
#define RIGHTHANDROBOT_CPP

#include "RightHandRobot.h"
#include <iostream>




RightHandRobot::RightHandRobot(const Maze &m){
    _coords = m.getStart();
    _state = STARTED;
}

Robot::Move RightHandRobot::wallPos(const Maze &m) const{
    if(m.isWall(Coords{_coords._x-1,_coords._y}) && !m.isWall(Coords{_coords._x,_coords._y-1})){
        return LEFT;
    }
    if(m.isWall(Coords{_coords._x+1,_coords._y}) && !m.isWall(Coords{_coords._x,_coords._y+1})){
        return RIGHT;
    }
    if(m.isWall(Coords{_coords._x,_coords._y-1}) && !m.isWall(Coords{_coords._x+1,_coords._y})){
        return DOWN;
    }
    if(m.isWall(Coords{_coords._x,_coords._y+1}) && !m.isWall(Coords{_coords._x-1,_coords._y-1})){
        return UP;
    }
    return INVALID;
}


void RightHandRobot::move(const Maze &m){

    std::vector<Move> valid = validMoves(m, _coords);

    if(valid.size() == 0){
        std::cerr << "Robot bloccato" << std::endl;
        throw(BlockedRobot());
        return;
    }

    switch(_state){
        case STARTED:
        {   //scope for temp
            Move temp = valid[rand()%valid.size()];
            moveDirection(temp);
            _initial = temp;
            _state = SERCHINGWALL;
        }
        break;

        case SERCHINGWALL:
            moveDirection(_initial);
            if(wallPos(m) != INVALID){
                _state = NEXTTOWALL;
            }
        break;

        case NEXTTOWALL:
            if(!m.isWall(_coords + _lastmove)){
                moveDirection(_lastmove);
                break;
            }
            moveDirection(wallPos(m));
            _lastmove = wallPos(m);
        break;


    };

    
}



#endif