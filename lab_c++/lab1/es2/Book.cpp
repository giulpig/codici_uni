#include "Book.hpp"
#include <ctype.h>
#include <regex>

const string ISBN_regex = "^[0-9]+-[0-9]+-[0-9]+-[A-Za-z0-9]$";

Book::Book(string _title, string _author, string _ISBN, int year, unsigned int month, unsigned int day, int yearNow, unsigned int monthNow, unsigned int dayNow){
    title = _title;
    author = _author;
    ISBN = _ISBN;
    if(!checkISBN())
        throw InvalidISBN();
    Date copyrightDate = (Date){year, month, day};
    checkIn(yearNow, monthNow, dayNow);
}

void Book::checkIn  (int year, unsigned int month, unsigned int day){
    in = true;
    checkDate = (Date) {year, month, day};
}

void Book::checkOut (int year, unsigned int month, unsigned int day){
    in = false;
    checkDate = (Date) {year, month, day};
}

string Book::getDate(){
    return to_string(checkDate.year) + "/" + to_string(checkDate.month) + "/" + to_string(checkDate.day) + "\n";
}

bool Book::operator== (const Book &b){
    return this->ISBN == b.ISBN;
}

bool Book::operator!= (const Book &b){
    return this->ISBN != b.ISBN;
}

ostream& operator<< (ostream &out, const Book &b)
{
    out << string("Title:  ") << b.title  << string("\n");
    out << string("Author: ") << b.author << string("\n");
    out << string("ISBN:   ") << b.ISBN   << string("\n");
    return out;
}

bool Book::checkISBN(){
    return regex_match(ISBN, regex(ISBN_regex));
}


