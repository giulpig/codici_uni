#ifndef SMARTROBOT_CPP
#define SMARTROBOT_CPP


#include "SmartRobot.h"


SmartRobot::SmartRobot(const Maze &m){
	_coords = m.getStart();
	findRouteBFS(m);
	whatmove = 0;
}

void SmartRobot::move(const Maze){
	_coords = _coords + _mosse[whatmove++];
}

void SmartRobot::findRouteBFS(const Maze &m){
	stack<Coords> s;
	vector<bool> visited(SIZE*SIZE);
	
	s.push(_coords);
	
	while(!s.empty()){
		
		Coords now = s.top();
		s.pop();
		visited[now._x*SIZE+now._y] = true;
		
		if(!visited[now._x*SIZE+now._y] && m.getEnd() == now)
			return;
		
		
		if(!visited[now._x*SIZE+now._y] && !m.isWall(_coords + UP)){
			s.push(_coords + UP);
			continue;
		}
		
		if(!visited[now._x*SIZE+now._y] && !m.isWall(_coords + DOWN)){
			s.push(_coords + DOWN);
			continue;
		}
		
		if(!visited[now._x*SIZE+now._y] && !m.isWall(_coords + LEFT)){
			s.push(_coords + LEFT);
			continue;
		}
		
		if(!visited[now._x*SIZE+now._y] && !m.isWall(_coords + RIGHT)){
			s.push(_coords + RIGHT);
			continue;
		}
		
		_mosse.pop_back();
		visited[now._x*SIZE+now._y] = false;
		
	}
	
}





#endif