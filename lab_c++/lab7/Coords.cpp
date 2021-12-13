#ifndef COORDS_CPP
#define COORDS_CPP


#include "Coords.h"

Coords& Coords::operator=(const Coords &o){
    
    if(this == &o){
        return *this;
    }

    _x = o._x;
    _y = o._y;

    return *this;
}



const Coords Coords::operator+(int m) const{
    return applyMove(m);
}



const Coords Coords::applyMove(int m) const{
    switch (m){
        case 0: //UP
            return Coords(_x-1, _y);
            break;

        case 1: //DOWN
            return Coords(_x+1, _y);
            break;

        case 2: //RIGHT
            return Coords(_x, _y+1);
            break;

        case 3: //LEFT
            return Coords(_x, _y-1);
            break;
    };
}

bool operator==(const Coords &o1, const Coords &o2){
    return o1._x == o2._x && o1._y == o2._y;
}


#endif