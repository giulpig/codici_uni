#include "Robot.h"
#include "RightHandRobot.h"
#include "RandomRobot.h"
#include "Maze.h"
#include "Coords.h"


using namespace std;

void printAll(const Maze& m, const Robot &r){
    for(int i=0; i<SIZE; i++){
        for(int j=0; j<SIZE; j++){
            if(Coords{i,j} == r.getCoords()){
                cout << 'R';
                continue;
            }
            switch (m.whatIs(Coords{i,j})){
                case Maze::WALL:
                    std::cout << '*';
                    break;
                case Maze::FREE:
                    std::cout << ' ';
                    break;
                case Maze::S:
                    std::cout << 'S';
                    break;
                case Maze::E:
                    std::cout << 'E';
                    break;
            };
        }
        std::cout << std::endl;
    }
}


int main(){

    Maze labirinto{"input.txt"};
    //labirinto.print();
    RandomRobot lello{labirinto};
    printAll(labirinto, lello);

    while(!lello.win(labirinto)){
        cout << "--------------\n";
        lello.move(labirinto);
        printAll(labirinto, lello);
    }

    return 0;
}