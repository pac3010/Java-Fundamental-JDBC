package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Region;

public class RegionDAO {
  
  private Connection connection;

  public RegionDAO(Connection connection) {
    this.connection = connection;
  }
  //Get data by id
  public Region getById(int region_id) {
    String query = "SELECT * FROM regions WHERE region_id = ?";
    Region region = null;

    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, region_id);

      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        region = new Region(resultSet.getInt("region_id"), resultSet.getString("region_name"));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return region;
  }

  //Get All Data
  public List<Region> getAll() {
    List<Region> regions = new ArrayList<>();
    String query = "SELECT * FROM regions";

    try {
      ResultSet resultSet = connection.prepareStatement(query).executeQuery();

      while (resultSet.next()) {
        Region region = new Region(resultSet.getInt("region_id"), resultSet.getString("region_name"));
        regions.add(region);
      }
    } catch (SQLException e) {
        e.printStackTrace();
    }
      return regions;
    }

  //Insert Data
  public boolean insert(Region region){
    
    try{
      PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO regions (region_id, region_name) VALUES (?,?)");
      preparedStatement.setInt(1, region.getRegion_id());
      preparedStatement.setString(2, region.getRegion_name());
      int countVar = preparedStatement.executeUpdate();
      System.out.println(countVar);

      return true;
    } catch(SQLException e){
      System.out.println(e.getMessage());
    }

    return false;
  }

  //Update Data by Id
  public void update(String new_region_name, int region_id) {
    String query = "UPDATE regions SET region_name = ? WHERE region_id = ?";

    Region existedRegion = getById(region_id);
    if(existedRegion == null){
      System.out.println("No data found");
    }

    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, new_region_name);
      preparedStatement.setInt(2, region_id);
      int rowsCount = preparedStatement.executeUpdate();
      if(rowsCount > 1){
        System.out.println("1 row has been updated");
      }else{
        System.out.format("%d rows has been updated", rowsCount);
      }
      
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    } 
  }

  //Delete data by id
  public void delete(int region_id){
    String query = "DELETE FROM regions WHERE region_id = ?";
  

  try {
    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, region_id);

    int rowsCount = preparedStatement.executeUpdate();
    if(rowsCount>1){
      System.out.println("1 data has been deleted");
    }else{
      System.out.format("%d data has been Deleted\n", rowsCount);
    }
  } catch (SQLException e) {
    System.out.println(e.getMessage());
  }
}
}