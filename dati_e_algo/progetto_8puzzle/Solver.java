import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.StringTokenizer;

import java.io.*;       //Input/Output


public class Solver{

    /////////////////////////////////////////////
    //Tree class
    ////////////////////////////////////////////

    public static class Tree{
        public short N;
        public TreeNode root;
        public static PriorityQueue<TreeNode> pQueue = new PriorityQueue<TreeNode>();
        
        public static int nodes = 0;    //toremove
        
        public Tree(TreeNode r){
            root = r;
            root.board.calcManhattan();
        }

        public TreeNode findSolver(){
            pQueue.add(root);
            while(!pQueue.isEmpty()){

                TreeNode current = pQueue.peek();

                TreeNode son = current.nextSon();   //also updates activeBoards
                
                if(current.lastSonChecked == 3){
                    pQueue.poll();
                }

                //System.out.print("\r" + current.moves + " " + (current.moves + current.board.dist) + "   ");
                //System.out.print("\r" + nodes);
                //System.out.print("\r" + current.sons.length);
                //System.out.print("\r" + current.board.toString());

                if(son != null){

                    //System.out.print("\r" + son.board.dist + "   ");

                    if(son.board.dist == 0){
                        return son;
                    }

                    pQueue.add(son);
                }
                
            }
            System.out.println("Porcodiiiioioioi");      //toremove
            return null;
        }



        /////////////////////////////////////////////
        //TreeNode class
        ////////////////////////////////////////////

        public static class TreeNode implements Comparable<TreeNode>{
            
            public static HashMap<Board, TreeNode> activeBoards = new HashMap<Board, TreeNode>();
            
            public byte moves;       //mosse per arrivare qua === depth dell'albero
            public byte lastMove;    //ultima mossa fatta: 0->radice 1->su 2->sx 3->dx 4->giu
            public byte lastSonChecked = -1;
            public Board board;
            //public boolean alive = true;

            public TreeNode father;

            public TreeNode(Board b, byte m, TreeNode f, byte l){
                board = b;
                moves = m;
                father = f;
                lastMove = l;

                Tree.nodes++;       //toremove
                //System.out.print("\r" + nodes + " " + activeBoards.size());
            }


            public TreeNode nextSon(){

                short temp;
                Board newBoard;
                TreeNode ret = null;

                if(lastSonChecked==-1){
                    if(lastMove!=4 && board.bucox > 0){          //swap in alto
                    
                        newBoard = new Board(board.table);    //qua porei fregare la board se c'e' collisione e ho una dist minore

                        ret = new TreeNode(newBoard, (byte)(moves+1), this, (byte)1);

                        //aggiorno dist: real-expected
                        if(board.bucox-1 < (ret.board.table[board.bucox-1][board.bucoy]-1)/ret.board.table.length){
                            ret.board.dist = board.dist - 1;
                        }
                        else{
                            ret.board.dist = board.dist + 1;
                        }
                    
                        //swap
                        temp = ret.board.table[board.bucox][board.bucoy];
                        ret.board.table[board.bucox][board.bucoy] = ret.board.table[board.bucox-1][board.bucoy];
                        ret.board.table[board.bucox-1][board.bucoy] = temp;

                        //update posizione buco, sale
                        ret.board.bucox = (short)(board.bucox-1);
                        ret.board.bucoy = board.bucoy;
                    }
                    lastSonChecked++;
                }


                if(ret==null && lastSonChecked==0){
                    if(lastMove!=3 && board.bucoy > 0){          //swap a sx
                    
                        newBoard = new Board(board.table);

                        ret = new TreeNode(newBoard, (byte)(moves+1), this, (byte)2);

                        //aggiorno dist: real-expected, casella swappata va a dx
                        if(board.bucoy-1 < (ret.board.table[board.bucox][board.bucoy-1]-1)%ret.board.table.length){
                            ret.board.dist = board.dist - 1;
                        }
                        else{
                            ret.board.dist = board.dist + 1;
                        }

                        temp = ret.board.table[board.bucox][board.bucoy];
                        ret.board.table[board.bucox][board.bucoy] = ret.board.table[board.bucox][board.bucoy-1];
                        ret.board.table[board.bucox][board.bucoy-1] = temp;

                        //update buco, va a sx
                        ret.board.bucoy = (short)(board.bucoy-1);
                        ret.board.bucox = board.bucox;
                    }
                    lastSonChecked++;
                }


                if(ret==null && lastSonChecked==1){
                    if(lastMove!=2 && board.bucoy < board.table.length-1){       //swap a dx
                    
                        newBoard = new Board(board.table);

                        ret = new TreeNode(newBoard, (byte)(moves+1), this, (byte)3);

                        //aggiorno dist: real-expected, casella swappata va a sx
                        if(board.bucoy+1 > (ret.board.table[board.bucox][board.bucoy+1]-1)%ret.board.table.length){
                            ret.board.dist = board.dist - 1;
                        }
                        else{
                            ret.board.dist = board.dist + 1;
                        }
                
                        temp = ret.board.table[board.bucox][board.bucoy];
                        ret.board.table[board.bucox][board.bucoy] = ret.board.table[board.bucox][board.bucoy+1];
                        ret.board.table[board.bucox][board.bucoy+1] = temp;

                        //update buco, va a dx
                        ret.board.bucoy = (short)(board.bucoy+1);
                        ret.board.bucox = board.bucox;
                    }
                    lastSonChecked++;
                }


                if(ret==null && lastSonChecked==2){
                    if(lastMove!=1 && board.bucox < board.table.length-1){      //swap in basso
                    
                        newBoard = new Board(board.table);

                        ret = new TreeNode(newBoard, (byte)(moves+1), this, (byte)4);

                        //aggiorno dist: real-expected
                        if(board.bucox+1 > (ret.board.table[board.bucox+1][board.bucoy]-1)/ret.board.table.length){
                            ret.board.dist = board.dist - 1;
                        }
                        else{
                            ret.board.dist = board.dist + 1;
                        }
                    
                        temp = ret.board.table[board.bucox][board.bucoy];
                        ret.board.table[board.bucox][board.bucoy] = ret.board.table[board.bucox+1][board.bucoy];
                        ret.board.table[board.bucox+1][board.bucoy] = temp;

                        //update buco, va in basso
                        ret.board.bucox = (short)(board.bucox+1);
                        ret.board.bucoy = board.bucoy;

                        //all'ultimo controllo elimino la entry
                        //activeBoards.remove(this.board);
                    }
                    lastSonChecked++;
                }


                if(ret==null){
                    return null;
                }

                //se gia' esiste, agisco su activeBoards
                TreeNode collisioner = activeBoards.get(ret.board);
                if(collisioner != null){

                    if(collisioner.board.dist+collisioner.moves <= board.dist+moves){
                        ret = null;
                    }
                    else{
                        activeBoards.remove(collisioner.board);
                        pQueue.remove(collisioner);
                        collisioner = null;

                        activeBoards.put(board, this);
                    }
                }
                else{
                    activeBoards.put(board, this);
                }

                return ret;
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

        Tree.TreeNode radice = new Tree.TreeNode(tab, (byte)(0), null, (byte)0);
        Tree albero = new Tree(radice);

        //albero.findSolver();

        printPath(albero.findSolver());

    }


}
