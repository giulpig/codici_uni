#include <string>

using namespace std;


class InvalidISBN{};


class Book{

public:

    Book(string title, string author, string ISBN, int year, unsigned int month, unsigned int day, int yearNow, unsigned int monthNow, unsigned int dayNow);

    string getDate();
    string getTitle(){return title;};
    string getAuthor(){return author;};
    string getISBN(){return ISBN;};

    void checkIn  (int year, unsigned int month, unsigned int day);
    void checkOut (int year, unsigned int month, unsigned int day);

    bool operator== (const Book &b);
    bool operator!= (const Book &b);
    friend ostream& operator<< (ostream &out, const Book &b);

private:
    
    struct Date{     //warning, unvalidated
        int year, month, day;
    };

    bool checkISBN();
    
    string title, author, ISBN;
    Date copyrightDate;
    bool in;
    Date checkDate;

};