/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Gym.GymManagement.gymcontoller;

import com.example.Gym.GymManagement.Vmm.DbLoader;
import com.example.Gym.GymManagement.Vmm.RDBMS_TO_JSON;
import jakarta.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class GymRestController {

    @PostMapping("/ownersignup")
    public String ownersignup(@RequestParam String name, @RequestParam String pass, @RequestParam String email, @RequestParam String city, @RequestParam String franchise, @RequestParam MultipartFile photo) {

        try {

            ResultSet rs = DbLoader.executeQuery("SELECT * FROM ownersignup WHERE oemail = '" + email + "'");
            if (rs.next()) {
                return "fail";
            } else {

                String projectPath = System.getProperty("user.dir");
                String internalPath = "/src/main/resources/static";
                String folderName = "/myUploads";
                String orgName = "/" + photo.getOriginalFilename();

                rs.moveToInsertRow();

                rs.updateString("oname", name);
                rs.updateString("oemail", email);
                rs.updateString("opassword", pass);
                rs.updateString("ocity", city);
                rs.updateString("franchise", franchise);
                rs.updateString("ophoto", orgName);
                rs.updateString("ostatus", "Blocked");
                rs.insertRow();

                return "success";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "error: " + ex.getMessage();
        }
    }

    @PostMapping("/ownerlogin")
    public String ownerlogin(@RequestParam String email, @RequestParam String pass, HttpSession session) {
        try {
            System.out.println("oemail " + email);
            System.out.println("opassword " + pass);
            ResultSet rs = DbLoader.executeQuery("Select * from  ownersignup where oemail = '" + email + "' and opassword = '" + pass + "' ");
            if (rs.next()) {
                session.setAttribute("id", rs.getInt("id"));
              
                return "success";
            } else {
                return "failed";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.toString();
        }

    }

    @GetMapping("/getownerdetails")
    public String getownerdetails() {
        String ans = new RDBMS_TO_JSON().generateJSON("select * from ownersignup");
        return ans;
    }

    @GetMapping("/ApprovedButton")
    public String approvedButton(@RequestParam String accept) {
        int id = Integer.parseInt(accept);
        try {
            ResultSet rs = DbLoader.executeQuery("UPDATE ownersignup SET ostatus = 'Approved' WHERE id = " + id);
            if (rs.next()) {
                return "Approved";
            } else {
                return "Owner not found or update failed";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.toString();
        }
    }

    @GetMapping("/BlockedButton")
    public String blockedButton(@RequestParam String block) {
        int id = Integer.parseInt(block);
        try {
            ResultSet rs = DbLoader.executeQuery("UPDATE ownersignup SET ostatus = 'Blocked' WHERE id = " + id);
            if (rs.next()) {
                return "Blocked";
            } else {
                return "Owner not found or update failed";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.toString();
        }
    }

    @PostMapping("/ownermanagegym")
    public String ownermanagegym(HttpSession session,
             @RequestParam String gymname,
             @RequestParam String address,
             @RequestParam String latitude,
             @RequestParam String longitude,
             @RequestParam String ameneties,
              @RequestParam String description,
             @RequestParam String myDropdown,
             @RequestParam MultipartFile photo) {

        try {
            ResultSet rs = DbLoader.executeQuery("select * from ownergym where gymname='" + gymname + "'");
            if (rs.next()) {
                return "fail";
            } else {
                String projectPath = System.getProperty("user.dir");
                String internalPath = "/src/main/resources/static";
                String folderName = "/myUploads";
                String orgName = "/" + photo.getOriginalFilename();
                FileOutputStream fos = new FileOutputStream(projectPath + internalPath + folderName + orgName);

                byte[] b1 = photo.getBytes();

                fos.write(b1);
                fos.close();
                rs.moveToInsertRow();
                rs.updateString("gymname", gymname);
                rs.updateString("Address", address);
                rs.updateString("ogcity", myDropdown);
                rs.updateString("latitude", latitude);
                rs.updateString("longitude", longitude);
                rs.updateString("Ameneties", ameneties);
                rs.updateString("Ameneties", description);
                rs.updateString("ogphoto", orgName);

                Integer s = (Integer) session.getAttribute("id");
                System.out.println(s);
                rs.updateInt("ownerId", s);
                rs.insertRow();
                return "Added Successfully";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.toString();
        }

    }

    @GetMapping("/getgymdetails")
    public String getgymdetails() {
        String ans = new RDBMS_TO_JSON().generateJSON("select * from ownergym");
        return ans;
    }

    @PostMapping("/deleteGym")
    public String deleteGym(@RequestParam String id) {
        try {
            int myid = Integer.parseInt(id);

            ResultSet rs = DbLoader.executeQuery("SELECT * FROM ownergym WHERE id = " + myid);
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

    @PostMapping("/packagetable")
    public String packagetable(HttpSession session,
             @RequestParam String pname,
             @RequestParam String pprice,
             @RequestParam String offerprice,
             @RequestParam String duration,
             @RequestParam String inclusion,
             @RequestParam int gid) {

        try {
            ResultSet rs = DbLoader.executeQuery("select * from packagetable where pname='" + pname + "'");
            
            if (rs.next()) {
                return "fail";
            } else {
                rs.moveToInsertRow();
                rs.updateString("pname", pname);
                rs.updateString("pprice", pprice);
                rs.updateString("offerprice", offerprice);
                rs.updateString("duration", duration);
                rs.updateString("inclusion", inclusion);
                rs.updateInt("gid", gid);

                rs.insertRow();
                return "Added Successfully";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.toString();
        }
    }

    @GetMapping("/getpackagedetails")
    public String getpackagedetails(@RequestParam String id) {
        String ans = new RDBMS_TO_JSON().generateJSON("select * from packagetable where gid='"+id+"'");
        return ans;
    }
    
    
    
    @PostMapping("/deletePackage")
    public String deletePackage(@RequestParam String id) {
        try {
            int myid = Integer.parseInt(id);

            ResultSet rs = DbLoader.executeQuery("SELECT * FROM packagetable WHERE id = " + myid);
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
    
    
            
    @PostMapping("/getEditData")
    public String getEditData(@RequestParam int pid) {
        System.out.println(pid);
        String ans = new RDBMS_TO_JSON().generateJSON("select * from packagetable where id ="+pid+" ");
        return ans;
    }
         
    
     @PostMapping("/updatetable")
    public String updatetable(
             @RequestParam String pname,
             @RequestParam String pprice,
             @RequestParam String offerprice,
             @RequestParam String duration,
             @RequestParam String inclusion,
             @RequestParam String pid) {

        try {
            ResultSet rs = DbLoader.executeQuery("select * from packagetable where id='" + pid + "'");
            if (rs.next()) {
                 rs.moveToCurrentRow();
                rs.updateString("pname", pname);
                rs.updateString("pprice", pprice);
                rs.updateString("offerprice", offerprice);
                rs.updateString("duration", duration);
                rs.updateString("inclusion", inclusion);
               

                rs.updateRow();
                return "Updated Successfully";
            } else {
               return "fail";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.toString();
        }
    }
    
     @PostMapping("/getEditOwnerData")
    public String getEditOwnerData(HttpSession session) {
        Integer oid=(Integer) session.getAttribute("id");
        String ans = new RDBMS_TO_JSON().generateJSON("select * from ownersignup where id ="+oid+" ");
        return ans;
    }
         
    
     @PostMapping("/updatedetails")
    public String updatedetails(
            
             @RequestParam String name,
             @RequestParam String city,
             @RequestParam String franchise,
             
             HttpSession session) {

        try {
            Integer oid=(Integer) session.getAttribute("id");
            ResultSet rs = DbLoader.executeQuery("select * from ownersignup where id='" + oid + "'");
            if (rs.next()) {
                
                rs.moveToCurrentRow();
                rs.updateString("oname", name);
                rs.updateString("ocity", city);
                rs.updateString("franchise", franchise);
//                rs.updateString("ophoto" , photo);
              rs.updateRow();
                return "Updated Successfully";
            } else {
               return "fail";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.toString();
        }
    }
      @PostMapping("/showgympack")
    public String showgympack(@RequestParam String sgpid) {
        String ans = new RDBMS_TO_JSON().generateJSON("select * from packagetable where id='"+sgpid+"'");
        return ans;
    }
    
     @PostMapping("/bookingpayment")
    public String bookingpayment(HttpSession session , @RequestParam int pid , @RequestParam String sid, @RequestParam String eid , @RequestParam String ppid) {
        try{
            String useremail = (String) session.getAttribute("useremail");
            int gid = 0;
            int ownerid = 0;
            String ownerEmail = "";
            String pname = "";
            ResultSet rs = DbLoader.executeQuery("select * from packagetable where id = "+pid+" ");
            if(rs.next()){
               gid = rs.getInt("gid");
               pname = rs.getString("pname");
            }
            
            ResultSet rs2 = DbLoader.executeQuery("select * from ownergym where id ="+gid+" ");
            if(rs2.next()){
               ownerid = rs2.getInt("ownerid");
            }
            
            ResultSet rs3 = DbLoader.executeQuery("select * from ownersignup where id ="+ownerid+" ");
            if(rs3.next()){
                ownerEmail = rs3.getString("oemail");
            }
            
            ResultSet rs4 = DbLoader.executeQuery("select * from paymenttable ");
            
                rs4.moveToInsertRow();
                rs4.updateInt("packageid", pid);
                rs4.updateString("startdate", sid);
                rs4.updateString("enddate", eid);
                rs4.updateString("price", ppid);
                rs4.updateString("useremail", useremail);
                rs4.updateString("owneremail", ownerEmail);
                rs4.updateString("packagename", pname);
                rs4.updateString("paymenttype", "online"); 
                rs4.updateString("address", "N/A");
                rs4.insertRow();
           return "success";
            
        }catch(Exception ex){
        ex.printStackTrace();
            return ex.toString();
        }

    }
}
