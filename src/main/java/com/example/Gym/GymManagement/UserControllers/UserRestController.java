/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Gym.GymManagement.UserControllers;

import com.example.Gym.GymManagement.Vmm.DbLoader;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author hp
 */
public class UserRestController {
//     @PostMapping("/userlogin")
//    public String userlogin(@RequestParam String email, @RequestParam String pass, HttpSession session) {
//        try {
//            System.out.println("useremail " + email);
//            System.out.println("userpassword " + pass);
//            ResultSet rs = DbLoader.executeQuery("Select * from  usersignup where useremail = '" + email + "' and userpassword = '" + pass + "' ");
//            if (rs.next()) {
//                session.setAttribute("id", rs.getInt("id"));
//                session.setAttribute("useremail", email);
//                return "success";
//            } else {
//                return "failed";
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return ex.toString();
//        }
//    }
}
