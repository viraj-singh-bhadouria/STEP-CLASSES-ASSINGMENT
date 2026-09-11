public class BookInventory {
    private int copiesTotal;
    private int copiesAvailable;

    public BookInventory(int copiesTotal) {
        if (copiesTotal <= 0) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    public void checkOut() {
        if (this.copiesAvailable > 0) {
            this.copiesAvailable--;
        }
    }

    public void checkIn() {
        if (this.copiesAvailable < this.copiesTotal) {
            this.copiesAvailable++;
        }
    }

    public int getCopiesAvailable() {
        return this.copiesAvailable;
    }

    public static void main(String[] args) {
        try {
            new BookInventory(0);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        BookInventory b = new BookInventory(3);
        b.checkOut(); 
        b.checkOut(); 
        b.checkOut(); 
        b.checkOut();
        System.out.println(b.getCopiesAvailable());

        b.checkIn(); 
        b.checkIn(); 
        b.checkIn(); 
        b.checkIn();
        System.out.println(b.getCopiesAvailable());
    }
}
