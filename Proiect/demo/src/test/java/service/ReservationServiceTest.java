package service;

import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.implementation.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Date;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ReservationServiceTest {

    private static final long ID = 1;
    private static final Date START = new Date();
    private static final Date END = new Date();

    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    private Reservation reservation;

    @BeforeEach
    void init() {
        initMocks(this);
        reservation = new Reservation();
        reservation.setId(ID);

        when(reservationRepository.findReservationById(ID)).thenReturn(reservation);
    }

    @Test
    void insertNewReservation_thenCheckAdded_Pass() {


        reservationService = new ReservationService(reservationRepository);

        Reservation addedReservation = reservationService.addReservation(reservation);

        assertNotNull(addedReservation);
        assertNotNull(reservation);
        assertEquals(addedReservation, reservation);

    }

    @Test
    void deleteReservation_thenCheck_Pass() {

        reservationService = new ReservationService(reservationRepository);

        Boolean deleted = reservationService.deleteReservation(reservation);

        assertEquals(deleted, true);

    }

    @Test
    void updateRoomPricePerNight_thenCheck_Fail() {

        when(reservationRepository.findReservationById(ID)).thenReturn(null);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            reservationService.updateReservationInterval(reservation,START,END);
        });
    }

    @Test
    void updateRoomPricePerNight_thenCheckAdded_Pass() {

        reservationService = new ReservationService(reservationRepository);

        Reservation updatedReservation = reservationService.updateReservationInterval(reservation,START,END);

        assertNotNull(updatedReservation);
        assertEquals(updatedReservation.getStartDate(),START);
        assertEquals(updatedReservation.getEndDate(),END);

    }

}
