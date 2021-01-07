public class StudentParExt extends StudentPar{

    static boolean method;    //0->matricola, 1->lessicografico

    public void setOrder(boolean option){
        method = option;
    }

    public StudentParExt(int aRollno, String aName, String anAddress){
        super(aRollno, aName, anAddress);
    }

    @Override
    public int compareTo(StudentPar other){
        if(other == null)
            throw new IllegalArgumentException();

        if(method==true)
            return rollno() - other.rollno();

        return name().compareTo(other.name());

    }



}