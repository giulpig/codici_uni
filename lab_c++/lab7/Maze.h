#ifndef MAZE_H
#define MAZE_H

#define SIZE 9

#include "Coords.h"

#include <string>
#include <iostream>


class Maze{
    public:

        enum Tile{
            WALL,
            E,      //end
            S,      //start
            FREE
        };

        explicit Maze(const std::string &filename);
        Maze(const Maze &o);
        Maze(const Maze &&o);

        Maze& operator=(const Maze &o);

        const bool isWall(const Coords &c) const;
        const Coords& getStart(void) const;
        const Coords& getEnd(void) const;

        const Tile whatIs(const Coords &c) const;

        void print(void) const;


    private:
        Coords _Ecoord;
        Coords _Scoord;
        Tile _table[SIZE][SIZE];


};



#endif
