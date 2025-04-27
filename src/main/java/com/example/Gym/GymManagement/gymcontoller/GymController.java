/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Gym.GymManagement.gymcontoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GymController {
    
      @GetMapping("/osignup")
    public String osignup()
    {
        return "OwnerSignUp";
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
