import java.util.*;

interface ShippingType_Q2 {
    double calculateCharge(double weightKg);
    String getName();
}

class StandardShipping_Q2 implements ShippingType_Q2 {
    @Override
    public double calculateCharge(double weightKg) {
        return 40.0 + (10.0 * weightKg);
    }

    @Override
    public String getName() { return "Standard"; }
}

class ExpressShipping_Q2 implements ShippingType_Q2 {
    @Override
    public double calculateCharge(double weightKg) {
        return 80.0 + (15.0 * weightKg);
    }

    @Override
    public String getName() { return "Express"; }
}

class FragileShipping_Q2 implements ShippingType_Q2 {
    private StandardShipping_Q2 baseShipping = new StandardShipping_Q2();

    @Override
    public double calculateCharge(double weightKg) {
        return baseShipping.calculateCharge(weightKg) + 50.0;
    }

    @Override
    public String getName() { return "Fragile"; }
}

interface NotificationChannel_Q2 {
    void notifyUpdate(String parcelId, String status);
}

class SmsChannel_Q2 implements NotificationChannel_Q2 {
    @Override
    public void notifyUpdate(String parcelId, String status) {
        System.out.printf("[SMS] %s is now %s.%n", parcelId, status);
    }
}

class EmailChannel_Q2 implements NotificationChannel_Q2 {
    @Override
    public void notifyUpdate(String parcelId, String status) {
        System.out.printf("[Email] %s is now %s.%n", parcelId, status);
    }
}

class Parcel_Q2 {
    private String parcelId;
    private double weightKg;
    private ShippingType_Q2 shippingType;
    private String status;
    private double charge;
    private List<NotificationChannel_Q2> channels = new ArrayList<>();

    private static final List<String> STATUS_FLOW = Arrays.asList(
        "BOOKED", "PICKED_UP", "IN_TRANSIT", "OUT_FOR_DELIVERY", "DELIVERED"
    );

    public Parcel_Q2(String parcelId, double weightKg, ShippingType_Q2 shippingType) {
        this.parcelId = parcelId;
        this.weightKg = weightKg;
        this.shippingType = shippingType;
        this.charge = shippingType.calculateCharge(weightKg);
        this.status = "BOOKED";
    }

    public String getParcelId() { return parcelId; }
    public double getCharge() { return charge; }
    public String getStatus() { return status; }

    public void subscribeChannel(NotificationChannel_Q2 channel) {
        channels.add(channel);
    }

    public void notifyChannels() {
        for (NotificationChannel_Q2 channel : channels) {
            channel.notifyUpdate(parcelId, status);
        }
    }

    public void updateStatus(String newStatus) {
        int currentIndex = STATUS_FLOW.indexOf(this.status);
        int targetIndex = STATUS_FLOW.indexOf(newStatus);

        if (targetIndex != currentIndex + 1) {
            System.out.printf("Invalid transition: %s -> %s is not allowed.%n", this.status, newStatus);
            return;
        }

        this.status = newStatus;
        notifyChannels();
    }

    public boolean cancel() {
        if ("BOOKED".equals(this.status)) {
            this.status = "CANCELLED";
            System.out.printf("Parcel %s cancelled successfully.%n", parcelId);
            return true;
        } else {
            System.out.printf("Cancellation failed: %s can be cancelled only while BOOKED.%n", parcelId);
            return false;
        }
    }
}

public class SwiftShipTracker_Q2 {
    public static void main(String[] args) {
        Parcel_Q2 parcel = new Parcel_Q2("P101", 2.0, new ExpressShipping_Q2());
        System.out.printf("Parcel %s booked (Express, %.0f kg). Charge: %.2f.%n", parcel.getParcelId(), 2.0, parcel.getCharge());

        parcel.subscribeChannel(new SmsChannel_Q2());
        parcel.subscribeChannel(new EmailChannel_Q2());

        parcel.notifyChannels();
        parcel.updateStatus("PICKED_UP");
        parcel.cancel();
        parcel.updateStatus("IN_TRANSIT");
        parcel.updateStatus("DELIVERED");
    }
}
