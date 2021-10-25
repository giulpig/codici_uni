#include <iostream>

using namespace std;


void print_reference(const double &r){

    cout << r << endl;

}

void print_pointer(void* p, bool type){

    if(!type)
        cout << *((int*)p) << endl;

    else
        cout << *((double*)p) << endl;

}



int main(){

    int myint = -8;                //con questo sizeof() = 80
    double mydouble = 9.99;

    //int* mioarray = new int[10];    //con questo sizeof() = 8

    print_reference((double)myint);

    print_reference(mydouble);

    print_pointer(&myint, 0);

    print_pointer(&mydouble, 1);



    return 0;
}