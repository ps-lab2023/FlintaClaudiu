package com.example.demo.controller;

import com.example.demo.DTO.ReservationDTO;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.model.User;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    @PostMapping("/send-email/{id}")
    public ResponseEntity<String> sendEmail(@PathVariable Long id) {
        try {

            ReservationDTO reservation = reservationMapper.mapModelToDto(reservationRepository.findReservationById(id));

            String emailBody = "Reservation details:\n" +
                    "Reservation ID: " + reservation.getId() + "\n" +
                    "Start Date: " + reservation.getStartDate() + "\n" +
                    "End Date: " + reservation.getEndDate() + "\n" +
                    "Hotel Name: " + reservation.getHotelName() + "\n" +
                    "Hotel Address: " + reservation.getHotelAddress() + "\n";


            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo("claudiu_12@yahoo.com");
            helper.setSubject("New Reservation");
            helper.setText(emailBody);

            FileSystemResource file = new FileSystemResource("C:\\Users\\Claudiu\\Documents\\Anul 3\\Sem 2\\PS\\Proiect\\reservation.xml");
            helper.addAttachment("reservation.xml", file);

            javaMailSender.send(mimeMessage);

            return ResponseEntity.ok("Email sent successfully!");
        } catch (MailException ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + ex.getMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + e.getMessage());
        }
    }

    @PostMapping("/send-email2/{id}")
    public ResponseEntity<String> sendEmail2(@PathVariable Long id) {
        try {

            ReservationDTO reservation = reservationMapper.mapModelToDto(reservationRepository.findReservationById(id));

            String emailBody = "Reservation with id: " + reservation.getId() +
                    " was cancelled successfully" + "\n";

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo("claudiu_12@yahoo.com");
            mailMessage.setSubject("New Reservation");
            mailMessage.setText(emailBody);
            mailMessage.setFrom("bookingb37@gmail.com");

            javaMailSender.send(mailMessage);

            return ResponseEntity.ok("Email sent successfully!");
        } catch (MailException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + ex.getMessage());
        }
    }

    @PostMapping("/send-email3/{email}")
    public ResponseEntity<String> sendEmail3(@PathVariable String email) {
        try {
            User user = userRepository.findUserByEmail(email);
            String emailBody = "The password is: " + user.getPassword();

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo("claudiu_12@yahoo.com");
            mailMessage.setSubject("New Reservation");
            mailMessage.setText(emailBody);
            mailMessage.setFrom("bookingb37@gmail.com");

            javaMailSender.send(mailMessage);

            return ResponseEntity.ok("Email sent successfully!");
        } catch (MailException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + ex.getMessage());
        }
    }
}


