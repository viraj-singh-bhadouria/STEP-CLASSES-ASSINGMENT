import java.util.*;

abstract class SmartDevice {
    private String id;
    private boolean isOn = false;

    public SmartDevice(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void turnOn() {
        this.isOn = true;
        System.out.println(id + " is ON");
    }

    public void turnOff() {
        this.isOn = false;
        System.out.println(id + " is OFF");
    }
}

interface Dimmable {
    void dim(int level);
}

interface Schedulable {
    void schedule(String time);
}

interface EnergyMonitorable {
    void getEnergy();
}

class Light extends SmartDevice implements Dimmable, Schedulable {
    public Light(String id) {
        super(id);
    }

    @Override
    public void dim(int level) {
        System.out.println(getId() + " dimmed to " + level);
    }

    @Override
    public void schedule(String time) {
        System.out.println(getId() + " scheduled " + time);
    }
}

class Fan extends SmartDevice implements Schedulable {
    public Fan(String id) {
        super(id);
    }

    @Override
    public void schedule(String time) {
        System.out.println(getId() + " scheduled " + time);
    }
}

class Plug extends SmartDevice implements EnergyMonitorable {
    private int energyKWh;

    public Plug(String id, int energyKWh) {
        super(id);
        this.energyKWh = energyKWh;
    }

    @Override
    public void getEnergy() {
        System.out.println(getId() + " energy " + energyKWh + " kWh");
    }
}

public class SmartHomePlatform {
    private Map<String, SmartDevice> devices = new HashMap<>();

    public void addDevice(SmartDevice device) {
        devices.put(device.getId(), device);
    }

    public void processCommand(String command) {
        String[] parts = command.split(" ");
        String action = parts[0];
        String deviceId = parts[1];

        SmartDevice device = devices.get(deviceId);
        if (device == null) return;

        switch (action) {
            case "ON":
                device.turnOn();
                break;
            case "OFF":
                device.turnOff();
                break;
            case "DIM":
                if (device instanceof Dimmable) {
                    ((Dimmable) device).dim(Integer.parseInt(parts[2]));
                } else {
                    System.out.println(deviceId + " rejected: DIM unsupported");
                }
                break;
            case "SCHEDULE":
                if (device instanceof Schedulable) {
                    ((Schedulable) device).schedule(parts[2]);
                } else {
                    System.out.println(deviceId + " rejected: SCHEDULE unsupported");
                }
                break;
            case "ENERGY":
                if (device instanceof EnergyMonitorable) {
                    ((EnergyMonitorable) device).getEnergy();
                } else {
                    System.out.println(deviceId + " rejected: ENERGY unsupported");
                }
                break;
        }
    }

    public static void main(String[] args) {
        SmartHomePlatform platform = new SmartHomePlatform();
        platform.addDevice(new Light("L1"));
        platform.addDevice(new Fan("F1"));
        platform.addDevice(new Plug("P1", 12));

        platform.processCommand("ON L1");
        platform.processCommand("DIM L1 40");
        platform.processCommand("DIM F1 30");
        platform.processCommand("SCHEDULE F1 22:00");
        platform.processCommand("ENERGY P1");
        platform.processCommand("ENERGY L1");
    }
}