#ifndef unique_ptr_H
#define unique_ptr_H


template <typename T>
class unique_ptr{
	
	public:
		unique_ptr(T* t);
		~unique_ptr();
		
		T* operator -> () const noexcept;
		T& operator * () const noexcept;
		
		T* release();
	
	private:
		T* _pointer;
	
};




#endif