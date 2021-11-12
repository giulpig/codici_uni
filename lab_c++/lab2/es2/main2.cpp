#include <iostream>

using namespace std;


constexpr int dim = 10;

void f(){

    int mioarray[dim];

    int* puntatore = &mioarray[6];

    for(int i=0; i<dim-6; i++){
        puntatore[i] = i;
    }

    for(int i=0; i<dim; i++){
        cout << mioarray[i] << " ";
    }

    cout << "\n\n";

}

void f_illegal_C(){

    int mioarray[dim];

    int* puntatore = &mioarray[6];

    for(int i=0; i<15; i++){
        puntatore[i] = 9876;
    }

    for(int i=0; i<20; i++){
        cout << mioarray[i] << " ";
    }

    cout << "\n\n";

}


void f_illegal_new(){

    int* mioarray = new int[10];

    int* puntatore = &mioarray[6];

    for(int i=0; i<10; i++){
        puntatore[i] = i;
    }

    for(int i=0; i<20; i++){
        cout << mioarray[i] << " ";
    }

    cout << "\n\n";

}




int main(){

    f();

    f_illegal_C();

    f_illegal_new();


    return 0;
}