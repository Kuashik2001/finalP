
  package com.hm.HospitalManagement.service;
  
  import com.hm.HospitalManagement.entity.Appointment;
import com.hm.HospitalManagement.entity.Doctor;
import com.hm.HospitalManagement.entity.Patient;
import com.hm.HospitalManagement.repository.AppointmentRepository;
import com.hm.HospitalManagement.repository.DoctorRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; 
  import org.springframework.stereotype.Service;
  
  import java.util.List; 
  import java.util.Optional;
import java.util.stream.Collectors;
  
  @Service 
  public class DoctorService {
  
  @Autowired 
  private DoctorRepository doctorRepository;
  @Autowired
  private AppointmentRepository appointmentRepo;
  
  private static final Logger log = LoggerFactory.getLogger(DoctorService.class);
  
  // Get all doctors
  public List<Doctor> getAllDoctors() { 
	  log.info("getting all doctors");   		// Log for Doctor Fetch Message
	  return doctorRepository.findAll(); }
  
  // Add a new doctor 
  public void addDoctor(Doctor doctor) {
  doctorRepository.save(doctor); }
  
  // Get a doctor by ID 
//  public Doctor getDoctorById(Long id) { Optional<Doctor>
//  optionalDoctor = doctorRepository.findById(id); return
//  optionalDoctor.orElse(null); }  							//old
  public Optional<Doctor> getDoctorById(Long id)
  {
	  return doctorRepository.findById(id);
  }
  
  
  public List<Patient> getPatientsByDoctorId(Long doctorId) {
      List<Appointment> appointments = appointmentRepo.findByDoctorId(doctorId);

      // Extract unique patients from appointments
      
      return appointments.stream()
              .map(Appointment::getPatient)
              .distinct()
              .collect(Collectors.toList());
  }
  
  
  
  
  // Update an existing doctor 
//  public void updateDoctor(Doctor doctor) {
//  doctorRepository.save(doctor); }  //old
  
  public void updateDoctor(Doctor doctor)
  {
	  if(doctorRepository.existsById(doctor.getId()))
	  {
		  doctorRepository.save(doctor);
	  }
  }
  
  // Delete a doctor 
  public void deleteDoctor(Long id) {
  doctorRepository.deleteById(id); } 
  
  //Added
  public Doctor getDoctorByEmail(String email)
  {
	  return doctorRepository.findByEmail(email);
  }
  
  }

  
