public class DeliverySlot {
    private final String orderId;
    private final String timeSlot;

    public DeliverySlot(String orderId, String timeSlot) {
        if (orderId == null || orderId.trim().length() == 0) {
            throw new IllegalArgumentException();
        }
        this.orderId = orderId;
        this.timeSlot = timeSlot;
    }

    public DeliverySlot(String orderId) {
        this(orderId, "ASAP");
    }

    public String getOrderId() {
        return orderId;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    boolean isPeakHour() {
        if (timeSlot.equals("12:00-13:00")) {
            return true;
        }
        if (timeSlot.equals("13:00-14:00")) {
            return true;
        }
        if (timeSlot.equals("19:00-20:00")) {
            return true;
        }
        if (timeSlot.equals("20:00-21:00")) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new DeliverySlot("ORD101", "13:00-14:00").isPeakHour());
        System.out.println(new DeliverySlot("ORD102").isPeakHour());
    }
}
