import java.util.*;

interface Capability_Q3 {
    String getName();
    boolean applyValue(Object value);
}

class PowerCapability_Q3 implements Capability_Q3 {
    private boolean state = false;

    @Override
    public String getName() { return "Power"; }

    @Override
    public boolean applyValue(Object value) {
        if (value instanceof String) {
            String val = ((String) value).toUpperCase();
            if ("ON".equals(val) || "OFF".equals(val)) {
                this.state = "ON".equals(val);
                return true;
            }
        }
        return false;
    }

    public boolean getState() { return state; }
}

class BrightnessCapability_Q3 implements Capability_Q3 {
    private int brightness = 0;

    @Override
    public String getName() { return "Brightness"; }

    @Override
    public boolean applyValue(Object value) {
        if (value instanceof Integer) {
            int val = (Integer) value;
            if (val >= 0 && val <= 100) {
                this.brightness = val;
                return true;
            }
        }
        return false;
    }
}

class TemperatureCapability_Q3 implements Capability_Q3 {
    private int temperature = 20;

    @Override
    public String getName() { return "Temperature"; }

    @Override
    public boolean applyValue(Object value) {
        if (value instanceof Integer) {
            int val = (Integer) value;
            if (val >= 16 && val <= 30) {
                this.temperature = val;
                return true;
            }
        }
        return false;
    }
}

class Device_Q3 {
    private String name;
    private Map<String, Capability_Q3> capabilities = new LinkedHashMap<>();

    public Device_Q3(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void addCapability(Capability_Q3 capability) {
        capabilities.put(capability.getName(), capability);
    }

    public boolean hasCapability(String capabilityName) {
        return capabilities.containsKey(capabilityName);
    }

    public boolean executeCapability(String capabilityName, Object value) {
        Capability_Q3 cap = capabilities.get(capabilityName);
        if (cap != null) {
            boolean success = cap.applyValue(value);
            if (!success && "Temperature".equals(capabilityName)) {
                System.out.printf("Rejected: %s temperature must be between 16°C and 30°C.%n", name);
            }
            return success;
        }
        return false;
    }
}

class SceneStep_Q3 {
    private String capabilityName;
    private Object targetValue;

    public SceneStep_Q3(String capabilityName, Object targetValue) {
        this.capabilityName = capabilityName;
        this.targetValue = targetValue;
    }

    public String getCapabilityName() { return capabilityName; }
    public Object getTargetValue() { return targetValue; }
}

class Scene_Q3 {
    private String name;
    private List<SceneStep_Q3> steps = new ArrayList<>();

    public Scene_Q3(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void addStep(String capabilityName, Object targetValue) {
        steps.add(new SceneStep_Q3(capabilityName, targetValue));
    }

    public void execute(List<Device_Q3> devices) {
        System.out.printf("Scene '%s' started.%n", name);
        int totalActions = 0;

        for (SceneStep_Q3 step : steps) {
            for (Device_Q3 device : devices) {
                if (device.hasCapability(step.getCapabilityName())) {
                    boolean applied = device.executeCapability(step.getCapabilityName(), step.getTargetValue());
                    if (applied) {
                        totalActions++;
                        if ("Power".equals(step.getCapabilityName())) {
                            System.out.printf("%s: %s.%n", device.getName(), step.getTargetValue());
                        } else if ("Brightness".equals(step.getCapabilityName())) {
                            System.out.printf("%s: brightness set to %s%%.%n", device.getName(), step.getTargetValue());
                        } else if ("Temperature".equals(step.getCapabilityName())) {
                            System.out.printf("%s: temperature set to %s°C.%n", device.getName(), step.getTargetValue());
                        }
                    }
                }
            }
        }
        System.out.printf("Scene '%s' completed: %d actions applied.%n", name, totalActions);
    }
}

public class SmartLabControlPanel_Q3 {
    public static void main(String[] args) {
        Device_Q3 ac = new Device_Q3("Lab AC");
        ac.addCapability(new PowerCapability_Q3());
        ac.addCapability(new TemperatureCapability_Q3());

        Device_Q3 lights = new Device_Q3("Ceiling Lights");
        lights.addCapability(new PowerCapability_Q3());
        lights.addCapability(new BrightnessCapability_Q3());

        Device_Q3 projector = new Device_Q3("Projector");
        projector.addCapability(new PowerCapability_Q3());

        List<Device_Q3> devices = Arrays.asList(ac, lights, projector);

        Scene_Q3 lectureMode = new Scene_Q3("Lecture Mode");
        lectureMode.addStep("Power", "ON");
        lectureMode.addStep("Brightness", 40);
        lectureMode.addStep("Temperature", 24);

        lectureMode.execute(devices);

        ac.executeCapability("Temperature", 12);

        projector.addCapability(new BrightnessCapability_Q3());
        System.out.println("Projector: Brightness capability added.");
        projector.executeCapability("Brightness", 70);
        System.out.println("Projector: brightness set to 70%.");
    }
}
