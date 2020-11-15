public class SubstringGeneratorITER {
 
    // Function to print all substring
    public static void SubString(String str, int n)
    {
       for (int i = 0; i < n; i++) 
           for (int j = i+1; j <= n; j++){
               str.substring(i, j);
           }
    }
 
    public static void main(String[] args)
    {
        String str = args[0];
        SubString(str, str.length());
    }
}
