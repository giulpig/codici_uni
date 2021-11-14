import java.util.Arrays;

public class Board{

    public short[][] table;
    public int buco = -1;
    //public static int lStringa = 0;
    public int dist = -1;
    public int hash;

    public Board(){     //empty, for stealing
        ;
    }    

    public Board(short[][] tiles){        //for copying
        //table = Arrays.stream(tiles).map(short[]::clone).toArray(short[][]::new);
        table = new short[tiles.length][tiles.length];

        int ret = 0;

        for(int i=0; i<table.length; i++){           //copio, calcolo hash
            for(int j=0; j<table.length; j++){
                table[i][j] = tiles[i][j];

                //
            }
        }

        dist = ret;
    }


    public final String toString(){
        
        /*
        if(lStringa == 0){
            StringBuffer ret = new StringBuffer();
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(i*N+j == N-1)
                        ret.append(table[i][j]);
                    else
                        ret.append(table[i][j] + " ");
                }
            }
            return ret.toString();
        }

        
        else{                                       //fast toString
            char[] ret = new char[lStringa];
            int counter = 0;

            while(counter < N){
                int el = table[counter/N][counter%N];
                int mask10 = next10pow(el);

                ret[counter++] = (char)(el/(mask10) + '0');
                mask10/=10;

                for(int i=log10(N)-1; i>=1; i--){
                    ret[counter++] = (char)((el%(mask10*10))/mask10 + '0');
                }
                ret[counter++] = ' ';
            }
            return ret;
        }
        */

        StringBuffer ret = new StringBuffer();
        for(int i=0; i<table.length; i++){
            for(int j=0; j<table.length; j++){
                if(i*table.length+j == table.length*table.length-1)
                    ret.append(table[i][j]);
                else
                    ret.append(table[i][j] + " ");
            }
        }
        return ret.toString();

        
    }

    public int manhatan(){
        return dist;
    }



    public void calcManhattan(){
        
        int ret = 0;

        for(int i=0; i<table.length; i++){                     //calcolo distanza e trovo buco
            for(int j=0; j<table.length; j++){
                if(table[i][j] != 0)
                    ret += Math.abs(i-(table[i][j]-1)/table.length) + Math.abs(j-(table[i][j]-1)%table.length);
                else
                    buco = table.length*i+j;
            }
        }

        dist = ret;
    }

    /*
    static int next10pow(int v){
        return (v >= 100000000) ? 1000000000 : 
            (v >= 10000000) ? 100000000 : (v >= 1000000) ? 10000000 : 
            (v >= 100000) ? 1000000 : (v >= 10000) ? 100000 :
            (v >= 1000) ? 10000 : (v >= 100) ? 1000 : (v >= 10) ? 100 : 10; 
    }

    static int pow(int a, int b){
        int result = 1;
        for (int i = 1; i <= b; i++) {
            result *= a;
        }
        return result;
    }
    */

    @Override
        public int hashCode(){
            
            return toString().hashCode();

            //return hash;
        }

        @Override
        public boolean equals(Object o){

            if(dist != ((Board)(o)).dist || buco != ((Board)(o)).buco){
                //System.out.println("Non Conflitto");
                return false;
            }
            
            for(int i=0; i<table.length; i++){
                for(int j=0; j<table.length; j++){
                    if(table[i][j] != ((Board)(o)).table[i][j])
                        return false;
                }
            }
            //System.out.println("Conflitto");
            return true;
        }

}