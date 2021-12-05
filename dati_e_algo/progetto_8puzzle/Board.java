public class Board{

    //public static int[] npowers;
    //public int hash = 2999;

    public static int[][] shouldbe;
    public static int bitsPerNum;
    public static int lato;

    public long[] compressed;
    public int[][] table;
    public int bucox;
    public int bucoy;
    public short mdist;
    public short cdist;


    public Board(int[][] tiles, boolean first){        //for first entry

        lato = tiles.length;
        bitsPerNum = 31 - Integer.numberOfLeadingZeros(lato*lato);
        
        table = new int[lato][lato];

        shouldbe = new int[lato*lato][2];

        compressed = new long[bitsPerNum*lato/64];

        //int ntemp = 1;
        //int ntiles = tiles.length * tiles.length;

        //npowers = new int[ntiles];
        //int count = 0;

        for(int i=0; i<lato; i++){           //copio, trovo buco, calcolo dist, ***calcolo hash
            for(int j=0; j<lato; j++){

                table[i][j] = tiles[i][j];

                if(table[i][j] != 0){

                    shouldbe[table[i][j]][0] = (table[i][j]-1)/lato;
                    shouldbe[table[i][j]][1] = (table[i][j]-1)%lato;

                    mdist += Math.abs(i-shouldbe[table[i][j]][0]) + Math.abs(j-shouldbe[table[i][j]][1]);
                    
                    for(int k=j+1; k<lato; k++){    //right check
                        if(tiles[i][k]!=0 && shouldbe[table[i][j]][0]==i && (tiles[i][k]-1)/lato==i && shouldbe[table[i][j]][1] > (tiles[i][k]-1)%lato){
                            cdist++;
                        }
                    }
                    for(int k=i+1; k<lato; k++){    //down check
                        if(tiles[k][j]!=0 && shouldbe[table[i][j]][1]==j && (tiles[k][j]-1)%lato==j && shouldbe[table[i][j]][0] > (tiles[k][j]-1)/lato){
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


    public Board(int[][] tiles){        //for copying
        table = new int[lato][lato];

        for(int i=0; i<lato; i++){
            for(int j=0; j<lato; j++){
                table[i][j] = tiles[i][j];
            }
        }
    }


    public final String toString(){
        
        StringBuilder ret = new StringBuilder();
        for(int i=0; i<lato; i++){
            for(int j=0; j<lato; j++){
                if(i*lato+j == lato*lato-1)
                    ret.append(table[i][j]);
                else
                    ret.append(table[i][j] + " ");
            }
        }
        return ret.toString();
    }


    public final String toStringNoSpaces(){

        StringBuilder ret = new StringBuilder();
        for(int i=0; i<lato; i++){
            for(int j=0; j<lato; j++){
                ret.append(table[i][j]);
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
                if(table[i][j] != 0)
                    ret += Math.abs(i-(table[i][j]-1)/lato) + Math.abs(j-(table[i][j]-1)%lato);
            }
        }

        mdist = ret;
    }


    //////////////////////////////MAYBE COMPRESS??
    public void compress(){
        
        for(int i=0; i<lato; i++){
            for(int j=0; j<lato; j++){
                
            }
        }

    }

    public static void setTile(int i, int j, int v){
        int index = i*lato;
    }


    @Override
    public int hashCode(){          ///////////////////////////WORK IN PROGESS
        //return hash;
        return toStringNoSpaces().hashCode();
    }

    @Override
    public boolean equals(Object o){

        if(mdist != ((Board)(o)).mdist || bucox != ((Board)(o)).bucox || bucoy != ((Board)(o)).bucoy || cdist != ((Board)(o)).cdist){
            //System.out.println("Non Conflitto");
            return false;
        }
        
        for(int i=0; i<lato; i++){
            for(int j=0; j<lato; j++){
                if(table[i][j] != ((Board)(o)).table[i][j])
                    return false;
            }
        }
        //System.out.println("Conflitto");
        return true;
    }

}