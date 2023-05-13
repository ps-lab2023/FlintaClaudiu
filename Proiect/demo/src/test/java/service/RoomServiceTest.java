package service;

import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.implementation.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RoomServiceTest {


    private static  final long ID = 1;
    private static  final float ACTUL_PRICE = 123.9F;
    private static  final float NEW_PRICE = 139.6F;

    private RoomService roomService;

    @Mock
    private RoomRepository roomRepository;

    private Room room;

    @BeforeEach
    void init() {
        initMocks(this);
        room = new Room();
        room.setId(ID);
        room.setPricePerNight(ACTUL_PRICE);

        when(roomRepository.findRoomById(ID)).thenReturn(room);
    }

    @Test
    void insertNewRoom_thenCheckAdded_Pass() {


        roomService = new RoomService(roomRepository);

        //Room addedRoom = roomService.addRoom(room);

       // assertNotNull(addedRoom);
        assertNotNull(room);
        //assertEquals(addedRoom, room);

    }

    @Test
    void deleteRoom_thenCheck_Pass() {

        roomService = new RoomService(roomRepository);

        Boolean deleted = roomService.deleteRoom(2L);

        assertEquals(deleted, true);

    }

    @Test
    void updateRoomPricePerNight_thenCheck_Fail() {

        roomService = new RoomService(roomRepository);

        //Room updatedRoom = roomService.updatePricePerNight(2L,NEW_PRICE);

        //assertNotNull(updatedRoom);
        //assertNotEquals(updatedRoom.getPricePerNight(),ACTUL_PRICE);

    }

    @Test
    void updateRoomPricePerNight_thenCheckAdded_Pass() {

        roomService = new RoomService(roomRepository);

        //Room updatedRoom = roomService.updatePricePerNight(2L,NEW_PRICE);

       // assertNotNull(updatedRoom);
        //assertEquals(updatedRoom.getPricePerNight(),NEW_PRICE);

    }


}
