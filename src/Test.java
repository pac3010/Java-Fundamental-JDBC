import tools.DbConnection;
import daos.RegionDAO;
import models.Region;

public class Test {
    
        public static void main(String[] args) throws Exception {
            DbConnection connection = new DbConnection();
            System.out.println(connection.getConnection());
    
            RegionDAO rdao = new RegionDAO(connection.getConnection());
            // Update data
            // rdao.update("Jepang", 7);
            
            //Delete data
            rdao.delete(7);

            //get all data
            for(Region region : rdao.getAll()){
                System.out.println(region.getRegion_id());
                System.out.println(region.getRegion_name());
            }
            
        }
    }
