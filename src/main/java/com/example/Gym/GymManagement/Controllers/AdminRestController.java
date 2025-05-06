/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Gym.GymManagement.Controllers;

import com.example.Gym.GymManagement.Vmm.DbLoader;
import com.example.Gym.GymManagement.Vmm.RDBMS_TO_JSON;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileOutputStream;
import java.sql.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AdminRestController {

    @PostMapping("/adminLogin")
    public String adminlogin(@RequestParam String name, @RequestParam String pass) {
        try {
            System.out.println("Name " + name);
            System.out.println("Pass " + pass);
            ResultSet rs = DbLoader.executeQuery("Select * from  admintable where name = '" + name + "' and password = '" + pass + "' ");
            if (rs.next()) {
                return "success";
            } else {
                return "failed";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.toString();
        }
    }

    @PostMapping("/adminmanagecity")
    public String adminmanagecity(@RequestParam String cname, @RequestParam MultipartFile cphoto, @RequestParam String cdesc) {
        String projectPath = System.getProperty("user.dir");
        String internalPath = "/src/main/resources/static";
        String folderName = "/myUploads";
        String orgName = "/" + cphoto.getOriginalFilename();
        try {
            ResultSet rs = DbLoader.executeQuery("select * from adminmanagecity where cname='" + cname + "'");
            if (rs.next()) {
                return "Failed";
            } else {
                FileOutputStream fos = new FileOutputStream(projectPath + internalPath + folderName + orgName);

                byte[] b1 = cphoto.getBytes();

                fos.write(b1);
                fos.close();
                rs.moveToInsertRow();
                rs.updateString("cname", cname);
                rs.updateString("cdesc", cdesc);
                rs.updateString("cphoto", orgName);
                rs.insertRow();
                return "Added Successfully";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.toString();
        }

    }

    @GetMapping("/getcities")
    public String getcities() {
        String ans = new RDBMS_TO_JSON().generateJSON("select * from adminmanagecity");
        return ans;
    }

    @PostMapping("/deletecity")
    public String deleteCity(@RequestParam String id) {
        try {
            int myid = Integer.parseInt(id);

            ResultSet rs = DbLoader.executeQuery("SELECT * FROM adminmanagecity WHERE id = " + myid);
            if (rs.next()) {
                rs.deleteRow();
                return "success";
            } else {
                return "failure";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.toString();
        }
    }
  
    @PostMapping("/usersignup")
    public String usersignup(@RequestParam String name, @RequestParam String pass, @RequestParam String email, @RequestParam String phoneno, @RequestParam String address, @RequestParam MultipartFile photo) {

        try {

            ResultSet rs = DbLoader.executeQuery("SELECT * FROM usersignup WHERE useremail = '" + email + "'");
            if (rs.next()) {
                return "fail";
            } else {

                String projectPath = System.getProperty("user.dir");
                String internalPath = "/src/main/resources/static";
                String folderName = "/myUploads";
                String orgName = "/" + photo.getOriginalFilename();

                rs.moveToInsertRow();

                rs.updateString("username", name);
                rs.updateString("useremail", email);
                rs.updateString("userpassword", pass);
                rs.updateString("phoneno", phoneno);
                rs.updateString("address", address);
                rs.updateString("photo", orgName);
                
                rs.insertRow();

                return "success";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "error: " + ex.getMessage();
        }
    }
   @PostMapping("/userlogin")
    public String userlogin(@RequestParam String email, @RequestParam String pass) {
        try {
            System.out.println("useremail " + email);
            System.out.println("userpassword " + pass);
            ResultSet rs = DbLoader.executeQuery("Select * from  usersignup where useremail = '" + email + "' and userpassword = '" + pass + "' ");
            if (rs.next()) {
//                session.setAttribute("id", rs.getInt("id"));
                return "success";
            } else {
                return "failed";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.toString();
        }

    }
  }



    
