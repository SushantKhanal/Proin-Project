package com.spring.controller;

        import com.spring.model.Admin;
        import com.spring.requestDTO.AdminDto;
        import com.spring.requestDTO.EmailContent;
        import com.spring.scheduler.EmailSenderScheduler;
        import com.spring.services.AdminSignUpService;
        import com.spring.utils.WebResourceConstant;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WebResourceConstant.API_BASE +
        WebResourceConstant.SignUpCtrl.SIGNUP_BASE)
public class AdminSignUpController {

    private final AdminSignUpService adminSignUpService;
    private final EmailSenderScheduler emailSenderScheduler;

    public AdminSignUpController(AdminSignUpService adminSignUpService, EmailSenderScheduler emailSenderScheduler) {
        this.adminSignUpService = adminSignUpService;
        this.emailSenderScheduler = emailSenderScheduler;
    }

    @PostMapping(WebResourceConstant.SignUpCtrl.CREATE_ADMIN)
    public ResponseEntity<Void> adminSignUp(@RequestBody AdminDto adminDto) {
        //save this with status 0;
        Admin admin = new Admin(adminDto.getId(), adminDto.getFirstName(), adminDto.getLastName(), adminDto.getDob(),
               adminDto.getBio(), adminDto.getNation(), adminDto.getUsername(), adminDto.getPassword(),
                adminDto.getAddress(), adminDto.getEmail(), adminDto.getAgenda(), adminDto.getJoinDate(),
                adminDto.getAcademics(), adminDto.getExperience(), 0L);
        adminSignUpService.createAdmin(admin);
        //send email
        EmailContent email = new EmailContent("About Proin app admin Sign Up",
                "Your proin app sign up request was recieved, we will follow up shortly",
                admin.getEmail());
        emailSenderScheduler.sendEmailToClient(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
