import java.util.Arrays;
import java.util.BitSet;

public class Board{

    public class Board16 extends Board{
        public long ctable = 0;
        byte lato;

        public Board16(){     //empty, for stealing(?)
            ;
        }


        public Board16(short[][] tiles){        //for copying
            lato = (byte)tiles.length;
            byte counter = 0;
            for(int i=tiles.length-1; i>=0; i--){           //copio, calcolo hash
                for(int j=tiles.length-1; j>=0; j--){
                    ctable |= (long)tiles[i][j] << counter;
                    counter += 4;
                }
            }
        }

        @Override
        public void calcManhattan(){
            int ret = 0;
            long mask = 31; //1111b            

            for(byte i=0; i<lato*lato; i++){                     //calcolo distanza e trovo buco
                long el = ctable | (mask<<=1);
                if(el != 0)
                    ret += Math.abs(i/lato-(el-1)/table.length) + Math.abs(i%lato-(el-1)%table.length);
                else{
                    bucox = (short)(i/lato);
                    bucoy = (short)(i%lato);
                }
            }

            dist = ret;
        }

    }









    public short[][] table = null;
    //public int buco = -1;
    public short bucox = -1;
    public short bucoy = -1;
    //public static int lStringa = 0;
    public int dist = -1;

    public Board(){     //empty, for stealing(?)
        ;
    }    

    public Board(short[][] tiles){        //for copying
        //table = Arrays.stream(tiles).map(short[]::clone).toArray(short[][]::new);
        
        
        table = new short[tiles.length][tiles.length];

        for(int i=0; i<tiles.length; i++){           //copio, ***calcolo hash
            for(int j=0; j<tiles.length; j++){
                table[i][j] = tiles[i][j];
            }
        }
    }


    public final String toString(){

        if(table == null){
            long ctable = ((Board16)(this)).ctable;
            long mask = 31; //1111b

            StringBuffer ret = new StringBuffer();

            for(byte i=0; i<((Board16)(this)).lato*((Board16)(this)).lato-1; i++){
                ret.append((ctable | (mask<<=1)) + " ");
            }
            ret.append(ctable | (mask<<=1));

            return ret.toString();
        }
        
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


    //////////////////////////////MAYBE COMPRESS??
    public void compress(){
        
        for(short i=0; i<table.length; i++){
            for(short j=0; j<table.length; j++){
                ;
            }
        }

    }



    @Override
    public int hashCode(){          ///////////////////////////WORK IN PROGESS
        if(table == null)
            return Long.hashCode(((Board16)(this)).ctable);

        return toString().hashCode();
    }

    @Override
    public boolean equals(Object o){

        if(table == null){
            return ((Board16)(this)).ctable == ((Board16)(o)).ctable;
        }

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