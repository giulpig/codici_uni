public class Board{

    //public static int[] npowers;

    public int[][] table = null;
    public int bucox = -1;
    public int bucoy = -1;
    public int dist = -1;


    //public int hash = 2999;

    public Board(){     //empty, for stealing(?)
        ;
    }

    public Board(int[][] tiles, boolean first){        //for first entry (calc hash)
        
        table = new int[tiles.length][tiles.length];

        //int ntemp = 1;
        //int ntiles = tiles.length * tiles.length;

        //npowers = new int[ntiles];
        //int count = 0;

        for(int i=0; i<tiles.length; i++){           //copio, ***calcolo hash
            for(int j=0; j<tiles.length; j++){
                table[i][j] = tiles[i][j];
                //npowers[count++] = ntemp;
                //hash += table[i][j]*(ntemp*=ntiles);
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
        return dist;
    }

    public void calcManhattan(){
        
        int ret = 0;

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

        dist = ret;
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