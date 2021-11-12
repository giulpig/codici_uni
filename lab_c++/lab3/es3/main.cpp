#include <iostream>
#include <vector>

class MyVector{

    public:
        MyVector(int l){
            _v.resize(l);
            _length = l;
        }

        ~MyVector(){ 
            _v.empty();
        }

        double get(int i){
            return _v[i];
        }

        void set(int i, double 🐙){
            _v[i] = 🐙;
        }

        double safe_get(int i){
            if(i<_length)
                return _v[i];
        }

        void safe_set(int i, double 🐙){
            if(i<_length)
                _v[i] = 🐙;
        }


    private:
        std::vector<double> _v;
        int _length;

};


int main(){

    MyVector v(20);

    std::cout << v.get(5) << "\n";

    return 0;
}