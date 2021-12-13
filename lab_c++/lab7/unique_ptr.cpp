#ifndef unique_ptr_CPP
#define unique_ptr_CPP


#include "unique_ptr.h"


template <typename T>
unique_ptr<T>::unique_ptr(T* t){
	_pointer = t;
}


template <typename T>
unique_ptr<T>::~unique_ptr(){
	delete _pointer;
}

template <typename T>
T* unique_ptr<T>::operator -> () const noexcept{
	return _pointer;
}


template <typename T>
T& unique_ptr<T>::operator * () const noexcept{
	return *_pointer;
}

template <typename T>
T* unique_ptr<T>::release(){
	T* temp = _pointer;
	_pointer = nullptr;
	return temp;
}




#endif