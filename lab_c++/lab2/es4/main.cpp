#include <iostream>

using namespace std;

constexpr int dim = 10;


void print_array(void* a, int n){

    //int* b = static_cast<int*>(b);

    int* b = (int*)(b);


    for(int i=0; i<n; i++)
        cout << b[i] << " ";
    
    cout << "\n";

}



int main(){

    int mioarray[dim];                //cosi' sizeof() = 80

    //int* mioarray = new int[10];    //cosi' sizeof() = 8

    print_array(mioarray, dim);

    return 0;
}