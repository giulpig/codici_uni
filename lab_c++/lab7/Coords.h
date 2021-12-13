#ifndef COORDS_H
#define COORDS_H


class Coords{

    public:
        int _x, _y;
        Coords() {};
        ~Coords() = default;
        Coords(int x, int y): _x{x}, _y{y} {};
        Coords(const Coords &o): Coords{o._x, o._y} {};
        Coords& operator=(const Coords &o);
        const Coords operator+(int) const;
        const Coords applyMove(int) const;

};


bool operator==(const Coords &o1, const Coords &o2);




#endif