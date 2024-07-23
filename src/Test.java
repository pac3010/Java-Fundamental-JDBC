import tools.DbConnection;
import daos.RegionDAOInterface;
import daos.implementation.RegionDAO;
import models.Region;

public class Test {
    
        public static void main(String[] args) throws Exception {
            DbConnection connection = new DbConnection();
            System.out.println(connection.getConnection());

            RegionDAOInterface rdao = new RegionDAO(connection.getConnection());
            
            //Delete data
            // rdao.delete(7);
            //get all data
            for(Region region : rdao.get()){
                System.out.println(region.getId());
                System.out.println(region.getName());
            }
            // Region newRegion = new Region(6, "Madagascar");
            // rdao.update(newRegion);
        }
    }
