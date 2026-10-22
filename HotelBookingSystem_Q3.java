import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

class Room_Q3 {
    private String roomName;
    private double ratePerNight;

    public Room_Q3(String roomName, double ratePerNight) {
        this.roomName = roomName;
        this.ratePerNight = ratePerNight;
    }

    public String getRoomName() { return roomName; }

    public double calculatePrice(long nights) {
        return ratePerNight * nights;
    }
}

class DeluxeRoom_Q3 extends Room_Q3 {
    public DeluxeRoom_Q3(String roomName) {
        super(roomName, 200.0);
    }
}

class StandardRoom_Q3 extends Room_Q3 {
    public StandardRoom_Q3(String roomName) {
        super(roomName, 150.0);
    }
}

class Reservation_Q3 {
    private String resId;
    private Room_Q3 room;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private double totalPrice;

    public Reservation_Q3(String resId, Room_Q3 room, LocalDate startDate, LocalDate endDate) {
        this.resId = resId;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "ACTIVE";

        long nights = ChronoUnit.DAYS.between(startDate, endDate);
        this.totalPrice = room.calculatePrice(nights);
    }

    public Room_Q3 getRoom() { return room; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getTotalPrice() { return totalPrice; }

    public boolean overlapsWith(LocalDate reqStart, LocalDate reqEnd) {
        if (!"ACTIVE".equals(this.status)) {
            return false;
        }
        return reqStart.isBefore(this.endDate) && reqEnd.isAfter(this.startDate);
    }
}

class BookingManager_Q3 {
    private List<Room_Q3> rooms = new ArrayList<>();
    private List<Reservation_Q3> reservations = new ArrayList<>();

    public void addRoom(Room_Q3 room) {
        rooms.add(room);
    }

    public Reservation_Q3 bookRoom(String roomName, String startDateStr, String endDateStr) {
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        Room_Q3 room = rooms.stream()
                .filter(r -> r.getRoomName().equalsIgnoreCase(roomName))
                .findFirst()
                .orElse(null);

        if (room == null) {
            System.out.println("Room " + roomName + " does not exist.");
            return null;
        }

        for (Reservation_Q3 res : reservations) {
            if (res.getRoom().getRoomName().equalsIgnoreCase(roomName) && res.overlapsWith(startDate, endDate)) {
                System.out.printf("Booking failed: %s is not available for %s to %s.%n", roomName, startDateStr, endDateStr);
                return null;
            }
        }

        Reservation_Q3 reservation = new Reservation_Q3("RES-" + (reservations.size() + 1), room, startDate, endDate);
        reservations.add(reservation);
        System.out.printf("%s booked from %s to %s. Total price: $%.2f%n", roomName, startDateStr, endDateStr, reservation.getTotalPrice());
        return reservation;
    }

    public boolean cancelReservation(String roomName) {
        for (Reservation_Q3 res : reservations) {
            if (res.getRoom().getRoomName().equalsIgnoreCase(roomName) && "ACTIVE".equals(res.getStatus())) {
                res.setStatus("CANCELLED");
                System.out.println("Reservation for " + roomName + " cancelled successfully.");
                return true;
            }
        }
        System.out.println("No active reservation found for " + roomName);
        return false;
    }
}

public class HotelBookingSystem_Q3 {
    public static void main(String[] args) {
        BookingManager_Q3 bm = new BookingManager_Q3();
        bm.addRoom(new DeluxeRoom_Q3("Deluxe Room 101"));
        bm.addRoom(new StandardRoom_Q3("Standard Room 205"));

        bm.bookRoom("Deluxe Room 101", "2024-12-01", "2024-12-05");
        bm.bookRoom("Standard Room 205", "2024-12-03", "2024-12-07");
        bm.bookRoom("Deluxe Room 101", "2024-12-03", "2024-12-07");
        bm.cancelReservation("Deluxe Room 101");
    }
}
