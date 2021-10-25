#include <iostream>

using namespace std;



int main(){

    char stringa[] = "mi piace la pasta al sugo";

    int i=0;
    while(stringa[i]){
        cout << stringa[i];
        i++;
    }
    
    cout << endl << "Terminatore (in int):" << (int)stringa[i];

    cout << endl;


    return 0;
}