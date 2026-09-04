public class ParkingAllocationSystem {

    public static class ParkingSlot {
        private String slotNo;
        private int capacity;
        private int occupiedCount;

        public ParkingSlot(String slotNo, int capacity, int occupiedCount) {
            this.slotNo = slotNo;
            this.capacity = capacity;
            this.occupiedCount = occupiedCount;
        }

        public boolean allot(String vehicleNo) {
            if (occupiedCount < capacity) {
                occupiedCount++;
                return true;
            }
            return false;
        }

        public String getSlotNo() {
            return slotNo;
        }

        public int getCapacity() {
            return capacity;
        }

        public int getOccupiedCount() {
            return occupiedCount;
        }
    }

    public static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        if (slots == null) return null;
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null && slots[i].getOccupiedCount() < slots[i].getCapacity()) {
                return slots[i];
            }
        }
        return null;
    }

    public static void safeAllot(ParkingSlot[] slots, String vehicleNo) {
        ParkingSlot slot = findAvailableSlot(slots);
        if (slot != null) {
            slot.allot(vehicleNo);
            System.out.println(vehicleNo + " allotted to slot " + slot.getSlotNo());
        } else {
            System.out.println("No slots available for " + vehicleNo);
        }
    }

    public static void main(String[] args) {
        ParkingSlot slot1 = new ParkingSlot("A1", 4, 3);
        ParkingSlot slot2 = new ParkingSlot("A2", 5, 5);
        ParkingSlot[] slots = new ParkingSlot[] { slot1, slot2 };

        safeAllot(slots, "TN09AB1234");
        safeAllot(slots, "TN09AB1234");
    }
}