/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Gym.GymManagement.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/alogin")
    public String alogin() {
        return "AdminLogin";
    }

    @GetMapping("/ahome")
    public String ahome(HttpSession session) {
        String aname = (String) session.getAttribute("adminname");
        if(aname == null){
            return "redirect:/alogin";
        }else{
        return "AdminHome";
        }
    }

    @GetMapping("/AdminManageCities")
    public String aManageCities(HttpSession session) {
        String aname = (String) session.getAttribute("adminname");
        if(aname == null){
            return "redirect:/alogin";
        }else{
        return "AdminManageCities";
        }
    }

    @GetMapping("/amanageowner")
    public String amanageowner(HttpSession session) {
        String aname = (String) session.getAttribute("adminname");
        if(aname == null){
            return "redirect:/alogin";
        }else{
        return "AdminManageOwner";
        }
    }

    @GetMapping("/changeadminpassword")
    public String changeadminpassword(HttpSession session) {
        String aname = (String) session.getAttribute("adminname");
        if(aname == null){
            return "redirect:/alogin";
        }else{
        return "ChangeAdminPassword";
        }
    }

    @GetMapping("/userlogin")
    public String userlogin() {
        return "UserLogin";
    }

    @GetMapping("/usersignup")
    public String usersignup() {
        return "UserSignup";
    }

    @GetMapping("/uhome")
    public String uhome(HttpSession session) {
        String uemail = (String) session.getAttribute("useremail");
        if (uemail == null) {
            return "redirect:/userlogin";
        } else {
            return "UserHome";
        }
    }

    @GetMapping("/morecities")
    public String morecities(HttpSession session) {
        String uemail = (String) session.getAttribute("useremail");
        if (uemail == null) {
            return "redirect:/userlogin";
        } else {
            return "MoreCities";
        }
    }

    @GetMapping("/choosecitiesgym")
    public String choosecitiesgym(HttpSession session) {
        String uemail = (String) session.getAttribute("useremail");
        if (uemail == null) {
            return "redirect:/userlogin";
        } else {
            return "ChooseCitiesGym";
        }

    }

    @GetMapping("/usergymdetail")
    public String usergymdetail(HttpSession session) {
        String uemail = (String) session.getAttribute("useremail");
        if (uemail == null) {
            return "redirect:/userlogin";
        } else {
            return "UserGymDetail";
        }
    }

    @GetMapping("/payment")
    public String payment(HttpSession session) {

        String uemail = (String) session.getAttribute("useremail");
        if (uemail == null) {
            return "redirect:/userlogin";
        } else {
            return "Payment";
        }
    }

    @GetMapping("/userbookinghistory")
    public String userbookinghistory(HttpSession session) {
        String uemail = (String) session.getAttribute("useremail");
        if (uemail == null) {
            return "redirect:/userlogin";
        } else {
            return "UserBookingHistory";
        }
    }

    @GetMapping("/usershowgympackage")
    public String usershowgympackage(HttpSession session) {
        String uemail = (String) session.getAttribute("useremail");
        if (uemail == null) {
            return "redirect:/userlogin";
        } else {
            return "/UserShowGymPackage";
        }
    }

    @GetMapping("/changeuserpassword")
    public String changeuserpassword(HttpSession session) {
        String uemail = (String) session.getAttribute("useremail");
        if (uemail == null) {
            return "redirect:/userlogin";
        } else {
            return "ChangeUserPassword";
        }
    }

}
