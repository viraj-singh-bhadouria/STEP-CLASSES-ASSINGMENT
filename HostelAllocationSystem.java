public class HostelAllocationSystem {

    public static class HostelRoom {
        private String roomNo;
        private int beds;
        private int occupied;

        public HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }

        public boolean allot(String name) {
            if (occupied < beds) {
                occupied++;
                return true;
            }
            return false;
        }

        public String getRoomNo() {
            return roomNo;
        }

        public int getBeds() {
            return beds;
        }

        public int getOccupied() {
            return occupied;
        }
    }

    public static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        if (rooms == null) return null;
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] != null && rooms[i].getOccupied() < rooms[i].getBeds()) {
                return rooms[i];
            }
        }
        return null;
    }

    public static void safeAllot(HostelRoom[] rooms, String studentName) {
        HostelRoom room = findAvailableRoom(rooms);
        if (room != null) {
            room.allot(studentName);
            System.out.println(studentName + " allotted to room " + room.getRoomNo());
        } else {
            System.out.println("No rooms available for " + studentName);
        }
    }

    public static void main(String[] args) {
        HostelRoom room1 = new HostelRoom("C-214", 3, 2);
        HostelRoom room2 = new HostelRoom("C-507", 2, 2);
        HostelRoom[] rooms = new HostelRoom[] { room1, room2 };

        safeAllot(rooms, "Divya");
        safeAllot(rooms, "Divya");
    }
}
