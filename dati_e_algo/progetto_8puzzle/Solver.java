import java.util.PriorityQueue;
import java.util.HashMap;

import java.io.*;       //Input/Output

public class Solver{    

    ////////////////////////////////////////////
    //Tree class
    ////////////////////////////////////////////

    public static PriorityQueue<Tree.TreeNode> pQueue = new PriorityQueue<Tree.TreeNode>();
    
    public static class Tree{
        public TreeNode root;
        //public short lastLevel;         //for debug

        public static TreeNode winner, current;
        
        public Tree(TreeNode r){
            root = r;
        }

        public TreeNode findSolver(){
            pQueue.add(root);
            while(!pQueue.isEmpty()){

                current = pQueue.poll();

                winner = current.calcSons();
                if(winner != null)   //true->win, also updates activeBoards and pQueue
                    return winner;
                
                //for debug, prints the current level
                //if(current.moves+current.board.cdist+current.board.mdist > lastLevel){
                //    System.out.print("\r" + (current.moves+current.board.cdist+current.board.mdist) + "   ");
                //    lastLevel = (short)(current.moves+current.board.cdist+current.board.mdist);
                //}
                
            }
            return null;
        }



        /////////////////////////////////////////////
        //TreeNode class
        ////////////////////////////////////////////

        public static class TreeNode implements Comparable<TreeNode>{
            
            public static HashMap<Board, Tree.TreeNode> activeBoards = new HashMap<Board, Tree.TreeNode>(31721, 0.25f);
            
            public static TreeNode collisioner;
            public static int i;

            public short moves;         //mosse per arrivare qua === depth dell'albero
            public byte lastMove;       //ultima mossa fatta: 0->radice 1->su 2->sx 3->dx 4->giu
            public Board board;

            public TreeNode father;

            public TreeNode(){          //java lo richiede, non ho idea del perche'
                ;
            }

            public TreeNode(Board b, short m, TreeNode f, byte l){
                board = b;
                moves = m;
                father = f;
                lastMove = l;
            }


            public TreeNode calcSons(){

                Board newBoard;
                TreeNode ret;

                if(lastMove!=4 && board.bucox > 0){          //swap in alto
                
                    newBoard = new Board(board.compressed);

                    ret = new TreeNode(newBoard, (short)(moves+1), this, (byte)1);

                    //aggiorno dist: real-expected
                    if(Board.shouldbe[ret.board.getTile(board.bucox-1, board.bucoy)][0] >= board.bucox){
                        ret.board.mdist = board.mdist - 1;
                    }
                    else{
                        ret.board.mdist = board.mdist + 1;
                    }
                    
                    //swap
                    ret.board.setTile(board.bucox, board.bucoy, ret.board.getTile(board.bucox-1, board.bucoy));
                    ret.board.setTile(board.bucox-1, board.bucoy, 0);

                    if(ret.board.mdist == 0){
                        return ret;
                    }

                    //update linear confict
                    for(i=0; i<board.bucoy; i++){
                        if(Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][0] == board.bucox-1 && Board.shouldbe[ret.board.getTile(board.bucox-1, i)][0] == board.bucox-1 && Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][1] < Board.shouldbe[ret.board.getTile(board.bucox-1, i)][1]){
                            ret.board.cdist-=2;      //remove old confl
                        }
                        if(Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][0] == board.bucox && Board.shouldbe[ret.board.getTile(board.bucox, i)][0] == board.bucox && Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][1] < Board.shouldbe[ret.board.getTile(board.bucox, i)][1]){
                            ret.board.cdist+=2;      //add new confl.
                        }
                    }
                    for(i=board.bucoy+1; i<Board.lato; i++){
                        if(Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][0] == board.bucox-1 && Board.shouldbe[ret.board.getTile(board.bucox-1, i)][0] == board.bucox-1 && Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][1] > Board.shouldbe[ret.board.getTile(board.bucox-1, i)][1]){
                            ret.board.cdist-=2;      //remove old confl
                        }
                        if(Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][0] == board.bucox && Board.shouldbe[ret.board.getTile(board.bucox, i)][0] == board.bucox && Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][1] > Board.shouldbe[ret.board.getTile(board.bucox, i)][1]){
                            ret.board.cdist+=2;      //add new confl.
                        }
                    }
                    ret.board.cdist += board.cdist;
                    
                    //update posizione buco, sale
                    ret.board.bucox = board.bucox-1;
                    ret.board.bucoy = board.bucoy;
                    

                    checkCollision(ret);
                }


                if(lastMove!=3 && board.bucoy > 0){          //swap a sx
                
                    newBoard = new Board(board.compressed);

                    ret = new TreeNode(newBoard, (short)(moves+1), this, (byte)2);

                    //aggiorno dist: real-expected, casella swappata va a dx
                    if(Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy-1)][1] >= board.bucoy){
                        ret.board.mdist = board.mdist - 1;
                    }
                    else{
                        ret.board.mdist = board.mdist + 1;
                    }

                    //swap
                    ret.board.setTile(board.bucox, board.bucoy, ret.board.getTile(board.bucox, board.bucoy-1));
                    ret.board.setTile(board.bucox, board.bucoy-1, 0);

                    if(ret.board.mdist == 0){
                        return ret;
                    }
                    

                    //update linear confict
                    for(i=0; i<board.bucox; i++){
                        if(Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][1] == board.bucoy-1 && Board.shouldbe[ret.board.getTile(i, board.bucoy-1)][1] == board.bucoy-1 && Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][0] < Board.shouldbe[ret.board.getTile(i, board.bucoy-1)][0]){
                            ret.board.cdist-=2;      //remove old confl
                        }
                        if(Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][1] == board.bucoy && Board.shouldbe[ret.board.getTile(i, board.bucoy)][1] == board.bucoy && Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][0] < Board.shouldbe[ret.board.getTile(i, board.bucoy)][0]){
                            ret.board.cdist+=2;      //add new confl.
                        }
                    }
                    for(i=board.bucox+1; i<Board.lato; i++){
                        if(Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][1] == board.bucoy-1 && Board.shouldbe[ret.board.getTile(i, board.bucoy-1)][1] == board.bucoy-1 && Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][0] > Board.shouldbe[ret.board.getTile(i, board.bucoy-1)][0]){
                            ret.board.cdist-=2;      //remove old confl
                        }
                        if(Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][1] == board.bucoy && Board.shouldbe[ret.board.getTile(i, board.bucoy)][1] == board.bucoy && Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][0] > Board.shouldbe[ret.board.getTile(i, board.bucoy)][0]){
                            ret.board.cdist+=2;      //add new confl.
                        }
                    }
                    ret.board.cdist += board.cdist;


                    //update buco, va a sx
                    ret.board.bucoy = board.bucoy-1;
                    ret.board.bucox = board.bucox;

                    checkCollision(ret);
                }


                if(lastMove!=2 && board.bucoy < Board.lato-1){       //swap a dx
                
                    newBoard = new Board(board.compressed);

                    ret = new TreeNode(newBoard, (short)(moves+1), this, (byte)3);

                    //aggiorno dist: real-expected, casella swappata va a sx
                    if(Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy+1)][1] <= board.bucoy){
                        ret.board.mdist = board.mdist - 1;
                    }
                    else{
                        ret.board.mdist = board.mdist + 1;
                    }

                    //swap
                    ret.board.setTile(board.bucox, board.bucoy, ret.board.getTile(board.bucox, board.bucoy+1));
                    ret.board.setTile(board.bucox, board.bucoy+1, 0);

                    if(ret.board.mdist == 0){
                        return ret;
                    }


                    //update linear confict
                    for(i=0; i<board.bucox; i++){
                        if(Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][1] == board.bucoy+1 && Board.shouldbe[ret.board.getTile(i, board.bucoy+1)][1] == board.bucoy+1 && Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][0] < Board.shouldbe[ret.board.getTile(i, board.bucoy+1)][0]){
                            ret.board.cdist-=2;      //remove old confl
                        }
                        if(Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][1] == board.bucoy && Board.shouldbe[ret.board.getTile(i, board.bucoy)][1] == board.bucoy && Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][0] < Board.shouldbe[ret.board.getTile(i, board.bucoy)][0]){
                            ret.board.cdist+=2;      //add new confl.
                        }
                    }
                    for(i=board.bucox+1; i<Board.lato; i++){
                        if(Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][1] == board.bucoy+1 && Board.shouldbe[ret.board.getTile(i, board.bucoy+1)][1] == board.bucoy+1 && Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][0] > Board.shouldbe[ret.board.getTile(i, board.bucoy+1)][0]){
                            ret.board.cdist-=2;      //remove old confl
                        }
                        if(Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][1] == board.bucoy && Board.shouldbe[ret.board.getTile(i, board.bucoy)][1] == board.bucoy && Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][0] > Board.shouldbe[ret.board.getTile(i, board.bucoy)][0]){
                            ret.board.cdist+=2;      //add new confl.
                        }
                    }
                    ret.board.cdist += board.cdist;

                    //update buco, va a dx
                    ret.board.bucoy = board.bucoy+1;
                    ret.board.bucox = board.bucox;

                    checkCollision(ret);
                }


                if(lastMove!=1 && board.bucox < Board.lato-1){      //swap in basso
                
                    newBoard = new Board(board.compressed);

                    ret = new TreeNode(newBoard, (short)(moves+1), this, (byte)4);

                    //aggiorno dist: real-expected
                    if(Board.shouldbe[ret.board.getTile(board.bucox+1, board.bucoy)][0] <= board.bucox){
                        ret.board.mdist = board.mdist - 1;
                    }
                    else{
                        ret.board.mdist = board.mdist + 1;
                    }


                    //swap
                    ret.board.setTile(board.bucox, board.bucoy, ret.board.getTile(board.bucox+1, board.bucoy));
                    ret.board.setTile(board.bucox+1, board.bucoy, 0);

                    if(ret.board.mdist == 0){
                        return ret;
                    }

                    //update linear confict
                    for(i=0; i<board.bucoy; i++){
                        if(Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][0] == board.bucox+1 && Board.shouldbe[ret.board.getTile(board.bucox+1, i)][0] == board.bucox+1 && Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][1] < Board.shouldbe[ret.board.getTile(board.bucox+1, i)][1]){
                            ret.board.cdist-=2;      //remove old confl
                        }
                        if(Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][0] == board.bucox && Board.shouldbe[ret.board.getTile(board.bucox, i)][0] == board.bucox && Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][1] < Board.shouldbe[ret.board.getTile(board.bucox, i)][1]){
                            ret.board.cdist+=2;      //add new confl.
                        }
                    }
                    for(i=board.bucoy+1; i<Board.lato; i++){
                        if(Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][0] == board.bucox+1 && Board.shouldbe[ret.board.getTile(board.bucox+1, i)][0] == board.bucox+1 && Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][1] > Board.shouldbe[ret.board.getTile(board.bucox+1, i)][1]){
                            ret.board.cdist-=2;      //remove old confl
                        }
                        if(Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][0] == board.bucox && Board.shouldbe[ret.board.getTile(board.bucox, i)][0] == board.bucox && Board.shouldbe[ret.board.getTile(board.bucox, board.bucoy)][1] > Board.shouldbe[ret.board.getTile(board.bucox, i)][1]){
                            ret.board.cdist+=2;      //add new confl.
                        }
                    }
                    ret.board.cdist += board.cdist;

                    //update buco, va in basso
                    ret.board.bucox = board.bucox+1;
                    ret.board.bucoy = board.bucoy;

                    checkCollision(ret);
                }

                return null;

            }
            


            public static void checkCollision(TreeNode ret){
                //se gia' esiste, agisco su activeBoards
                collisioner = activeBoards.get(ret.board);
                if(collisioner != null){

                    if(collisioner.board.mdist+collisioner.board.cdist+collisioner.moves <= ret.board.mdist+ret.board.cdist+ret.moves){
                        ret = null;
                    }
                    else{
                        activeBoards.remove(collisioner.board);
                        pQueue.remove(collisioner);
                        collisioner = null;

                        pQueue.add(ret);
                        activeBoards.put(ret.board, ret);
                    }
                }
                else{
                    pQueue.add(ret);
                    activeBoards.put(ret.board, ret);
                }

            }


            public int compareTo(TreeNode o){
                return board.mdist+board.cdist+moves - (o.board.mdist+o.board.cdist+o.moves);
            }

            @Override
            public boolean equals(Object o){
                return board.mdist+board.cdist+moves == (((TreeNode)(o)).board.mdist+((TreeNode)(o)).board.cdist+((TreeNode)(o)).moves);
            }
        }
    }




    /////////////////////////////////////////////
    //IO operations
    ////////////////////////////////////////////

    public static Board readInput(String filename) throws FileNotFoundException, IOException{

        InputReader sc = new InputReader(new FileInputStream(filename), 1<<17);

        int lato = sc.nextInt();

        return new Board(sc.nextIntMatrix(lato, lato), true);
    }

    //Recreate path from solution to root
    public static void printPath(Tree.TreeNode n) throws IOException{
        Tree.TreeNode[] path = new Tree.TreeNode[n.moves+1];
        Tree.TreeNode pointer = n;
        byte counter = 0;
        while(pointer!=null){
            path[counter++] = pointer;
            pointer = pointer.father;
        }


        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 2048);

        out.write((counter-1) + "\n");

        while(--counter >= 0){
            out.write(path[counter].board.toString());
            out.write("\n");
        }

        out.flush();

        out.close();

        /*
        System.out.println((counter-1) + "\n");

        while(--counter >= 0){
            System.out.println(path[counter].board.toString() + "\n");
        }
        */
    }



    public static void main(String[] args) throws FileNotFoundException, IOException{

        Board tab;
        
        tab = readInput(args[0]);

        Tree.TreeNode radice;

        radice = new Tree.TreeNode(tab, (short)(0), null, (byte)0);

        Tree albero = new Tree(radice);

        printPath(albero.findSolver());

    }



    //just a long class for improved input times
    public static class InputReader {
  
        private static final int DEFAULT_BUFFER_SIZE = 1 << 16;
        
        // 'c' is used to refer to the current character in the stream
        private int c;
        
        // Variables associated with the byte buffer. 
        private byte[] buf;
        private int bufferSize, bufIndex, numBytesRead;
        
        private InputStream stream;
      
        // End Of File (EOF) character
        private static final byte EOF   = -1;
      
        // Space character: ' '
        private static final byte SPACE = 32;
      
        // Dash character: '-'
        private static final byte DASH  = 45;

        private char[] charBuffer;
      
        // Primitive data type lookup tables used for optimizations
        private static byte[] bytes = new byte[58];
        private static  int[] ints  = new int[58];
        private static char[] chars = new char[128];
      
        static {
          char ch = ' '; int value = 0; byte _byte = 0;
          for (int i = 48; i <  58; i++ ) bytes[i] = _byte++;
          for (int i = 48; i <  58; i++ )  ints[i] = value++;
          for (int i = 32; i < 128; i++ ) chars[i] = ch++;
        }
      
        /**
         * Create an InputReader that reads from standard input.
         * @param  stream        Takes an {@link java.io.InputStream#InputStream() InputStream} as a parameter to read from.
         * @param  bufferSize    The size of the buffer to use.
         */
        public InputReader (InputStream stream, int bufferSize) {
          if (stream == null || bufferSize <= 0)
            throw new IllegalArgumentException();
          buf = new byte[bufferSize];
          charBuffer = new char[128];
          this.bufferSize = bufferSize;
          this.stream = stream;
        }
        

        private int readJunk(int token) throws IOException { 
          
          if (numBytesRead == EOF) return EOF;
      
          // Seek to the first valid position index
          do {
            
            while(bufIndex < numBytesRead) {
              if (buf[bufIndex] > token) return 0;
              bufIndex++;
            }
      
            // reload buffer
            numBytesRead = stream.read(buf);
            if (numBytesRead == EOF) return EOF;
            bufIndex = 0;
      
          } while(true);
      
        }
        

        public int nextInt() throws IOException {
          
          if (readJunk(DASH-1) == EOF) throw new IOException();
          int sgn = 1, res = 0;
      
          c = buf[bufIndex];
          if (c == DASH) { sgn = -1; bufIndex++; }
      
          do {
      
            while(bufIndex < numBytesRead) {
              if (buf[bufIndex] > SPACE) {
                res = (res<<3)+(res<<1);
                res += ints[buf[bufIndex++]];
              } else {
                bufIndex++;
                return res*sgn;
              }
            }
      
            // Reload buffer
            numBytesRead = stream.read(buf);
            if (numBytesRead == EOF) return res*sgn;
            bufIndex = 0;
      
          } while(true);
      
        }

        // Read a two dimensional matrix of ints of size rows x cols
        public int[][] nextIntMatrix(int rows, int cols) throws IOException {
          int[][] matrix = new int[rows][cols];
          for(int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
              matrix[i][j] = nextInt();
          return matrix;
        }
      
        // Closes the input stream
        public void close() throws IOException {
          stream.close();
        }
      
      }


}
