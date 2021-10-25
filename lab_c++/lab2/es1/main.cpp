#include <iostream>

using namespace std;


int main(){

    int my_int = 6;

    /*int* p1 = &my_int;
    int* p2 = &my_int;
    int* p3 = &my_int;*/

    int *p1, *p2, *p3;

    p1=p2=p3 = &my_int;

    cout << &my_int << " " << p1 << " " << p2 << " " << p3 << "\n\n";

    my_int++;

    cout << my_int << " " << *p1 << " " << *p2 << " " << *p3 << "\n\n";


    return 0;
}