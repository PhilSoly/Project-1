
#include <string>
#include <vector>
#include <cstdlib>
#include <iostream>
#include <fstream>
#include <sstream>

using namespace std;

#include "Book.h"
#include "RefBook.h"
#include "MusicCD.h"
#include "LibraryCreate.h"

class ManageLibrary{
private:
bool done;
public:
	//start and keep the program running
	void run(string courseDataFileName){
		LibraryCatalog *lib = LibraryCreate::read(courseDataFileName);
		help();
		string cmd;
		done = false;
		do{
			getline(cin, cmd);
			if (!cmd.empty())
				process(lib, cmd);
		} while (!done);
	}
	//help commmand for the user to know what to do
	void help(){
		cout << "Library:" << endl ;
		cout << "   1. list all items" << endl ;
		cout << "   2. list available items" << endl ;
		cout << "   3. list only reference items" << endl ;
		cout << "   4. list only books" << endl ;
		cout << "   5. list only music" << endl ;
		cout << "   6. search" << endl ;
		cout << "   7. check out" << endl ;
		cout << "   8. return" << endl ;
		cout << "   9. exit the program" << endl ;
		cout << "Enter your selection: ";
	}
	//process the command based on the input
	void process(LibraryCatalog *lib, string function){
		help();
		if (function.compare("1") == 0)
			cout << lib->toString() << endl;
		else if (function.compare("2") == 0)
			cout << lib->printAvailable() << endl;
		else if (function.compare("3") == 0)
			cout << lib->printRef() << endl;
		else if (function.compare("4") == 0)
			cout << lib->printBook() << endl;
		else if (function.compare("5") == 0)
			cout << lib->printMusic() << endl;
		else if (function.compare("6") == 0)
			search(lib, getstr("Please enter your search: "));
		else if (function.compare("7") == 0)
			checkItem(lib, getstr("Please enter the item's id: "));
		else if (function.compare("8") == 0)
			returnItem(lib, getstr("Please enter the item's id: "));
		else if (function.compare("9") == 0)
			done = true;
		else
			cout << "Invalid command\n";
	}
	//search the library for a string
	void search(LibraryCatalog * lib, string arg){
		vector<LibraryItem *> libList = lib->search(arg) ;
		if (libList.size() == 0)
			cout << "   No match is found. " << endl;
		else
			for(int count=0; count < libList.size(); count++)
				cout << libList[count]->toString() << endl;
	}
	//return the book if checked out
	void returnItem(LibraryCatalog * lib, string arg){
		vector<LibraryItem *> libList = lib->search(arg);
		if (libList.size() == 0)
			cout << "No match found\n";
		else
			for(int count=0; count < libList.size(); count++)
				lib->returnBackItem(libList[0]);
	}
	//checkout a book if possible
	void checkItem(LibraryCatalog * lib, string arg){
		vector<LibraryItem *> libList = lib->search(arg);
		if (libList.size() == 0)
			cout << "   No match is found. " << endl ;
		else
			for(int count=0; count < libList.size(); count++)
				lib->checkOutItem(libList[0]);
	}
	//get string to use else-where
	string getstr(string prompt){
		cout << prompt;
		string ans;
		getline(cin, ans);
		return (ans);
	}
};
//use command line argument to start the program
int main(int argc, char * argv[])
{
	if (argc != 2)
		cout << "Error" << endl;
	else
	{
		string courseDataFileName = argv[1];
		(new ManageLibrary())->run(courseDataFileName);
	}
	return 0;
}

