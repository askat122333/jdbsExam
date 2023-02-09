package org.example;

import java.sql.*;

public class Main {
   private static final String URL = "jdbc:postgresql://localhost:5432/air_travel";
   private static  final String USER = "postgres";
   private static final String PASSWORD = "postgres";
    public static void main(String[] args) {
        addCompany(7L,"asdaI");
        showPassenger(1L);
    }
    public static void showPassenger(Long id) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "select C.name as Company_name,plane,P.name as Passender_name,place,town_from,town_to from Trip\n" +
                    "join Company C on C.id = Trip.company\n" +
                    "join Pass_in_trip Pit on Trip.id = Pit.trip\n" +
                    "join Passenger P on P.id = Pit.passenger where P.id = ?;";
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String comName = resultSet.getString(1);
                String plane = resultSet.getString(2);
                String name = resultSet.getString(3);
                String place = resultSet.getString(4);
                String townFrom = resultSet.getString(5);
                String townTo = resultSet.getString(6);

                System.out.print(comName+" ,");
                System.out.print(plane +" ,");
                System.out.print(name +" ,");
                System.out.print(place+" ,");
                System.out.print(townFrom+" ,");
                System.out.println(townTo);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
    public static void addCompany(Long id,String name) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "INSERT into Company(id,name) values (?,?)";

            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.setString(2,name);
            int resultSet = preparedStatement.executeUpdate();
            System.out.println(resultSet);
            System.out.println("Company added");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}