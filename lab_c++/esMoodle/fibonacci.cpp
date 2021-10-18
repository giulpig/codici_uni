#include "printFunc.cpp"
#include <vector>


using namespace std;


void fibonacci(int x, int y, vector<int> &v, int n){
	v.push_back(x);
	v.push_back(y);
	for(int i=2; i<n; i++)
		v.push_back(v[i-1]+v[i-2]);
}


int main(){
	vector<int> miovettore;

	fibonacci(1, 1, miovettore, 20);
	print("miovettore: ", miovettore);

	return 0;
}