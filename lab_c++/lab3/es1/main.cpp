#include <iostream>


int main(){

    int* mioarray = new int[10];

    for(int i=0; i<200000; i++){
        mioarray[i] = i;
        std::cout << mioarray[i] << "\n";
    }


    return 0;
}