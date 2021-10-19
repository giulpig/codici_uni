#include "Book.hpp"
#include <iostream>

using namespace std;

int main(){

    Book bibbia{"Bibbia", "Apostoli", "109-143-123-f", 0, 1, 1, 2021, 9, 8};

    cout << bibbia;

    bibbia.checkOut(2021, 3, 4);

    return 0;
}