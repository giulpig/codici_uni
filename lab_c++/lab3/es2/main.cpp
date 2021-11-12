#include <iostream>
#include <vector>


int main(){

    std::vector<int> v;

    //std::vector<int> *p = new std::vector<int>;

    for(int i=0; i<20000000; i++){
        v.push_back(i);
        //p->push_back(i);
    }

    long long somma = 0;

    for(int i=0; i<20000000; i++){
        //somma += (*p)[i];
        somma += v[i];
    }


    return 0;
}