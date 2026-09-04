public class TypingSpeed {
   public static void checkTypingAccuracy(String original,String typed){
        int count=0;
    int Accuracy;
        for(int i=0;i<original.length();i++){
           if(original.charAt(i)==typed.charAt(i)){
            count=count+1;
           }else{
            System.out.println("First mismatch at position "+ count);
            break;
           }
        }
        if(count==original.length()){
            System.out.println("No mismatches");
        }
        System.out.println("Matched :"+ count +"/"+original.length());
        Accuracy=(count*100)/original.length();
        System.out.println("Accuracy: " + Accuracy+"%");
    }
    public static void main(String[]args){
        String original1 = "hello world";
        String typed1="hello worlt";
        checkTypingAccuracy(original1,typed1);
      String original2="coding";
       String typed2="coding";
        checkTypingAccuracy(original2,typed2);

    }
}