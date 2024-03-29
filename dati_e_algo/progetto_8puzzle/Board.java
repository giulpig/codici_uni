import java.util.Arrays;

public class Board{

    public static int[][] shouldbe;
    public static int bitsPerNum;
    public static int lato;
    public static long mask;
    public static int numsPerLong;
    public static int longs;

    public static int i, j, ret, tile, k, index, index2;
    public static long maskHere;

    public long[] compressed;
    public int bucox;
    public int bucoy;
    public int mdist;
    public int cdist;


    public Board(int[][] tiles, boolean first){        //for first entry

        lato = tiles.length;
        bitsPerNum = 32 - Integer.numberOfLeadingZeros(lato*lato);
        mask = (1<<bitsPerNum) - 1;
        numsPerLong = 64/bitsPerNum;
        int remainder = lato*lato;
        do{
            longs++;
        }
        while((remainder-=numsPerLong) > 0);

        shouldbe = new int[lato*lato][2];

        compressed = new long[longs];

        for(i=0; i<lato; i++){           //copio, trovo buco, calcolo dist
            for(j=0; j<lato; j++){

                setTile(i, j, tiles[i][j]);
                
                tile = getTile(i,j);

                if(tile != 0){

                    shouldbe[tile][0] = (tile-1)/lato;
                    shouldbe[tile][1] = (tile-1)%lato;

                    mdist += Math.abs(i-shouldbe[tile][0]) + Math.abs(j-shouldbe[tile][1]);
                    
                    for(k=j+1; k<lato; k++){    //right check
                        if(tiles[i][k]!=0 && shouldbe[tile][0]==i && (tiles[i][k]-1)/lato==i && shouldbe[tile][1] > (tiles[i][k]-1)%lato){
                            cdist+=2;
                        }
                    }
                    for(k=i+1; k<lato; k++){    //down check
                        if(tiles[k][j]!=0 && shouldbe[tile][1]==j && (tiles[k][j]-1)%lato==j && shouldbe[tile][0] > (tiles[k][j]-1)/lato){
                            cdist+=2;
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
        for(i=0; i<longs; i++){
            compressed[i] = tiles[i];
        }
    }


    public final String toString(){
        
        StringBuilder ret = new StringBuilder();
        for(i=0; i<lato; i++){
            for(j=0; j<lato; j++){
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
        
        ret = 0;

        for(i=0; i<lato; i++){                     //calcolo distanza
            for(j=0; j<lato; j++){
                if(getTile(i,j) != 0)
                    ret += Math.abs(i-(getTile(i,j)-1)/lato) + Math.abs(j-(getTile(i,j)-1)%lato);
            }
        }

        mdist = ret;
    }


    public void setTile(int i, int j, long v){
        index = (i*lato+j)/numsPerLong;   //which el in array?
        index2 = (i*lato+j)%numsPerLong;  //which el in long?
        maskHere = mask << (bitsPerNum*index2);
        compressed[index] = compressed[index] & (~maskHere) | v<<(bitsPerNum*index2);
    }


    public int getTile(int i, int j){
        index = (i*lato+j)/numsPerLong;   //which el in array?
        index2 = (i*lato+j)%numsPerLong;  //which el in long?
        maskHere = mask << (bitsPerNum*index2);
        return (int)((compressed[index] & maskHere) >>> (bitsPerNum*index2));
    }



    @Override
    public int hashCode(){
        return Arrays.hashCode(compressed);
    }

    @Override
    public boolean equals(Object o){

        if(mdist != ((Board)(o)).mdist || bucox != ((Board)(o)).bucox || bucoy != ((Board)(o)).bucoy || cdist != ((Board)(o)).cdist){
            return false;
        }

        return Arrays.equals(compressed, ((Board)(o)).compressed);
    }

}