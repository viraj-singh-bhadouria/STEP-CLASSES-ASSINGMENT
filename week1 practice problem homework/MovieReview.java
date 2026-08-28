import java.util.Scanner;
public class MovieReview {
    public static void classifyWordLengths(String review){
    int s=0;
    int m=0;
    int l=0;
    int count=0;
        for(int i=0;i<review.length();i++){
          if(review.charAt(i)!=' '){
            count=count+1;
          }else{
            if(count<=4){
                s=s+1;
                count=1;
            }else if(count<=8){
                m=m+1;
                count=1;
            }else{
                l=l+1;
                count=1;
            }
          }
        }
         if(count<=4){
                s=s+1;
                count=1;
            }else if(count<=8){
                m=m+1;
                count=1;
            }else{
                l=l+1;
                count=1;
            }

        System.out.println("Short: "+s+" | Medium: "+m+ " | Long:"+l);
    }
    public static void main(String[]args){
        String comment= "This movie was absolutely fantastic and thrilling" ;
        classifyWordLengths(comment);
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter your movie review: ");
        String feedback= sc.nextLine();
        classifyWordLengths(feedback);
        sc.close();


}
}