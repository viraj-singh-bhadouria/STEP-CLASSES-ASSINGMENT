public class DuplicateSeat{
    public static void checkDuplicateSeats(int seatNumbers[]) {
        boolean found =false;
        for(int i=0;i<seatNumbers.length;i++){
            for(int j=i+1;j<seatNumbers.length;j++){
                if(seatNumbers[i]==seatNumbers[j]){
                    System.out.println("Duplicate seat found  "+ seatNumbers[i]);
                    found=true;
                    break;
                }
            }
        }
          if(!found){
                    System.out.println("Duplicate seat not found");
                }
    }
            public static void main(String[]args){
                    int seatNumbers1[]={101, 102, 103, 102, 105};
                    checkDuplicateSeats(seatNumbers1);
                     int seatNumbers2[]={101, 102, 103, 104, 105} ;
                     checkDuplicateSeats(seatNumbers2);
                     
            }
    }