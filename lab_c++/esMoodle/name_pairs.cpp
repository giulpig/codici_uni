#include <iostream>
#include <vector>

using namespace std;

int min(int x, int y){
	if(x<y)
		return x;
	else
		return y;
}


class Name_pairs{
public:
	void read_names(){
		cout << "Reading names, type 'q' to stop\n";
		while(1){
			string input;
			getline(cin, input);
			if(input == string("q"))
				break;
			else
				name.push_back(input);
		}

	}

	void read_ages(){
		cout << "Reading ages, type '-1' to stop\n";
		while(1){
			int input;
			cin >> input;
			if(input == -1)
				break;
			else
				age.push_back(input);
		}

	}

	void print(){
		for(int i=0; i<min(name.size(), age.size()); i++){
			cout << name[i] << ": " << age[i] << "\n";
		}
	}



private:
	vector<string> name;
	vector<int> age;


};