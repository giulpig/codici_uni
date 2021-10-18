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

	fibonacci(1, 1, miovettore, 500);
	
	for(int i=0; i<miovettore.size()-1; i++){
		if(miovettore[i++]<=0){
			cout << "max int approx: " << miovettore[i];
			break;
		}
	}

	return 0;
}