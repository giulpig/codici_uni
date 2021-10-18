#include "printFunc.cpp"
#include <vector>


using namespace std;


void reverse(vector<int> &v){
	for(int i=0; i<v.size()/2; i++){
		int temp = v[i];
		v[i] = v[v.size()-1-i];
		v[v.size()-1-i] = temp;
	}
}


vector<int> reversed(const vector<int> &v){
	vector<int> rev;
	for(int i=v.size()-1; i>=0; i--)
		rev.push_back(v[i]);

	return rev;
}


int main(){
	vector<int> miovettore = {1, 2, 3, 4, 5, 6};

	print("Funzione con copia: ", reversed(miovettore));

	cout << "\n\n";

	reverse(miovettore);
	print("Funzione in-place: ", miovettore);


	return 0;
}