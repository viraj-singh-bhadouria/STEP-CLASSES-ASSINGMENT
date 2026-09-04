public class FoodOrder {
    private final String studentName;
    private final String dishName;
    private boolean delivered;

    public FoodOrder(String studentName, String dishName) {
        if (!isValid(studentName)) {
            throw new IllegalArgumentException();
        }
        if (!isValid(dishName)) {
            throw new IllegalArgumentException();
        }
        this.studentName = studentName.trim();
        this.dishName = dishName.trim();
        this.delivered = false;
    }

    private static boolean isValid(String str) {
        if (str == null) {
            return false;
        }
        String trimmed = str.trim();
        return trimmed.length() > 0;
    }

    void markDelivered() {
        if (this.delivered) {
            System.out.println("Order was already marked delivered");
        } else {
            this.delivered = true;
            System.out.println("Order marked delivered");
        }
    }

    public String getStudentName() {
        return studentName;
    }

    public String getDishName() {
        return dishName;
    }

    public boolean isDelivered() {
        return delivered;
    }

    static void processBatch(String[][] rawOrders) {
        int valid = 0;
        int rejected = 0;

        if (rawOrders != null) {
            for (int i = 0; i < rawOrders.length; i++) {
                String[] pair = rawOrders[i];
                if (pair == null || pair.length < 2) {
                    rejected++;
                    continue;
                }
                try {
                    new FoodOrder(pair[0], pair[1]);
                    valid++;
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }

    public static void main(String[] args) {
        String[][] rawOrders = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };
        processBatch(rawOrders);
    }
}