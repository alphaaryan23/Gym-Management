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
        @GetMapping("/ownerlogin")
      public String ownerlogin()
      {
          return "ownerLogin";
      }
       @GetMapping("/OwnerHome")
      public String OwnerHome()
      {
          return "OwnerHome";
      }
       @GetMapping("/oManageGym")
    public String oManageGym()
    {
        return "OwnerManageGym";
    }
}
