import java.util.Arrays;

public class Board{

    public short[][] table;
    //public int buco = -1;
    public short bucox = -1;
    public short bucoy = -1;
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

        for(short i=0; i<table.length; i++){                     //calcolo distanza e trovo buco
            for(short j=0; j<table.length; j++){
                if(table[i][j] != 0)
                    ret += Math.abs(i-(table[i][j]-1)/table.length) + Math.abs(j-(table[i][j]-1)%table.length);
                else{
                    bucox = i;
                    bucoy = j;
                }
            }
        }

        dist = ret;
    }

    @Override
    public int hashCode(){
        
        return toString().hashCode();

        //return hash;
    }

    @Override
    public boolean equals(Object o){

        if(dist != ((Board)(o)).dist || bucox != ((Board)(o)).bucox || bucoy != ((Board)(o)).bucoy){
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
