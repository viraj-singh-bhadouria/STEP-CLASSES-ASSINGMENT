public class BusTicket {
    private final String passengerName;
    private final String destination;
    private boolean checkedIn;

    public BusTicket(String passengerName, String destination) {
        if (!isValidString(passengerName) || !isValidString(destination)) {
            throw new IllegalArgumentException("Invalid passenger name or destination.");
        }
        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
        this.checkedIn = false;
    }

    private static boolean isValidString(String str) {
        if (str == null) {
            return false;
        }
        String trimmed = str.trim();
        if (trimmed.length() == 0) {
            return false;
        }
        for (int i = 0; i < trimmed.length(); i++) {
            char c = trimmed.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                return false;
            }
        }
        return true;
    }

    void markCheckedIn() {
        if (this.checkedIn) {
            throw new IllegalStateException("Already checked in.");
        }
        this.checkedIn = true;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getDestination() {
        return destination;
    }

    static void processBatch(String[][] rawBookings) {
        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        String[] acceptedKeys = new String[rawBookings != null ? rawBookings.length : 0];
        int acceptedCount = 0;

        if (rawBookings != null) {
            for (int i = 0; i < rawBookings.length; i++) {
                String[] booking = rawBookings[i];
                if (booking == null || booking.length < 2) {
                    rejected++;
                    continue;
                }

                try {
                    BusTicket ticket = new BusTicket(booking[0], booking[1]);
                    String key = ticket.getPassengerName().toLowerCase() + "|" + ticket.getDestination().toLowerCase();

                    boolean isDuplicate = false;
                    for (int j = 0; j < acceptedCount; j++) {
                        if (acceptedKeys[j].equals(key)) {
                            isDuplicate = true;
                            break;
                        }
                    }

                    if (isDuplicate) {
                        duplicates++;
                    } else {
                        acceptedKeys[acceptedCount++] = key;
                        valid++;
                    }
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected + " | Duplicates skipped: " + duplicates);
    }
    public static void main(String[] args) {
    String[][] rawBookings = {
        {"Divya", "Chennai"},
        {"", "Bangalore"},
        {"Ravi123", "Pune"},
        {"Divya", "Chennai"},
        {" ", " "}
    };

    processBatch(rawBookings);

    BusTicket ticket = new BusTicket("Divya", "Chennai");
    ticket.markCheckedIn();
}
}