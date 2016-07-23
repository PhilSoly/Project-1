
#ifndef MUSICCD_H_
#define MUSICCD_H_

#include "LibraryItemBase.h"

class MusicCD: public LibraryItemBase{
private:
	string type;
	string artist;
public:
	//constructor using the base-constructor of libraryitembase
	MusicCD(string n_type, int n_id, string n_title, string n_artist) : LibraryItemBase(n_id, n_title), type(n_type), artist(n_artist){}
	//search the item if it contains a string
	virtual bool search(string searchStr){
		string  s_find = type + " " + artist;
		transform(s_find.begin(), s_find.end(), s_find.begin(), ::tolower);
		transform(searchStr.begin(), searchStr.end(), searchStr.begin(), ::tolower);
		return LibraryItemBase::search(searchStr) || (s_find.find(searchStr) != string::npos);
	}
	//turn the item properties into a string
	virtual string toString(){
		string item = LibraryItemBase::toString() + "ARTIST: " + artist;
		return item;
	}

};

#endif
