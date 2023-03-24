package com.example.demo;

import com.example.demo.model.Hotel;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.repository.HotelRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.HotelServiceInterface;
import com.example.demo.service.ReservationServiceInterface;
import com.example.demo.service.RoomServiceInterface;
import com.example.demo.service.UserServiceInterface;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EntityScan
@SpringBootApplication
@EnableJpaRepositories
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository, HotelRepository hotelRepository, RoomRepository roomRepository, ReservationRepository reservationRepository, UserServiceInterface userServiceInterface, HotelServiceInterface hotelServiceInterface, RoomServiceInterface roomServiceInterface, ReservationServiceInterface reservationServiceInterface){
		return args->{


			Room room1 = new Room();
			room1.setName("Standard Room");
			room1.setPricePerNight(119.99f);
			Room room2 = new Room();
			room2.setName("Master Room");
			room2.setPricePerNight(179.99f);

			List<Room> rooms = new ArrayList<>();

			rooms.add(room1);
			rooms.add(room2);

			Hotel hotel = new Hotel();
			hotel.setName("Aro Palace");
			hotel.setCountry("Romania");
			hotel.setRegion("Brasov");
			hotel.setAddress("Street Bulevardul Eroilor, no. 1");
			hotel.setEmail("hotelaro@office.com");
			hotel.setNoOfFreeRooms(15l);
			hotel.setNoOfRooms(22l);
			hotel.setTelefon(07536412373l);


			hotel.setRooms(rooms);
			hotelRepository.save(hotel);

			room1.setHotel(hotel);
			room2.setHotel(hotel);
			roomRepository.save(room1);
			roomRepository.save(room2);


			Room room3 = new Room();
			room3.setName("Standard Room");
			room3.setPricePerNight(109.99f);
			Room room4 = new Room();
			room4.setName("Master Premium Room");
			room4.setPricePerNight(229.99f);

			List<Room> rooms2 = new ArrayList<>();

			rooms2.add(room3);
			rooms2.add(room4);


			Hotel hotel2 = new Hotel();
			hotel2.setName("Raddison Blue");
			hotel2.setCountry("Romania");
			hotel2.setRegion("Cluj");
			hotel2.setAddress("Street Aleea Staduionului , no. 22a");
			hotel2.setEmail("radissonblue@office.com");
			hotel2.setNoOfFreeRooms(25l);
			hotel2.setNoOfRooms(99l);
			hotel2.setTelefon(0764536552l);

			room3.setHotel(hotel2);
			room4.setHotel(hotel2);


			hotel2.setRooms(rooms2);
			hotelRepository.save(hotel2);


			roomRepository.save(room3);
			roomRepository.save(room4);



			User user =  new User();
			user.setLastName("Flinta");
			user.setFirstName("Claudiu");
			user.setPassword("12345");


			Reservation reservation = new Reservation();

			user.addReservation(reservation);

			userRepository.save(user);

			reservation.setUser(user);
			reservation.setRoom(room2);

			reservationRepository.save(reservation);

			User user1 =  new User();
			user1.setLastName("Flinta");
			user1.setFirstName("Claudiu");
			user1.setPassword("12345");

			userRepository.save(user1);

			userServiceInterface.updateUserPassword(user1,"ala bala");

			System.out.println(user1);



			hotelServiceInterface.findHotelsByCountry("Romania").forEach(
					componenta -> {

						System.out.println("Id: " + componenta.getId() + " Address: " + ((Hotel) componenta).getAddress());

					}
			);

			User user3 = new User();
			user3.setUserName("IonIon");

			Reservation reservation1 = new Reservation();

			user3.addReservation(reservation1);

			userRepository.save(user3);


			reservation1.setRoom(room3);
			reservation1.setUser(user3);

			reservationRepository.save(reservation1);

			Date d1 = new Date();
			Date d2 = new Date();

			reservationServiceInterface.updateReservationInterval(reservation1,d1,d2);

			reservationServiceInterface.updateReservationInterval(reservation,d1,d2);


			User delete_user = new User();

			userServiceInterface.addUser(delete_user);

			System.out.println(userServiceInterface.findUserById(delete_user.getId()));

			userServiceInterface.deleteUser(delete_user);

			System.out.println(userServiceInterface.findUserById(user.getId()));


			userServiceInterface.deleteUser(user);



		};
	}

}
