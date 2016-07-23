
#ifndef LIBRARYCREATE_H_
#define LIBRARYCREATE_H_

#include "LibraryCatalog.h"

class LibraryCreate
{
	public:
	//read a data file to create a library
	static LibraryCatalog * read(string courseDataFileName){
		LibraryCatalog * pCatalog = new LibraryCatalog() ;
		ifstream f(courseDataFileName.c_str());
		if (f.is_open()){
			string oneLine ;
			while (getline(f, oneLine)){
				if (!oneLine.empty() && oneLine[0]!='/' && oneLine[1]!='/')
					pCatalog->add(getOneItem(oneLine));
			}
		}
		return pCatalog ;
	}
	//add items to the library catalog
	static LibraryItem * getOneItem(string oneRecord){
		stringstream ss(oneRecord);
		string type;
		int id;
		string title;
		string author;
		ss >> type;
		ss >> id;
		getline(ss, title);
		author = title.substr(title.find('|')+2,title.length());
		title = title.substr(2,title.find('|')-2);
		if(type=="BOOK")
			return new Book(type, id, title, author);
		else if (type=="REF")
			return new RefBook(type, id, title, author);
		return new MusicCD(type, id, title, author);
	}
} ;

#endif
