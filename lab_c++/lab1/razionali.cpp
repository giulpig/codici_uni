#include <iostream>
#include <numeric>


using namespace std;



class Rational{
	
public:
	
	Rational(int numerator, int denominator): num{numerator}, den{denominator}{
		//if(denominator == 0)	throw DivisionByZero();
		simplify();
	}	
	
	Rational operator+ (const Rational &r);
	Rational operator- (const Rational &r);
	Rational operator* (const Rational &r);
	Rational operator/ (const Rational &r);
	bool operator== (Rational &r);
	
	double toDouble(){return (double)num/den;};
	
	void print();
	
private:

	int num, den;
	
	void simplify();
	
};


Rational Rational::operator+ (const Rational &r){
	
	int mincommon = lcm(this->den, r.den);
	Rational result{mincommon/this->den * this->num + mincommon/r.den * r.num, mincommon};
	result.simplify();
	return result;
}

Rational Rational::operator- (const Rational &r){

	int mincommon = lcm(this->den, r.den);
	Rational result{mincommon/this->den * this->num - mincommon/r.den * r.num, mincommon};
	result.simplify();
	return result;
}

Rational Rational::operator* (const Rational &r){
	
	Rational result{this->num * r.num, this->den * r.den};
	result.simplify();
	return result;
}

Rational Rational::operator/ (const Rational &r){
	
	Rational result{this->num * r.den, this->den * r.num};
	result.simplify();
	return result;
}

bool Rational::operator== (Rational &r){

	r.simplify();
	return(this->num==r.num && this->den==r.den);
}


void Rational::simplify(){

	int maxcommon = gcd(den, num);
	num /= maxcommon;
	den /= maxcommon;

}


void Rational::print(){

	cout << num << "/" << den << "\n";

}



int main(){

	Rational a{4, 5};
	Rational b{5, 7};
	
	Rational c = a - b;
	
	c.print();
	
	return 0;

}
