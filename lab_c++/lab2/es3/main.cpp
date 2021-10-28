#include <iostream>

using namespace std;

constexpr int dim = 10;


void print_array_length(double a[]){

    cout << sizeof(a) << "\n";           //sizeof = 8

    for(int i=0; i<dim; i++)
        cout << a[i] << " ";

    cout << "\n";

}



int main(){

    double mioarray[dim];                //con questo sizeof() = 80

    //double* mioarray = new double[10];    //con questo sizeof() = 8

    cout << sizeof(mioarray) << "\n";

    print_array_length(mioarray);


    return 0;
}