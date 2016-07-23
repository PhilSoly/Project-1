/*
 * LibraryItemBase.h
 *
 *  Created on: Jun 18, 2016
 *      Author: PC
 */

#ifndef LIBRARYITEMBASE_H_
#define LIBRARYITEMBASE_H_
#include <string>
#include <algorithm>
#include <sstream>
#include "LibraryItem.h"

using namespace std;

class LibraryItemBase: public LibraryItem{
private:
	int id;
	string title;
	bool loan;
public:

	LibraryItemBase(int n_id, string n_title){
		id = n_id;
		title = n_title;
		loan = false;
	}

	//access loan boolean
	virtual bool isOnLoan(){
		return loan;
	}
	//toggle loan boolean if false
	virtual bool borrow(){
		if(!loan){
			loan = true;
			return true;
		}
		return false;
	}
	//toggle loan boolean if true
	virtual bool takeBack(){
		if(loan){
			loan = false;
			return true;
		}
		return false;
	}
	//search the item if it contains a string
	virtual bool search(string searchStr){
		stringstream ss;
		ss << id;
		string s_find = ss.str() + " " + title;
		transform(s_find.begin(), s_find.end(), s_find.begin(), ::tolower);
		transform(searchStr.begin(), searchStr.end(), searchStr.begin(), ::tolower);
		return (s_find.find(searchStr) != string::npos);
	}
	//turn the item properties into a string
	virtual string toString(){
		string item = "ID: " + to_string(id) + " TITLE: " + title + " STATUS: ";
		if(loan) item += "unavailable ";
		else item += "available ";
		return item;
	}

};

#endif
