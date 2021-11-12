#include <iostream>

using namespace std;



int main(){

    char stringa[] = "Me gusta la mangusta";

    int i=0;
    while(stringa[i]){
        cout << stringa[i];
        i++;
    }
    
    cout << endl << "Terminatore (in int):" << (int)stringa[i];

    cout << endl;


    return 0;
}