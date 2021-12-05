import java.util.Arrays;

public class Board{

    //public static int[] npowers;
    //public int hash = 2999;

    public static int[][] shouldbe;
    public static int bitsPerNum;
    public static int lato;
    public static long mask;
    public static int numsPerLong;
    public static int longs;

    public long[] compressed;
    public int bucox;
    public int bucoy;
    public short mdist;
    public short cdist;


    public Board(int[][] tiles, boolean first){        //for first entry

        lato = tiles.length;
        bitsPerNum = 32 - Integer.numberOfLeadingZeros(lato*lato);
        mask = (1<<bitsPerNum) - 1;
        numsPerLong = 64/bitsPerNum;
        longs = (int)(Math.ceil(bitsPerNum*lato*lato/64.0));

        shouldbe = new int[lato*lato][2];

        compressed = new long[longs];

        for(int i=0; i<lato; i++){           //copio, trovo buco, calcolo dist, ***calcolo hash
            for(int j=0; j<lato; j++){

                setTile(i, j, tiles[i][j]);
                
                int tile = getTile(i,j);

                if(tile != 0){

                    shouldbe[tile][0] = (tile-1)/lato;
                    shouldbe[tile][1] = (tile-1)%lato;

                    mdist += Math.abs(i-shouldbe[tile][0]) + Math.abs(j-shouldbe[tile][1]);
                    
                    for(int k=j+1; k<lato; k++){    //right check
                        if(tiles[i][k]!=0 && shouldbe[tile][0]==i && (tiles[i][k]-1)/lato==i && shouldbe[tile][1] > (tiles[i][k]-1)%lato){
                            cdist++;
                        }
                    }
                    for(int k=i+1; k<lato; k++){    //down check
                        if(tiles[k][j]!=0 && shouldbe[tile][1]==j && (tiles[k][j]-1)%lato==j && shouldbe[tile][0] > (tiles[k][j]-1)/lato){
                            cdist++;
                        }
                    }
                }
                else{
                    bucox = i;
                    bucoy = j;
                }                
            }
        }
    }


    public Board(long[] tiles){        //for copying
        compressed = new long[longs];
        for(int i=0; i<longs; i++){
            compressed[i] = tiles[i];
        }
    }


    public final String toString(){
        
        StringBuilder ret = new StringBuilder();
        for(int i=0; i<lato; i++){
            for(int j=0; j<lato; j++){
                if(i*lato+j == lato*lato-1)
                    ret.append(getTile(i,j));
                else
                    ret.append(getTile(i,j) + " ");
            }
        }
        return ret.toString();
    }


    public int manhattan(){
        return mdist;
    }
    

    public void calcManhattan(){
        
        short ret = 0;

        for(int i=0; i<lato; i++){                     //calcolo distanza
            for(int j=0; j<lato; j++){
                if(getTile(i,j) != 0)
                    ret += Math.abs(i-(getTile(i,j)-1)/lato) + Math.abs(j-(getTile(i,j)-1)%lato);
            }
        }

        mdist = ret;
    }


    public void setTile(int i, int j, long v){
        int index = (i*lato+j)/numsPerLong;   //which el in array?
        int index2 = (i*lato+j)%numsPerLong;  //which el in long?
        long maskHere = mask << (bitsPerNum*index2);
        compressed[index] = compressed[index] & (~maskHere) | v<<(bitsPerNum*index2);
    }


    public int getTile(int i, int j){
        int index = (i*lato+j)/numsPerLong;   //which el in array?
        int index2 = (i*lato+j)%numsPerLong;  //which el in long?
        long maskHere = mask << (bitsPerNum*index2);
        return (int)((compressed[index] & maskHere) >>> (bitsPerNum*index2));
    }



    @Override
    public int hashCode(){          ///////////////////////////WORK IN PROGESS
        //return hash;
        //return toStringNoSpaces().hashCode();
        return Arrays.hashCode(compressed);
    }

    @Override
    public boolean equals(Object o){

        if(mdist != ((Board)(o)).mdist || bucox != ((Board)(o)).bucox || bucoy != ((Board)(o)).bucoy || cdist != ((Board)(o)).cdist){
            //System.out.println("Non Conflitto");
            return false;
        }
        
        for(int i=0; i<longs; i++){
            if(compressed[i] != ((Board)(o)).compressed[i])
                return false;
        }
        //System.out.println("Conflitto");
        return true;
    }

}