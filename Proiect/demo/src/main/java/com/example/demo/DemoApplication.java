package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.repository.HotelRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.SecurityConfiguration;
import com.example.demo.service.HotelServiceInterface;
import com.example.demo.service.ReservationServiceInterface;
import com.example.demo.service.RoomServiceInterface;
import com.example.demo.service.UserServiceInterface;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan
@SpringBootApplication
@EnableJpaRepositories
@Import(SecurityConfiguration.class)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository, HotelRepository hotelRepository, RoomRepository roomRepository, ReservationRepository reservationRepository, UserServiceInterface userServiceInterface, HotelServiceInterface hotelServiceInterface, RoomServiceInterface roomServiceInterface, ReservationServiceInterface reservationServiceInterface){
		return args->{


			User u =  userRepository.findFirstById(52L);
			if(u!= null) {
				System.out.println(u.getReservationList());
			}


		};
	}
}
