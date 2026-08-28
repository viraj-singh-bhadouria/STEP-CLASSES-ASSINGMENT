public class TrafficSignal {
    public static void findLongestStreak(String signalLog){
        int longest=0;
        int count=1;
        char colour=signalLog.charAt(longest);
        for(int i=0;i<signalLog.length()-1;i++){
            if(signalLog.charAt(i)==signalLog.charAt(i+1)){
                count=count+1;
            }else{
                if(count>longest){
                longest=count;
                colour=signalLog.charAt(i);
                count=1;
            }
            }

        }
        System.out.println("Longest streak :  "+ colour+"  repeated times"+longest);
        
    }
    public static void main(String[]args){
        String signal1="RRGGGYRR";
        findLongestStreak(signal1);
        String signal2="RRRRYYGG" ;
        findLongestStreak(signal2);

    }
}