#ifndef MAZE_CPP
#define MAZE_CPP

#include "Maze.h"
#include <fstream>

Maze::Maze(const std::string &filename){

    std::ifstream fin{filename};

    char reading;

    for(int i=0; i<SIZE; i++){
        for(int j=0; j<SIZE+1; j++){
            fin.get(reading);

            if(j==SIZE)
                continue;

            switch(reading){
                case 'S':
                    _table[i][j] = S;
                    _Scoord = Coords{i,j};
                    break;

                case 'E':
                    _table[i][j] = E;
                    _Ecoord = Coords{i,j};
                    break;

                case ' ':
                    _table[i][j] = FREE;
                    break;

                case '*':
                    _table[i][j] = WALL;
                    break;
            }            
        }
    }

}

const bool Maze::isWall(const Coords &c) const{
    return _table[c._x][c._y] == WALL;
}

const Coords& Maze::getStart() const{
    return _Scoord;
}

const Coords& Maze::getEnd(void) const{
    return _Ecoord;
}


void Maze::print(void) const{
    for(int i=0; i<SIZE; i++){
        for(int j=0; j<SIZE; j++){
            switch (_table[i][j]){
                case WALL:
                    std::cout << '*';
                    break;
                case FREE:
                    std::cout << ' ';
                    break;
                case S:
                    std::cout << 'S';
                    break;
                case E:
                    std::cout << 'E';
                    break;
            };
        }
        std::cout << std::endl;
    }
}


const Maze::Tile Maze::whatIs(const Coords &c) const{
    return _table[c._x][c._y];
}




#endif