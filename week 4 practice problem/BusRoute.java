public class BusRoute {
    private final String routeCode;
    private final String routeName;
    private final int priority;

    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 1);
    }

    public String getRouteCode() {
        return this.routeCode;
    }

    public String getRouteName() {
        return this.routeName;
    }

    public int getPriority() {
        return this.priority;
    }

    int compareTo(BusRoute other) {
        if (this.priority != other.priority) {
            return other.priority - this.priority;
        }
        int codeComparison = this.routeCode.compareToIgnoreCase(other.routeCode);
        if (codeComparison != 0) {
            return codeComparison;
        }
        int exactCodeComparison = this.routeCode.compareTo(other.routeCode);
        if (exactCodeComparison != 0) {
            return exactCodeComparison;
        }
        return this.routeName.compareTo(other.routeName);
    }

    static BusRoute[] rankRoutes(BusRoute[] routes) {
        if (routes == null) {
            return null;
        }
        BusRoute[] sorted = routes.clone();
        for (int i = 1; i < sorted.length; i++) {
            BusRoute current = sorted[i];
            int j = i - 1;
            while (j >= 0 && sorted[j].compareTo(current) > 0) {
                sorted[j + 1] = sorted[j];
                j--;
            }
            sorted[j + 1] = current;
        }
        return sorted;
    }

    @Override
    public String toString() {
        return "\"" + this.routeCode + "\"";
    }
    public static void main(String[] args) {
        BusRoute[] routes = {
            new BusRoute("RT205L", "Airport Express", 3),
            new BusRoute("rt201j", "City Central", 4),
            new BusRoute("RT299T", "Night Service")
        };

        BusRoute[] ranked = rankRoutes(routes);

        System.out.print("[");
        for (int i = 0; i < ranked.length; i++) {
            System.out.print(ranked[i] + (i < ranked.length - 1 ? ", " : ""));
        }
        System.out.println("]");
    }
}
