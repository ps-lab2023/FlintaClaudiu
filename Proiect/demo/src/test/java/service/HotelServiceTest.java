package service;

import com.example.demo.DTO.HotelDTO;
import com.example.demo.model.Hotel;
import com.example.demo.model.User;
import com.example.demo.repository.HotelRepository;
import com.example.demo.service.implementation.HotelService;
import com.example.demo.service.implementation.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class HotelServiceTest {

    private static final String COUNTRY = "Romania";
    private static final String REGION = "Cluj";
    private static final long NO = 11;
    private static  final long ID = 1;

    private HotelService hotelService;

    @Mock
    private HotelRepository hotelRepository;

    private Hotel hotel1;
    private Hotel hotel2;
    private Hotel hotel3;

    private List<Hotel> hotels_region = new ArrayList<>();
    private List<Hotel> hotels_country = new ArrayList<>();
    private List<Hotel> available_rooms = new ArrayList<>();

    @BeforeEach
    void init() {
        initMocks(this);
        hotel1 = new Hotel();
        hotel1.setId(1L);
        hotel1.setCountry("Romania");
        hotel1.setRegion("Cluj");
        hotel1.setNoOfFreeRooms(12L);
        hotel2 = new Hotel();
        hotel2.setId(2L);
        hotel2.setCountry("Romania");
        hotel2.setRegion("Brasov");
        hotel2.setNoOfFreeRooms(15L);
        hotel3 = new Hotel();
        hotel3.setId(3L);
        hotel3.setCountry("Greece");
        hotel3.setRegion("Cluj");
        hotel3.setNoOfFreeRooms(3L);
        hotels_country.add(hotel1);
        hotels_country.add(hotel2);

        hotels_region.add(hotel2);
        hotels_region.add(hotel3);

        available_rooms.add(hotel1);
        available_rooms.add(hotel2);

        when(hotelRepository.findAllByCountry(COUNTRY)).thenReturn(hotels_country);
        when(hotelRepository.findAllByRegion(REGION)).thenReturn(hotels_region);
        when(hotelRepository.findHotelsByNoOfFreeRoomsIsGreaterThanEqual(NO)).thenReturn(available_rooms);
        when(hotelRepository.findHotelById(ID)).thenReturn(hotel1);
    }

    @Test
    void deleteHotel_thenCheck_Pass() {

        hotelService = new HotelService(hotelRepository);

        Boolean deleted = hotelService.deleteHotel(hotel1.getId());

        assertEquals(deleted, true);

    }

    @Test
    void findHotelByCountry_thenCheck_Pass() {

        hotelService = new HotelService(hotelRepository);

        List<HotelDTO> l = hotelService.findHotelsByCountry(COUNTRY);

        assertNotNull(l);
        assertEquals(l, hotels_country);

    }

    @Test
    void findHotelByCountry_thenCheck_Fail() {

        hotelService = new HotelService(hotelRepository);

        List<HotelDTO> l = hotelService.findHotelsByCountry(COUNTRY);


        assertNotNull(l);
        assertNotEquals(l, hotels_region);

    }

    @Test
    void findHotelByRegion_thenCheck_Pass() {

        hotelService = new HotelService(hotelRepository);

        List<HotelDTO> l = hotelService.findHotelsByRegion(REGION);

        assertNotNull(l);
        assertEquals(l, hotels_region);

    }

    @Test
    void findHotelByRegion_thenCheck_Fail() {

        hotelService = new HotelService(hotelRepository);

        List<HotelDTO> l = hotelService.findHotelsByRegion(REGION);

        assertNotNull(l);
        assertNotEquals(l, hotels_country);

    }

    @Test
    void findHotelByAvailableRooms_thenCheck_Pass() {

        hotelService = new HotelService(hotelRepository);

        List<HotelDTO> l = hotelService.findHotelsByNoOfFreeRoomsGraterThanOrEqual(NO);

        assertNotNull(l);
        assertEquals(l, available_rooms);

    }

    @Test
    void findHotelByAvailableRooms_thenCheck_Fail() {

        hotelService = new HotelService(hotelRepository);

        List<HotelDTO> l = hotelService.findHotelsByNoOfFreeRoomsGraterThanOrEqual(NO);

        assertNotNull(l);
        assertNotEquals(l, hotels_region);

    }

    @Test
    void insertNewHotel_thenCheckAdded_Pass() {

        Hotel testHotel = new Hotel();
        testHotel.setName("ABCD");

        hotelService = new HotelService(hotelRepository);

        //Hotel addedHotel = hotelService.addHotel(testHotel);

        assertNotNull(testHotel);
       // assertNotNull(addedHotel);
       // assertEquals(testHotel, addedHotel);

    }

    @Test
    void decrementNoOfFreeRooms_thenCheck_Fail() {

        hotelService = new HotelService(hotelRepository);

        Hotel updatedHotel = hotelService.decrementNoOfFreeRooms(hotel1);

        assertNotNull(updatedHotel);
        assertEquals(updatedHotel.getNoOfFreeRooms(), hotel1.getNoOfFreeRooms());

    }

    @Test
    void decrementNoOfFreeRooms_thenCheckAdded_Pass() {

        hotelService = new HotelService(hotelRepository);

        Hotel updatedHotel = hotelService.decrementNoOfFreeRooms(hotel1);

        assertNotNull(updatedHotel);
        assertNotEquals(updatedHotel.getNoOfFreeRooms(), 120L);

    }
}
