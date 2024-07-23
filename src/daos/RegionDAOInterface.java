package daos;
import models.Region;
import java.util.*;

public interface RegionDAOInterface {
    public Region get(int id);
    public List<Region> get();
    public Integer insert(Region region);
    public Integer update(Region region);
    public Integer delete(int id);


}
