import java.util.*;

public class MontyHall {

    private Random oggettoRandom = new Random();
    int car, toRemove, choice;
    boolean[] values;

    public MontyHall(){        //1->car 0->goat
        car = oggettoRandom.nextInt(3);
        values = new boolean[3];
        values[car] = true;
    }

    public boolean award(boolean keepOrChange){     //1->car 0->goat
        
        choice = firstChoice();
        while(true){
            int temp = oggettoRandom.nextInt(3);
            if(temp!=choice && temp!=car){
                toRemove = temp;
                break;
            }
        }

        if(keepOrChange == true){   //keep
            return car==choice;
        }

        return car!=choice;

    }

    private int firstChoice(){
        return oggettoRandom.nextInt(3);
    }

    public String toString(){
        String res = "";
        for(int i=0; i<3; i++){
            res += "Porta" + i + " " + (values[i]==true ? "Car" : "Goat");
        }
        return res;
    }

    public static void main(String[] args){
        int cars = 0;
        int total = 500000;//Integer.parseInt(args[0]);
        
        for(int i=0; i<total; i++){
            MontyHall test = new MontyHall();
            if(test.award(true))  //keep
                cars++;
        }

        System.out.printf("Keep perc.: %f\n", (cars/(double)total*100.0));



        cars = 0;
        
        for(int i=0; i<total; i++){
            MontyHall test = new MontyHall();
            if(test.award(false))  //change
                cars++;
        }

        System.out.printf("Change perc.: %f\n", (cars/(double)total*100.0));
    }
}
