package daos.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.RegionDAOInterface;
import models.Region;

public class RegionDAO implements RegionDAOInterface {
  private Connection connection;

  public RegionDAO(Connection connection) {
    this.connection = connection;
  }
  @Override
  //Get Data by id
  public Region get(int id) {
    String query = "SELECT * FROM regions WHERE region_id = ?";
    Region region = null;

    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, id);

      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        region = new Region(resultSet.getInt("region_id"), resultSet.getString("region_name"));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return region;
  }

  @Override
  //Get All Data
  public List<Region> get() {
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

  @Override
  //Insert Data
  public Integer insert(Region region){
    
    try{
      PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO regions (region_id, region_name) VALUES (?,?)");
      preparedStatement.setInt(1, region.getId());
      preparedStatement.setString(2, region.getName());
      int rowsCount = preparedStatement.executeUpdate();

      return rowsCount;
    } catch(SQLException e){
      System.out.println(e.getMessage());
    }

    return 0;
  }

  @Override
  //Update Data by Id
  public Integer update(Region region) {
    String query = "UPDATE regions SET region_name = ? WHERE region_id = ?";

    Region existedRegion = get(region.getId());
    if(existedRegion == null){
      return 0;
    }

    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, region.getName());
      preparedStatement.setInt(2, region.getId());
      int rowsCount = preparedStatement.executeUpdate();
      
      return rowsCount;
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return 0; 
  }

  @Override
  //Delete data by id
  public Integer delete(int id){
    String query = "DELETE FROM regions WHERE region_id = ?";

    Region existedRegion = get(id);
    if(existedRegion == null){
      return 0;
    }

    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, id);

      int rowsCount = preparedStatement.executeUpdate();
      return rowsCount;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return 0;
}
}