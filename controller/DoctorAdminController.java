/*
 * package com.hm.HospitalManagement.controller;
 * 
 * 
 * 
 * import com.hm.HospitalManagement.Entity.Doctor; import
 * com.hm.HospitalManagement.Service.DoctorService; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.*;
 * 
 * import java.util.List; import jakarta.servlet.http.HttpServletResponse;
 * import jakarta.servlet.http.HttpSession;
 * 
 * @Controller
 * 
 * @RequestMapping("/doctor") public class DoctorAdminController {
 * 
 * @Autowired private DoctorService doctorService;
 * 
 * @GetMapping("/list") public String listDoctors(HttpSession session, Model
 * model, HttpServletResponse response) { String adminName = (String)
 * session.getAttribute("adminName"); if (adminName == null) { return
 * "redirect:/admin/adminLogin"; }
 * 
 * List<Doctor> doctors = doctorService.getAllDoctors();
 * model.addAttribute("doctors", doctors);
 * 
 * // Prevent caching issues response.setHeader("Cache-Control",
 * "no-cache, no-store, must-revalidate"); response.setHeader("Pragma",
 * "no-cache"); response.setHeader("Expires", "0");
 * 
 * return "admin-dashboard"; }
 * 
 * @GetMapping("/add") public String showAddDoctorForm(Model model) {
 * model.addAttribute("doctor", new Doctor()); return "add-doctor"; }
 * 
 * @PostMapping("/add") public String addDoctor(@ModelAttribute Doctor doctor) {
 * doctorService.addDoctor(doctor); return "redirect:/doctor/list"; }
 * 
 * @GetMapping("/update/{id}") public String showUpdateDoctorForm(@PathVariable
 * Long id, Model model) { Doctor doctor = doctorService.getDoctorById(id);
 * model.addAttribute("doctor", doctor); return "update-doctor"; }
 * 
 * @PostMapping("/update") public String updateDoctor(@ModelAttribute Doctor
 * doctor) { doctorService.updateDoctor(doctor); return "redirect:/doctor/list";
 * }
 * 
 * @GetMapping("/delete/{id}") public String deleteDoctor(@PathVariable Long id)
 * { doctorService.deleteDoctor(id); return "redirect:/doctor/list"; } }
 * 
 * 
 * 
 */


package com.hm.HospitalManagement.controller;

import com.hm.HospitalManagement.entity.Doctor;
import com.hm.HospitalManagement.service.DoctorService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctor")
public class DoctorAdminController {

    @Autowired
    private DoctorService doctorService;
    


    @GetMapping("/list")
    public String listDoctors(Model model,HttpSession session) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        
        //Adding
        String adminName = (String) session.getAttribute("adminName");
        model.addAttribute("doctors", doctors);
        model.addAttribute("adminMessage", "Hi " + adminName);
        //clear caching
        
        return "admin-dashboard";
    }

    @PostMapping("/add")
    public String addDoctor(@ModelAttribute Doctor doctor) {
    	
        doctorService.addDoctor(doctor);
        return "redirect:/doctor/list";
    }

    @PostMapping("/update")
    public String updateDoctor(@ModelAttribute Doctor doctor) {
    	
        doctorService.updateDoctor(doctor);
        return "redirect:/doctor/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
    	
        doctorService.deleteDoctor(id);
        return "redirect:/doctor/list";
    }
    
    
    
    
    //Added
//    @GetMapping("/dashboard")
//    public String showDoctorDashboard(HttpSession session, Model model) {
//        String doctorEmail = (String) session.getAttribute("doctorEmail");
//
//       if (doctorEmail == null) {
//           return "redirect:/doctor/login";
//       }
//
//        model.addAttribute("doctorName", doctorService.getDoctorByEmail(doctorEmail).getName());
//       return "doctor-dashboard";
//    }
}
