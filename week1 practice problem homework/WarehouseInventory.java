public class WarehouseInventory {
    public static void analyzerInventory(int sectionA[],int sectionB[]){
        int sumA=0;
        int sumB=0;
        int max=0;
        int item=1;
        for(int i=0;i<sectionA.length;i++){
            if(sectionA[i]>max){
                max=sectionA[i];
                item=i;
            }
            sumA=sumA+sectionA[i];
            sumB=sumB+sectionB[i];
        }

        if(sumA==sumB){
            System.out.println("Section A Total: "+ sumA+" | Section B Total: "+sumB+"  | Status :Balanced |  Highest Quantity: "+max+"(Section A, item "+item+")");
        }
    }
    public static void main(String[]args){
        int sectionA[]={20,15,30};
        int sectionB[]={25,10,30};
        analyzerInventory(sectionA,sectionB);


    }
}