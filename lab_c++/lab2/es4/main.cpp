#include <iostream>

using namespace std;


void print_array(void* a, int n){

    //int* b = static_cast<int*>(b);

    int* b = (int*)(b);


    for(int i=0; i<n; i++)
        cout << b[i] << " ";
    
    cout << "\n";

}



int main(){

    int mioarray[10];                //con questo sizeof() = 80

    //int* mioarray = new int[10];    //con questo sizeof() = 8

    print_array(mioarray, 10);

    return 0;
}