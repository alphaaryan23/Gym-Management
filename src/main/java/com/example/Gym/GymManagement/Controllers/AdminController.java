/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Gym.GymManagement.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/alogin")
    public String alogin()
    {
        return "AdminLogin";
    }
    
      @GetMapping("/ahome")
    public String ahome()
    {
        return "AdminHome";
    }
      @GetMapping("/AdminManageCities")
    public String aManageCities()
    {
        return "AdminManageCities";
    }
      @GetMapping("/amanageowner")
      public String amanageowner()
      {
          return "AdminManageOwner";
      }

      
       @GetMapping("/userlogin")
      public String userlogin()
      {
          return "UserLogin";
      }
        @GetMapping("/usersignup")
      public String usersignup()
      {
          return "UserSignup";
      }
     
         @GetMapping("/uhome")
    public String uhome()
    {
        return "UserHome";
    }
     @GetMapping("/morecities")
      public String morecities()
     {
          return "MoreCities";
      }
      
      @GetMapping("/choosecitiesgym")
      public String choosecitiesgym()
     {
          return "ChooseCitiesGym";
      }
       @GetMapping("/usergymdetail")
      public String usergymdetail()
     {
          return "UserGymDetail";
      }
        @GetMapping("/payment")
      public String payment()
     {
          return "Payment";
      }
       @GetMapping("/userbookinghistory")
      public String userbookinghistory()
     {
          return "UserBookingHistory";
      }
}
