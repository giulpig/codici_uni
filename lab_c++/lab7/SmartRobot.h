#ifndef SMARTROBOT_H
#define SMARTROBOT_H

#include "Robot.h"

#include <stack>
#include <vector>

using std::stack, std::vector;

class SmartRobot : public Robot{
	
	public:
		explicit SmartRobot(const Maze&);
		
		void move(const Maze&) override;
	
	
	private:
		vector<Move> _mosse;
		
		int whatmove;
		
		void findRouteBFS(const Maze&);
	
};





#endif