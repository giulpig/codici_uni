public class ChessKnight {

    private String position;
    private int position1, position2;

    public ChessKnight(String initial){
        if(isChessValidSquare(initial)== false){
            System.out.println("Illegal square");
            return;
        }
        position = initial;
        position1 = initial.charAt(0) - 97;
        position2 = initial.charAt(1) - 48;
    }

    private boolean isChessValidSquare(String toCheck){
        if(toCheck.length() == 2 
             && toCheck.charAt(0) >= 'a' 
             && toCheck.charAt(0) <= 'h'
             && toCheck.charAt(1) >= '0' 
             && toCheck.charAt(1) <= '8')
        {

            return true;
        }
        return false;
    }

    private boolean isKnightReachableSquare(String toCheck){
        final double DISTANCE = 2.23606797749979;
        int dist1 = position1 - toCheck.charAt(0) + 97;
        int dist2 = position2 - toCheck.charAt(1) + 48;

        if(isChessValidSquare(toCheck)
            && Math.sqrt(dist1*dist1 + dist2*dist2) == DISTANCE)
        {

            return true;
        }
        return false;
    }

    public void moveToSquare(String toMove){
        if(isKnightReachableSquare(toMove)){
            position = toMove;
            position1 = toMove.charAt(0) - 97;
            position2 = toMove.charAt(1) - 48;
        }
        else{
            System.out.println("Illegal move");
        }
    }

    public String toString(){
        return "Cavallo in " + position;
    }
    
    public static void main(String[] args) {
        ChessKnight mioCavallo = new ChessKnight("a1");
        mioCavallo.moveToSquare("a3");  //illegale
        mioCavallo.moveToSquare("b3");  //legale
        mioCavallo.moveToSquare("d4");
        System.out.println(mioCavallo);
    }


}
