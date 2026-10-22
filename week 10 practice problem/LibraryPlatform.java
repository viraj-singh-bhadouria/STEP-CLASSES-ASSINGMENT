import java.util.*;

abstract class Resource {
    private String id;

    public Resource(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

interface Reservable {
    void reserve(String memberId);
}

interface Downloadable {
    void download();
}

class BookResource extends Resource implements Reservable {
    public BookResource(String id) {
        super(id);
    }

    @Override
    public void reserve(String memberId) {
        System.out.println(getId() + " reserved for " + memberId);
    }
}

class EBookResource extends Resource implements Downloadable {
    public EBookResource(String id) {
        super(id);
    }

    @Override
    public void download() {
        System.out.println(getId() + " downloaded");
    }
}

public class LibraryPlatform {
    private List<Resource> resources = new ArrayList<>();

    public void addResource(Resource resource) {
        for (Resource r : resources) {
            if (r.getId().equals(resource.getId())) {
                System.out.println("duplicate rejected");
                return;
            }
        }

        int index = 0;
        while (index < resources.size() && resources.get(index).getId().compareTo(resource.getId()) < 0) {
            index++;
        }
        resources.add(index, resource);
        System.out.println(resource.getId() + " added");
    }

    public void reserveResource(String id, String memberId) {
        Resource r = findResourceById(id);
        if (r instanceof Reservable) {
            ((Reservable) r).reserve(memberId);
        } else if (r != null) {
            System.out.println(id + " rejected: reserve unsupported");
        }
    }

    public void downloadResource(String id) {
        Resource r = findResourceById(id);
        if (r instanceof Downloadable) {
            ((Downloadable) r).download();
        } else if (r != null) {
            System.out.println(id + " rejected: download unsupported");
        }
    }

    public void findResource(String id) {
        int low = 0, high = resources.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = resources.get(mid).getId().compareTo(id);
            if (cmp == 0) {
                System.out.println(id + " found at index " + mid);
                return;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println(id + " not found");
    }

    private Resource findResourceById(String id) {
        for (Resource r : resources) {
            if (r.getId().equals(id)) return r;
        }
        return null;
    }

    public static void main(String[] args) {
        LibraryPlatform platform = new LibraryPlatform();

        platform.addResource(new BookResource("B1"));
        platform.addResource(new BookResource("B1"));
        platform.addResource(new EBookResource("E1"));

        platform.reserveResource("B1", "M1");
        platform.downloadResource("B1");
        platform.downloadResource("E1");

        platform.findResource("E1");
    }
}
