package com.example.Gym.GymManagement.Vmm;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


 
 import java.sql.*;
public class DbLoader {
    public static ResultSet executeQuery(String stm){
        
         try{
            // load jdbc driver
         Class.forName("com.mysql.cj.jdbc.Driver");
         System.out.println("Driver loaded successfully"); 
         
         //create connection to the mysql databases
         Connection conn = DriverManager.getConnection( "jdbc:mysql://127.0.01:3306/vmm" , "root" , "9501425569");
         
         Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
         System.out.println("Statement Created");
         
         ResultSet rs  = s.executeQuery(stm);
         return rs;
         }
        catch(Exception ex ){
            ex.printStackTrace();
        }
         return null;
    }

    public static void executeUpdate(String query) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
