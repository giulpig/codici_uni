public class Board{

    //public static int[] npowers;
    //public int hash = 2999;

    public static int[][] shouldbe;

    public int[][] table = null;
    public int bucox;
    public int bucoy;
    public short mdist;
    public short cdist;


    public Board(){     //empty, for stealing(?)
        ;
    }

    public Board(int[][] tiles, boolean first){        //for first entry
        
        table = new int[tiles.length][tiles.length];

        shouldbe = new int[tiles.length*tiles.length][2];

        //int ntemp = 1;
        //int ntiles = tiles.length * tiles.length;

        //npowers = new int[ntiles];
        //int count = 0;

        for(int i=0; i<tiles.length; i++){           //copio, trovo buco, calcolo dist, ***calcolo hash
            for(int j=0; j<tiles.length; j++){

                table[i][j] = tiles[i][j];
                if(table[i][j] != 0){

                    shouldbe[table[i][j]][0] = (table[i][j]-1)/table.length;
                    shouldbe[table[i][j]][1] = (table[i][j]-1)%table.length;

                    mdist += Math.abs(i-shouldbe[table[i][j]][0]) + Math.abs(j-shouldbe[table[i][j]][1]);
                    
                    for(int k=j+1; k<tiles.length; k++){    //right check
                        if(tiles[i][k]!=0 && shouldbe[table[i][j]][0]==i && (tiles[i][k]-1)/table.length==i && shouldbe[table[i][j]][1] > (tiles[i][k]-1)%table.length){
                            cdist++;
                        }
                    }
                    for(int k=i+1; k<tiles.length; k++){    //down check
                        if(tiles[k][j]!=0 && shouldbe[table[i][j]][1]==j && (tiles[k][j]-1)%table.length==j && shouldbe[table[i][j]][0] > (tiles[k][j]-1)/table.length){
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
        table = new int[tiles.length][tiles.length];

        for(int i=0; i<tiles.length; i++){
            for(int j=0; j<tiles.length; j++){
                table[i][j] = tiles[i][j];
            }
        }
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


    public final String toStringNoSpaces(){
        
        StringBuffer ret = new StringBuffer();
        for(int i=0; i<table.length; i++){
            for(int j=0; j<table.length; j++){
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

        for(int i=0; i<table.length; i++){                     //calcolo distanza e trovo buco
            for(int j=0; j<table.length; j++){
                if(table[i][j] != 0)
                    ret += Math.abs(i-(table[i][j]-1)/table.length) + Math.abs(j-(table[i][j]-1)%table.length);
                else{
                    bucox = i;
                    bucoy = j;
                }
            }
        }

        mdist = ret;
    }


    //////////////////////////////MAYBE COMPRESS??
    public void compress(){
        
        for(int i=0; i<table.length; i++){
            for(int j=0; j<table.length; j++){
                
            }
        }

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