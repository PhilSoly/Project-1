#ifndef LIBRARYCATALOG_H_
#define LIBRARYCATALOG_H_

#include <string>
#include <vector>
#include <iostream>
#include <fstream>
#include <sstream>

using namespace std;

#include "LibraryItem.h"

class LibraryCatalog
{
private:
	vector<LibraryItem *> m_item;

public:

	//add an item to the library
	bool add(LibraryItem *pCourse){
		bool isSuccessful = false ;
		if (pCourse != NULL)
		{
			m_item.push_back(pCourse) ;
			isSuccessful = true ;
		}
		return (isSuccessful);
	}
	//search for an item in the library
	vector<LibraryItem *> search(string s){
		int count = m_item.size() ;
		vector<LibraryItem *> foundCourseList ;
		LibraryItem * pCourse = NULL ;
		for(int i=0; i < count; i++){
			pCourse = m_item[i];
			if (pCourse->search(s))
				foundCourseList.push_back(pCourse) ;
		}
		return ( foundCourseList ) ;
	}
	//print out the library
	string toString(){
		int count = m_item.size();
		string buff = "";
		for(int i=0; i < count; i++){
			buff += m_item[i]->toString() + "\n" ;
		}
		return (buff);
	}
	//print out the library available items
	string printAvailable(){
		int count = m_item.size();
		string buff = "";
		for(int i=0; i < count; i++){
			if(!m_item[i]->isOnLoan())
				buff += m_item[i]->toString() + "\n" ;
		}
		return (buff);
	}
	//print out the library reference items
	string printRef(){
		int count = m_item.size();
		string buff = "";
		for(int i=0; i < count; i++){
			RefBook *pFull = dynamic_cast<RefBook *> (m_item[i]);
			if(pFull!=NULL)
				buff += m_item[i]->toString() + "\n";
		}
		return (buff);
	}
	//print out the library books
	string printBook(){
		int count = m_item.size();
		string buff = "";
		for(int i=0; i < count; i++){
			Book *pFull = dynamic_cast<Book *> (m_item[i]);
			if(pFull!=NULL)
				buff += m_item[i]->toString() + "\n";
		}
		return (buff);
	}
	//print out the library music
	string printMusic(){
		int count = m_item.size();
		string buff = "";
		for(int i=0; i < count; i++){
			MusicCD *pFull = dynamic_cast<MusicCD *> (m_item[i]);
			if(pFull!=NULL)
				buff += m_item[i]->toString() + "\n";
		}
		return (buff);
	}
	//check out an item if the item isn't borrowed and can be checked out
	void checkOutItem(LibraryItem *pCourse){
		if(pCourse->borrow())
			cout << "Item borrowed\n";
		else
			cout << "Cannot borrow item\n";
	}
	//return an item if the item can be returned
	void returnBackItem(LibraryItem *pCourse){
		if(pCourse->takeBack())
			cout << "Item borrowed\n";
		else
			cout << "Cannot borrow item\n";
	}
};

#endif /* LIBRARYCATALOG_H_ */
