#ifndef ROBOT_CPP
#define ROBOT_CPP


#include "Robot.h"

const Coords Robot::getCoords(void) const{
    return _coords;
}


bool Robot::win(const Maze &m) const{
    return m.getEnd() == _coords;
}


void Robot::moveDirection(Move m){
    
    switch(m){
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


const std::vector<Robot::Move> Robot::validMoves(const Maze& m, const Coords& c){
    std::vector<Move> ret;
    
    if(_coords._x > 0 && !m.isWall(c.applyMove(UP))){
        ret.push_back(UP);
    }
    if(_coords._x < SIZE-1 && !m.isWall(c.applyMove(DOWN))){
        ret.push_back(DOWN);
    }
    if(_coords._y > 0 && !m.isWall(c.applyMove(LEFT))){
        ret.push_back(LEFT);
    }
    if(_coords._y < SIZE-1 && !m.isWall(c.applyMove(RIGHT))){
        ret.push_back(RIGHT);
    }

    return ret;
}



#endif