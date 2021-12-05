import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.StringTokenizer;

import java.io.*;       //Input/Output


public class Solver{
    static public int counter = 0;    //to remove

    ////////////////////////////////////////////
    //Tree class
    ////////////////////////////////////////////

    public static PriorityQueue<Tree.TreeNode> pQueue = new PriorityQueue<Tree.TreeNode>();
    
    public static class Tree{
        public TreeNode root;
        public short lastLevel;
        
        public static int nodes = 0;    //toremove
        
        public Tree(TreeNode r){
            root = r;
        }

        public TreeNode findSolver(){
            pQueue.add(root);
            while(!pQueue.isEmpty()){

                TreeNode current = pQueue.poll();

                TreeNode winner = current.calcSons();
                if(winner != null)   //true->win, also updates activeBoards and pQueue
                    return winner;

                if(current.moves+2*current.board.cdist+current.board.mdist > lastLevel){
                    System.out.print("\r" + (current.moves+2*current.board.cdist+current.board.mdist) + "   ");
                    lastLevel = (short)(current.moves+2*current.board.cdist+current.board.mdist);
                }
                //System.out.print("\r" + nodes + " ");
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


            public TreeNode calcSons(){

                Board newBoard;
                TreeNode ret;

                if(lastMove!=4 && board.bucox > 0){          //swap in alto
                
                    newBoard = new Board(board.table);    //qua porei fregare la board se c'e' collisione e ho una dist minore

                    ret = new TreeNode(newBoard, (short)(moves+1), this, (byte)1);

                    //aggiorno dist: real-expected
                    if(Board.shouldbe[ret.board.table[board.bucox-1][board.bucoy]][0] >= board.bucox){
                        ret.board.mdist = (short)(board.mdist - 1);
                    }
                    else{
                        ret.board.mdist = (short)(board.mdist + 1);
                    }
                    
                    //------aggiorno hash modificando tile sopra il buco------
                    //ret.board.hash = board.hash + ret.board.table[board.bucox-1][board.bucoy] * (Board.npowers[board.table.length*(board.bucox)+board.bucoy] - Board.npowers[board.table.length*(board.bucox-1)+board.bucoy]);

                    //swap
                    ret.board.table[board.bucox][board.bucoy] = ret.board.table[board.bucox-1][board.bucoy];
                    ret.board.table[board.bucox-1][board.bucoy] = 0;

                    if(ret.board.mdist == 0){
                        return ret;
                    }

                    //update linear confict
                    for(int i=0; i<board.bucoy; i++){
                        if(Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][0] == board.bucox-1 && Board.shouldbe[ret.board.table[board.bucox-1][i]][0] == board.bucox-1 && Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][1] < Board.shouldbe[ret.board.table[board.bucox-1][i]][1]){
                            ret.board.cdist--;      //remove old confl
                        }
                        if(Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][0] == board.bucox && Board.shouldbe[ret.board.table[board.bucox][i]][0] == board.bucox && Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][1] < Board.shouldbe[ret.board.table[board.bucox][i]][1]){
                            ret.board.cdist++;      //add new confl.
                        }
                    }
                    for(int i=board.bucoy+1; i<board.table.length; i++){
                        if(Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][0] == board.bucox-1 && Board.shouldbe[ret.board.table[board.bucox-1][i]][0] == board.bucox-1 && Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][1] > Board.shouldbe[ret.board.table[board.bucox-1][i]][1]){
                            ret.board.cdist--;      //remove old confl
                        }
                        if(Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][0] == board.bucox && Board.shouldbe[ret.board.table[board.bucox][i]][0] == board.bucox && Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][1] > Board.shouldbe[ret.board.table[board.bucox][i]][1]){
                            ret.board.cdist++;      //add new confl.
                        }
                    }
                    ret.board.cdist += board.cdist;
                    
                    //update posizione buco, sale
                    ret.board.bucox = board.bucox-1;
                    ret.board.bucoy = board.bucoy;
                    

                    checkCollision(ret);
                }


                if(lastMove!=3 && board.bucoy > 0){          //swap a sx
                
                    newBoard = new Board(board.table);

                    ret = new TreeNode(newBoard, (short)(moves+1), this, (byte)2);

                    //aggiorno dist: real-expected, casella swappata va a dx
                    if(Board.shouldbe[ret.board.table[board.bucox][board.bucoy-1]][1] >= board.bucoy){
                        ret.board.mdist = (short)(board.mdist - 1);
                    }
                    else{
                        ret.board.mdist = (short)(board.mdist + 1);
                    }

                    //swap
                    ret.board.table[board.bucox][board.bucoy] = ret.board.table[board.bucox][board.bucoy-1];
                    ret.board.table[board.bucox][board.bucoy-1] = 0;

                    if(ret.board.mdist == 0){
                        return ret;
                    }
                    
                    //------aggiorno hash modificando tile sopra il buco------
                    //ret.board.hash = board.hash + ret.board.table[board.bucox][board.bucoy-1] * (Board.npowers[board.table.length*(board.bucox)+board.bucoy] - Board.npowers[board.table.length*(board.bucox)+board.bucoy-1]);

                    //update linear confict
                    for(int i=0; i<board.bucox; i++){
                        if(Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][1] == board.bucoy-1 && Board.shouldbe[ret.board.table[i][board.bucoy-1]][1] == board.bucoy-1 && Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][0] < Board.shouldbe[ret.board.table[i][board.bucoy-1]][0]){
                            ret.board.cdist--;      //remove old confl
                        }
                        if(Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][1] == board.bucoy && Board.shouldbe[ret.board.table[i][board.bucoy]][1] == board.bucoy && Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][0] < Board.shouldbe[ret.board.table[i][board.bucoy]][0]){
                            ret.board.cdist++;      //add new confl.
                        }
                    }
                    for(int i=board.bucox+1; i<board.table.length; i++){
                        if(Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][1] == board.bucoy-1 && Board.shouldbe[ret.board.table[i][board.bucoy-1]][1] == board.bucoy-1 && Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][0] > Board.shouldbe[ret.board.table[i][board.bucoy-1]][0]){
                            ret.board.cdist--;      //remove old confl
                        }
                        if(Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][1] == board.bucoy && Board.shouldbe[ret.board.table[i][board.bucoy]][1] == board.bucoy && Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][0] > Board.shouldbe[ret.board.table[i][board.bucoy]][0]){
                            ret.board.cdist++;      //add new confl.
                        }
                    }
                    ret.board.cdist += board.cdist;


                    //update buco, va a sx
                    ret.board.bucoy = board.bucoy-1;
                    ret.board.bucox = board.bucox;

                    checkCollision(ret);
                }


                if(lastMove!=2 && board.bucoy < board.table.length-1){       //swap a dx
                
                    newBoard = new Board(board.table);

                    ret = new TreeNode(newBoard, (short)(moves+1), this, (byte)3);

                    //aggiorno dist: real-expected, casella swappata va a sx
                    if(Board.shouldbe[ret.board.table[board.bucox][board.bucoy+1]][1] <= board.bucoy){
                        ret.board.mdist = (short)(board.mdist - 1);
                    }
                    else{
                        ret.board.mdist = (short)(board.mdist + 1);
                    }

                    //swap
                    ret.board.table[board.bucox][board.bucoy] = ret.board.table[board.bucox][board.bucoy+1];
                    ret.board.table[board.bucox][board.bucoy+1] = 0;

                    if(ret.board.mdist == 0){
                        return ret;
                    }

                    //------aggiorno hash modificando tile sopra il buco------
                    //ret.board.hash = board.hash + ret.board.table[board.bucox][board.bucoy+1] * (Board.npowers[board.table.length*(board.bucox)+board.bucoy] - Board.npowers[board.table.length*(board.bucox)+board.bucoy+1]);
                    

                    //update linear confict
                    for(int i=0; i<board.bucox; i++){
                        if(Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][1] == board.bucoy+1 && Board.shouldbe[ret.board.table[i][board.bucoy+1]][1] == board.bucoy+1 && Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][0] < Board.shouldbe[ret.board.table[i][board.bucoy+1]][0]){
                            ret.board.cdist--;      //remove old confl
                        }
                        if(Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][1] == board.bucoy && Board.shouldbe[ret.board.table[i][board.bucoy]][1] == board.bucoy && Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][0] < Board.shouldbe[ret.board.table[i][board.bucoy]][0]){
                            ret.board.cdist++;      //add new confl.
                        }
                    }
                    for(int i=board.bucox+1; i<board.table.length; i++){
                        if(Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][1] == board.bucoy+1 && Board.shouldbe[ret.board.table[i][board.bucoy+1]][1] == board.bucoy+1 && Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][0] > Board.shouldbe[ret.board.table[i][board.bucoy+1]][0]){
                            ret.board.cdist--;      //remove old confl
                        }
                        if(Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][1] == board.bucoy && Board.shouldbe[ret.board.table[i][board.bucoy]][1] == board.bucoy && Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][0] > Board.shouldbe[ret.board.table[i][board.bucoy]][0]){
                            ret.board.cdist++;      //add new confl.
                        }
                    }
                    ret.board.cdist += board.cdist;

                    //update buco, va a dx
                    ret.board.bucoy = board.bucoy+1;
                    ret.board.bucox = board.bucox;

                    checkCollision(ret);
                }


                if(lastMove!=1 && board.bucox < board.table.length-1){      //swap in basso
                
                    newBoard = new Board(board.table);

                    ret = new TreeNode(newBoard, (short)(moves+1), this, (byte)4);

                    //aggiorno dist: real-expected
                    if(Board.shouldbe[ret.board.table[board.bucox+1][board.bucoy]][0] <= board.bucox){
                        ret.board.mdist = (short)(board.mdist - 1);
                    }
                    else{
                        ret.board.mdist = (short)(board.mdist + 1);
                    }

                    //------aggiorno hash modificando tile sopra il buco------
                    //ret.board.hash = board.hash + ret.board.table[board.bucox+1][board.bucoy] * (Board.npowers[board.table.length*(board.bucox)+board.bucoy] - Board.npowers[board.table.length*(board.bucox+1)+board.bucoy]);

                    //swap
                    ret.board.table[board.bucox][board.bucoy] = ret.board.table[board.bucox+1][board.bucoy];
                    ret.board.table[board.bucox+1][board.bucoy] = 0;

                    if(ret.board.mdist == 0){
                        return ret;
                    }

                    //update linear confict
                    for(int i=0; i<board.bucoy; i++){
                        if(Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][0] == board.bucox+1 && Board.shouldbe[ret.board.table[board.bucox+1][i]][0] == board.bucox+1 && Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][1] < Board.shouldbe[ret.board.table[board.bucox+1][i]][1]){
                            ret.board.cdist--;      //remove old confl
                        }
                        if(Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][0] == board.bucox && Board.shouldbe[ret.board.table[board.bucox][i]][0] == board.bucox && Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][1] < Board.shouldbe[ret.board.table[board.bucox][i]][1]){
                            ret.board.cdist++;      //add new confl.
                        }
                    }
                    for(int i=board.bucoy+1; i<board.table.length; i++){
                        if(Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][0] == board.bucox+1 && Board.shouldbe[ret.board.table[board.bucox+1][i]][0] == board.bucox+1 && Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][1] > Board.shouldbe[ret.board.table[board.bucox+1][i]][1]){
                            ret.board.cdist--;      //remove old confl
                        }
                        if(Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][0] == board.bucox && Board.shouldbe[ret.board.table[board.bucox][i]][0] == board.bucox && Board.shouldbe[ret.board.table[board.bucox][board.bucoy]][1] > Board.shouldbe[ret.board.table[board.bucox][i]][1]){
                            ret.board.cdist++;      //add new confl.
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
                TreeNode collisioner = activeBoards.get(ret.board);
                if(collisioner != null){

                    if(collisioner.board.mdist+collisioner.board.cdist*2+collisioner.moves <= ret.board.mdist+ret.board.cdist*2+ret.moves){
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
                return board.mdist+board.cdist*2+moves - (o.board.mdist+o.board.cdist*2+o.moves);
            }

            @Override
            public boolean equals(Object o){
                return board.mdist+board.cdist*2+moves == (((TreeNode)(o)).board.mdist+((TreeNode)(o)).board.cdist*2+((TreeNode)(o)).moves);
                //return false;
            }
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

        int nextInt() { return Integer.parseInt(next()); }
    }

    public static Board readInput(String filename) throws FileNotFoundException{
        
        FastReader sc = new FastReader(filename);

        int lato = sc.nextInt();

        int[][] tabella = new int[lato][lato];

        int n = lato*lato;
        int counter = 0;

        while(n-- > 0){
            tabella[counter/lato][counter%lato] = sc.nextInt();
            counter++;
        }

        return new Board(tabella, true);

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

        radice = new Tree.TreeNode(tab, (short)(0), null, (byte)0);

        Tree albero = new Tree(radice);

        //albero.findSolver();

        printPath(albero.findSolver());

    }


}
