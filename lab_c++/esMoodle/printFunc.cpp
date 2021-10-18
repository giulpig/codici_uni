#include <iostream>
#include <vector>

using namespace std;

void print(string label, const vector<int> &v){
	cout << label;
	for(int i:v)
		cout << i << " ";

}