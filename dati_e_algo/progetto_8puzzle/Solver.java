import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.StringTokenizer;

import java.io.*;       //Input/Output


public class Solver{

    /////////////////////////////////////////////
    //Tree class
    ////////////////////////////////////////////

    public static PriorityQueue<Tree.TreeNode> pQueue = new PriorityQueue<Tree.TreeNode>();
    
    public static class Tree{
        public TreeNode root;
        public short lastLevel;
        
        public static int nodes = 0;    //toremove
        
        public Tree(TreeNode r){
            root = r;
            root.board.calcManhattan();
        }

        public TreeNode findSolver(){
            pQueue.add(root);
            while(!pQueue.isEmpty()){

                TreeNode current = pQueue.poll();

                if(current.board.dist == 0){
                    return current;
                }

                current.calcSons();   //also updates activeBoards and pQueue

                if(current.moves + current.board.dist > lastLevel){
                    System.out.print("\r" + (current.moves + current.board.dist) + "   ");
                    lastLevel = (short)(current.moves + current.board.dist);
                }
                //System.out.print("\r" + nodes);
                //System.out.print("\r" + current.sons.length);
                //System.out.print("\r" + current.board.toString());
                
            }
            System.out.println("Porcodiiiioioioi");      //toremove
            return null;
        }



        /////////////////////////////////////////////
        //TreeNode class
        ////////////////////////////////////////////

        public static class TreeNode implements Comparable<TreeNode>{
            
            public static HashMap<Board, Tree.TreeNode> activeBoards = new HashMap<Board, Tree.TreeNode>(2999, 0.5f);
            
            public short moves;       //mosse per arrivare qua === depth dell'albero
            public byte lastMove;    //ultima mossa fatta: 0->radice 1->su 2->sx 3->dx 4->giu
            public Board board;
            //public boolean alive = true;

            public TreeNode father;

            public TreeNode(){          //java e' stupid*
                ;
            }

            public TreeNode(Board b, short m, TreeNode f, byte l){
                board = b;
                moves = m;
                father = f;
                lastMove = l;

                Tree.nodes++;       //toremove
                //System.out.print("\r" + nodes + " " + activeBoards.size());
            }


            public void calcSons(){

                Board newBoard;
                TreeNode ret;

                if(lastMove!=4 && board.bucox > 0){          //swap in alto
                
                    newBoard = new Board(board.table);    //qua porei fregare la board se c'e' collisione e ho una dist minore

                    ret = new TreeNode(newBoard, (short)(moves+1), this, (byte)1);

                    //aggiorno dist: real-expected
                    if(board.bucox-1 < (ret.board.table[board.bucox-1][board.bucoy]-1)/ret.board.table.length){
                        ret.board.dist = board.dist - 1;
                    }
                    else{
                        ret.board.dist = board.dist + 1;
                    }
                
                    //swap
                    ret.board.table[board.bucox][board.bucoy] = ret.board.table[board.bucox-1][board.bucoy];
                    ret.board.table[board.bucox-1][board.bucoy] = 0;

                    //update posizione buco, sale
                    ret.board.bucox = (short)(board.bucox-1);
                    ret.board.bucoy = board.bucoy;

                    checkCollision(ret);
                }


                if(lastMove!=3 && board.bucoy > 0){          //swap a sx
                
                    newBoard = new Board(board.table);

                    ret = new TreeNode(newBoard, (short)(moves+1), this, (byte)2);

                    //aggiorno dist: real-expected, casella swappata va a dx
                    if(board.bucoy-1 < (ret.board.table[board.bucox][board.bucoy-1]-1)%ret.board.table.length){
                        ret.board.dist = board.dist - 1;
                    }
                    else{
                        ret.board.dist = board.dist + 1;
                    }

                    //swap
                    ret.board.table[board.bucox][board.bucoy] = ret.board.table[board.bucox][board.bucoy-1];
                    ret.board.table[board.bucox][board.bucoy-1] = 0;

                    //update buco, va a sx
                    ret.board.bucoy = (short)(board.bucoy-1);
                    ret.board.bucox = board.bucox;

                    checkCollision(ret);
                }


                if(lastMove!=2 && board.bucoy < board.table.length-1){       //swap a dx
                
                    newBoard = new Board(board.table);

                    ret = new TreeNode(newBoard, (short)(moves+1), this, (byte)3);

                    //aggiorno dist: real-expected, casella swappata va a sx
                    if(board.bucoy+1 > (ret.board.table[board.bucox][board.bucoy+1]-1)%ret.board.table.length){
                        ret.board.dist = board.dist - 1;
                    }
                    else{
                        ret.board.dist = board.dist + 1;
                    }
                    
                    //swap
                    ret.board.table[board.bucox][board.bucoy] = ret.board.table[board.bucox][board.bucoy+1];
                    ret.board.table[board.bucox][board.bucoy+1] = 0;

                    //update buco, va a dx
                    ret.board.bucoy = (short)(board.bucoy+1);
                    ret.board.bucox = board.bucox;

                    checkCollision(ret);
                }


                if(lastMove!=1 && board.bucox < board.table.length-1){      //swap in basso
                
                    newBoard = new Board(board.table);

                    ret = new TreeNode(newBoard, (short)(moves+1), this, (byte)4);

                    //aggiorno dist: real-expected
                    if(board.bucox+1 > (ret.board.table[board.bucox+1][board.bucoy]-1)/ret.board.table.length){
                        ret.board.dist = board.dist - 1;
                    }
                    else{
                        ret.board.dist = board.dist + 1;
                    }
                    
                    //swap
                    ret.board.table[board.bucox][board.bucoy] = ret.board.table[board.bucox+1][board.bucoy];
                    ret.board.table[board.bucox+1][board.bucoy] = 0;

                    //update buco, va in basso
                    ret.board.bucox = (short)(board.bucox+1);
                    ret.board.bucoy = board.bucoy;

                    checkCollision(ret);
                }

            }
            


            public static void checkCollision(TreeNode ret){
                //se gia' esiste, agisco su activeBoards
                TreeNode collisioner = activeBoards.get(ret.board);
                if(collisioner != null){

                    if(collisioner.board.dist+collisioner.moves <= ret.board.dist+ret.moves){
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
                return board.dist+moves - (o.board.dist+o.moves);
            }

            @Override
            public boolean equals(Object o){
                return board.dist+moves == (((TreeNode)(o)).board.dist+((TreeNode)(o)).moves);
            }
        }



        /////////////////////////////////////////////
        //TreeNode16 class
        /////////////////////////////////////////////

        public static class TreeNode16 extends TreeNode{

            public static HashMap<Board, Tree.TreeNode16> activeBoards = new HashMap<Board, Tree.TreeNode16>(2999, 0.5f);
            
            public static long mask = 31;   //1111b

            public short moves;       //mosse per arrivare qua === depth dell'albero
            public byte lastMove;    //ultima mossa fatta: 0->radice 1->su 2->sx 3->dx 4->giu
            public Board.Board16 board;
            //public boolean alive = true;

            public TreeNode16 father;

            public TreeNode16(Board.Board16 b, short m, TreeNode16 f, byte l){
                board = b;
                moves = m;
                father = f;
                lastMove = l;

                Tree.nodes++;       //toremove
                //System.out.print("\r" + nodes + " " + activeBoards.size());
            }


            public void calcSons(){

                Board.Board16 newBoard;
                TreeNode16 ret;

                if(lastMove!=4 && board.bucox > 0){          //swap in alto
                
                    newBoard = new Board.Board16(board.ctable);

                    ret = new TreeNode16(newBoard, (short)(moves+1), this, (byte)1);

                    //OKKKIOIOIOIOIOI, il buco e' l'indice da destra
                    byte buco = (byte)(board.lato - (board.bucox*board.lato + board.bucoy)*4);

                    //aggiorno dist: real-expected
                    if(board.bucox-1 < ((board.ctable|(mask<<(buco-(board.lato*4))))-1)/ret.board.table.length){
                        ret.board.dist = board.dist - 1;
                    }
                    else{
                        ret.board.dist = board.dist + 1;
                    }
                
                    //swap
                    ret.board.table[board.bucox][board.bucoy] = ret.board.table[board.bucox-1][board.bucoy];
                    ret.board.table[board.bucox-1][board.bucoy] = 0;

                    ret.board.ctable = 0;

                    //update posizione buco, sale
                    ret.board.bucox = (short)(board.bucox-1);
                    ret.board.bucoy = board.bucoy;

                    checkCollision(ret);
                }


                if(lastMove!=3 && board.bucoy > 0){          //swap a sx
                
                    newBoard = new Board.Board16(board.table);

                    ret = new TreeNode16(newBoard, (short)(moves+1), this, (byte)2);

                    //aggiorno dist: real-expected, casella swappata va a dx
                    if(board.bucoy-1 < (ret.board.table[board.bucox][board.bucoy-1]-1)%ret.board.table.length){
                        ret.board.dist = board.dist - 1;
                    }
                    else{
                        ret.board.dist = board.dist + 1;
                    }

                    //swap
                    ret.board.table[board.bucox][board.bucoy] = ret.board.table[board.bucox][board.bucoy-1];
                    ret.board.table[board.bucox][board.bucoy-1] = 0;

                    //update buco, va a sx
                    ret.board.bucoy = (short)(board.bucoy-1);
                    ret.board.bucox = board.bucox;

                    checkCollision(ret);
                }


                if(lastMove!=2 && board.bucoy < board.table.length-1){       //swap a dx
                
                    newBoard = new Board.Board16(board.table);

                    ret = new TreeNode16(newBoard, (short)(moves+1), this, (byte)3);

                    //aggiorno dist: real-expected, casella swappata va a sx
                    if(board.bucoy+1 > (ret.board.table[board.bucox][board.bucoy+1]-1)%ret.board.table.length){
                        ret.board.dist = board.dist - 1;
                    }
                    else{
                        ret.board.dist = board.dist + 1;
                    }
            
                    //swap
                    ret.board.table[board.bucox][board.bucoy] = ret.board.table[board.bucox][board.bucoy+1];
                    ret.board.table[board.bucox][board.bucoy+1] = 0;

                    //update buco, va a dx
                    ret.board.bucoy = (short)(board.bucoy+1);
                    ret.board.bucox = board.bucox;

                    checkCollision(ret);
                }


                if(lastMove!=1 && board.bucox < board.table.length-1){      //swap in basso
                
                    newBoard = new Board.Board16(board.table);

                    ret = new TreeNode16(newBoard, (short)(moves+1), this, (byte)4);

                    //aggiorno dist: real-expected
                    if(board.bucox+1 > (ret.board.table[board.bucox+1][board.bucoy]-1)/ret.board.table.length){
                        ret.board.dist = board.dist - 1;
                    }
                    else{
                        ret.board.dist = board.dist + 1;
                    }
                
                    //swap
                    ret.board.table[board.bucox][board.bucoy] = ret.board.table[board.bucox+1][board.bucoy];
                    ret.board.table[board.bucox+1][board.bucoy] = 0;

                    //update buco, va in basso
                    ret.board.bucox = (short)(board.bucox+1);
                    ret.board.bucoy = board.bucoy;

                    checkCollision(ret);
                }

            }


            /*public static void checkCollision(TreeNode ret){
                //se gia' esiste, agisco su activeBoards
                TreeNode collisioner = activeBoards.get(ret.board);
                if(collisioner != null){

                    if(collisioner.board.dist+collisioner.moves <= ret.board.dist+ret.moves){
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

                int diff = board.dist+moves - (o.board.dist+o.moves);

                if(diff>0)
                    return 1;
                
                if(diff<0)
                    return -1;
                
                return 0;
            }

            @Override
            public boolean equals(Object o){
                return board.dist+moves == (((TreeNode)(o)).board.dist+((TreeNode)(o)).moves);
            }
            */

        }


    }

    /////////////////////////////////////////////
    //IO operations
    ////////////////////////////////////////////

    static class FastReader{
        BufferedReader br;
        StringTokenizer st;
 
        public FastReader(String filename) throws FileNotFoundException
        {
            br = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename)));
        }
 
        String next()
        {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        short nextShort() { return Short.parseShort(next()); }
    }

    public static Board readInput(String filename) throws FileNotFoundException{
        
        FastReader sc = new FastReader(filename);

        short lato = sc.nextShort();

        short[][] tabella = new short[lato][lato];

        short n = (short)(lato*lato);
        short counter = 0;

        while(n-- > 0){
            tabella[counter/lato][counter%lato] = sc.nextShort();
            counter++;
        }

        return new Board(tabella);

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

        BufferedOutputStream out = new BufferedOutputStream(System.out);

        out.write(((counter-1) + "\n").getBytes());

        while(--counter >= 0){
            out.write(path[counter].board.toString().getBytes());
            out.write("\n".getBytes());
        }

        out.flush();
    }



    public static void main(String[] args) throws FileNotFoundException, IOException{

        Board tab;                              //
        if(args.length == 0)                    //
            tab = readInput("input.txt");       //toDelete
        else                                    //
            tab = readInput(args[0]);           //

        //Board tab = readInput(args[0]);

        Tree.TreeNode radice;

        /*if(tab.table.length <= 4){
            radice = new Tree.TreeNode16(tab, (short)(0), null, (byte)0);
        }
        else{*/
            radice = new Tree.TreeNode(tab, (short)(0), null, (byte)0);
        //}

        Tree albero = new Tree(radice);

        //albero.findSolver();

        printPath(albero.findSolver());

    }


}
