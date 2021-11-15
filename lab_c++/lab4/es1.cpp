#include <iostream>
#include <string>

using namespace std;


bool is_palindrome(const string& s){

    for(int i=0; i<s.length()/2; i++){
        if(s[i]!=s[s.length()-1-i])
            return false;
    }
    return true;
}

bool is_palindrome2(const char s[], int length){

    for(int i=0; i<length/2; i++){
        if(s[i]!=s[length-1-i])
            return false;
    }
    return true;
}

bool is_palindrome3(const char* first, const char* last){

    int counter = 0;
    while(first+counter < last-counter){
        if(*(first+counter) != *(last-counter))
            return false;
        counter++;
    }
    return true;
}



int main(){

    const char str[] = "AAbAa";
    cout << is_palindrome3(str, &(str[4]));

    return 0;
}
