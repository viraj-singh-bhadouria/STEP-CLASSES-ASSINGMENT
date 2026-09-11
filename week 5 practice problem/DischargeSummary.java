class CriticalCareDischargeSummary extends DischargeSummary {
    private final int icuDays;

    public CriticalCareDischargeSummary(String patientId, String[] medicationCodes, int icuDays) {
        super(patientId, medicationCodes);
        this.icuDays = icuDays;
    }
    
    public int getIcuDays() {
        return this.icuDays;
    }
}
public class DischargeSummary {
    private final String patientId;
    private final String[] medicationCodes;

    static {
    }

    public DischargeSummary(String patientId, String[] medicationCodes) {
        this.patientId = patientId;
        
        if (medicationCodes == null) {
            throw new IllegalArgumentException("construction rejected");
        }
        
        String[] copiedCodes = new String[medicationCodes.length];
        for (int i = 0; i < medicationCodes.length; i++) {
            if (medicationCodes[i] == null || !medicationCodes[i].matches("MED-[A-Z]")) {
                throw new IllegalArgumentException("construction rejected");
            }
            copiedCodes[i] = medicationCodes[i];
        }
        this.medicationCodes = copiedCodes;
    }

    public String[] getMedicationCodes() {
        String[] copiedCodes = new String[this.medicationCodes.length];
        System.arraycopy(this.medicationCodes, 0, copiedCodes, 0, this.medicationCodes.length);
        return copiedCodes;
    }

    public DischargeSummary withCorrectedMedication(int index, String newCode) {
        String[] newCodes = this.getMedicationCodes();
        newCodes[index] = newCode;
        if (this instanceof CriticalCareDischargeSummary) {
            return new CriticalCareDischargeSummary(this.patientId, newCodes, ((CriticalCareDischargeSummary)this).getIcuDays());
        }
        return new DischargeSummary(this.patientId, newCodes);
    }

    public static String processNightlyBatch(DischargeSummary[] summaries) {
        if (summaries == null) return "";
        
        int processed = 0, nullSkipped = 0, criticalCare = 0, routine = 0;

        for (DischargeSummary summary : summaries) {
            if (summary == null) {
                nullSkipped++;
            } else {
                processed++;
                if (summary instanceof CriticalCareDischargeSummary) {
                    criticalCare++;
                } else {
                    routine++;
                }
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | " + criticalCare + " critical-care | " + routine + " routine";
    }

    public static void main(String[] args) {
        try {
            new DischargeSummary("MT2026-0142", new String[]{"MED-A", "bad"});
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        DischargeSummary d = new DischargeSummary("MT2026-0142", new String[]{"MED-A", "MED-B"});
        String[] codes = d.getMedicationCodes();
        codes[0] = "TAMPERED";
        System.out.println(d.getMedicationCodes()[0]);

        DischargeSummary[] summaries = {
            new CriticalCareDischargeSummary("MT001", new String[]{"MED-X"}, 4),
            null,
            new DischargeSummary("MT002", new String[]{"MED-Y"})
        };
        System.out.println(processNightlyBatch(summaries));
    }
}


