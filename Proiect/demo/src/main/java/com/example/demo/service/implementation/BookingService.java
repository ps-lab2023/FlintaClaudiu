package com.example.demo.service.implementation;

import com.example.demo.DTO.ReservationDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(ReservationDTO booking) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("recipient@example.com");
        message.setSubject("New Booking");
        message.setText("Booking ID: " + booking.getId() +
                "\nStart Date: " + booking.getStartDate() +
                "\nEnd Date: " + booking.getEndDate() +
                "\nHotel Name: " + booking.getHotelName() +
                "\nHotel Address: " + booking.getHotelAddress());

        javaMailSender.send(message);
    }
}
