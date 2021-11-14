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

                TreeNode current = pQueue.poll();

                current.calcSons();   //also update activeBoards

                System.out.print("\r" + current.moves + " " + (current.moves + current.board.dist) + "   ");
                //System.out.print("\r" + nodes);
                //System.out.print("\r" + current.sons.length);

                if(current.sons != null){

                    for(int i=0; i<current.sons.length; i++){
                        
                        if(current.sons[i].board.dist == 0){  //se e' nodo vincente
                            return current.sons[i];
                        }

                        pQueue.add(current.sons[i]);
                    }
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
            public byte lastMove;   //ultima mossa fatta: 0->radice 1->su 2->sx 3->dx 4->giu
            public Board board;
            //public boolean alive = true;

            public TreeNode father;
            public TreeNode[] sons;

            public TreeNode(Board b, byte m, TreeNode f, byte l){
                board = b;
                moves = m;
                father = f;
                lastMove = l;

                Tree.nodes++;       //toremove
            }

            public void calcSons(){   //calcola figli (non considera quelli con board gia' presenti)

                short bucox = (short)(board.buco / board.table.length);
                short bucoy = (short)(board.buco % board.table.length);

                short temp;
                byte count=0;
                
                TreeNode ret1=null, ret2=null, ret3=null, ret4=null;
                

                if(lastMove!=4 && bucox > 0){          //swap in alto
                    
                    Board newBoard = new Board(board.table);    //qua porei fregare la board se c'e' collisione e ho una dist minore

                    ret1 = new TreeNode(newBoard, (byte)(moves+1), this, (byte)1);

                    //aggiorno dist: real-expected
                    if(bucox-1 < (ret1.board.table[bucox-1][bucoy]-1)/ret1.board.table.length){
                        ret1.board.dist = board.dist - 1;
                    }
                    else{
                        ret1.board.dist = board.dist + 1;
                    }
                
                    //swap
                    temp = ret1.board.table[bucox][bucoy];
                    ret1.board.table[bucox][bucoy] = ret1.board.table[bucox-1][bucoy];
                    ret1.board.table[bucox-1][bucoy] = temp;

                    //update posizione buco, sale
                    ret1.board.buco = board.buco - board.table.length;

                    //se gia' esiste, agisco su activeBoards
                    TreeNode collisioner = activeBoards.get(ret1.board);
                    if(collisioner != null){
                        if(collisioner.board.dist+collisioner.moves <= board.dist+moves){
                            ret1 = null;
                        }
                        else{
                            count++;
                            activeBoards.remove(collisioner.board);
                            pQueue.remove(collisioner);
                            collisioner = null;

                            activeBoards.put(board, this);
                        }
                    }
                    else{
                        count++;
                        activeBoards.put(board, this);
                    }
                }


                if(lastMove!=3 && bucoy > 0){          //swap a sx
                    
                    Board newBoard = new Board(board.table);

                    ret2 = new TreeNode(newBoard, (byte)(moves+1), this, (byte)2);

                    //aggiorno dist: real-expected, casella swappata va a dx
                    if(bucoy-1 < (ret2.board.table[bucox][bucoy-1]-1)/ret2.board.table.length){
                        ret2.board.dist = board.dist - 1;
                    }
                    else{
                        ret2.board.dist = board.dist + 1;
                    }

                    temp = ret2.board.table[bucox][bucoy];
                    ret2.board.table[bucox][bucoy] = ret2.board.table[bucox][bucoy-1];
                    ret2.board.table[bucox][bucoy-1] = temp;

                    //update buco, va a sx
                    ret2.board.buco = board.buco - 1;

                    //se gia' esiste, agisco su activeBoards
                    TreeNode collisioner = activeBoards.get(ret2.board);
                    if(collisioner != null){
                        if(collisioner.board.dist+collisioner.moves <= board.dist+moves){
                            ret2 = null;
                        }
                        else{
                            count++;
                            activeBoards.remove(collisioner.board);
                            pQueue.remove(collisioner);
                            collisioner = null;

                            activeBoards.put(board, this);
                        }
                    }
                    else{
                        count++;
                        activeBoards.put(board, this);
                    }
                }

                
                if(lastMove!=2 && bucoy < board.table.length-1){       //swap a dx
                    
                    Board newBoard = new Board(board.table);

                    ret3 = new TreeNode(newBoard, (byte)(moves+1), this, (byte)3);

                    //aggiorno dist: real-expected, casella swappata va a sx
                    if(bucoy+1 > (ret3.board.table[bucox][bucoy+1]-1)/ret3.board.table.length){
                        ret3.board.dist = board.dist - 1;
                    }
                    else{
                        ret3.board.dist = board.dist + 1;
                    }
            
                    temp = ret3.board.table[bucox][bucoy];
                    ret3.board.table[bucox][bucoy] = ret3.board.table[bucox][bucoy+1];
                    ret3.board.table[bucox][bucoy+1] = temp;

                    //update buco, va a dx
                    ret3.board.buco = board.buco + 1;

                    //se gia' esiste, agisco su activeBoards
                    TreeNode collisioner = activeBoards.get(ret3.board);
                    if(collisioner != null){
                        if(collisioner.board.dist+collisioner.moves <= board.dist+moves){
                            ret3 = null;
                        }
                        else{
                            count++;
                            activeBoards.remove(collisioner.board);
                            pQueue.remove(collisioner);
                            collisioner = null;
                            
                            activeBoards.put(board, this);
                        }
                    }
                    else{
                        count++;
                        activeBoards.put(board, this);
                    }
                }


                if(lastMove!=1 && bucox < board.table.length-1){      //swap in basso
                    
                    Board newBoard = new Board(board.table);

                    ret4 = new TreeNode(newBoard, (byte)(moves+1), this, (byte)4);

                    //aggiorno dist: real-expected
                    if(bucox+1 > (ret4.board.table[bucox+1][bucoy]-1)/ret4.board.table.length){
                        ret4.board.dist = board.dist - 1;
                    }
                    else{
                        ret4.board.dist = board.dist + 1;
                    }
                
                    temp = ret4.board.table[bucox][bucoy];
                    ret4.board.table[bucox][bucoy] = ret4.board.table[bucox+1][bucoy];
                    ret4.board.table[bucox+1][bucoy] = temp;

                    //update buco, va in basso
                    ret4.board.buco = board.buco + board.table.length;

                    //se gia' esiste, agisco su activeBoards
                    TreeNode collisioner = activeBoards.get(ret4.board);
                    if(collisioner != null){
                        if(collisioner.board.dist+collisioner.moves <= board.dist+moves){
                            ret4 = null;
                        }
                        else{
                            count++;
                            activeBoards.remove(collisioner.board);
                            pQueue.remove(collisioner);
                            collisioner = null;

                            activeBoards.put(board, this);
                        }
                    }
                    else{
                        count++;
                        activeBoards.put(board, this);
                    }
                }



                if(count == 0)
                    return;

                sons = new TreeNode[count];
                if(ret4!=null)
                    sons[--count] = ret4;
                if(ret3!=null)
                    sons[--count] = ret3;
                if(ret2!=null)
                    sons[--count] = ret2;
                if(ret1!=null)
                    sons[--count] = ret1;
            }



            public int compareTo(TreeNode o){

                int diff = board.dist+moves - (o.board.dist+o.moves);

                if(diff>0)
                    return 1;
                
                if(diff<0)
                    return -1;
                
                return 0;
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

        printPath(albero.findSolver());

    }


}