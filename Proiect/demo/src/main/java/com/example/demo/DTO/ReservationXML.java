package com.example.demo.DTO;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Date;

@XmlRootElement(name = "reservation")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReservationXML {

    @XmlElement(name = "start-date")
    private Date startDate;

    @XmlElement(name = "end-date")
    private Date endDate;

    @XmlElement(name = "hotel-address")
    private String hotelAddress;

    public ReservationXML() {}

    public ReservationXML(Date startDate, Date endDate, String hotelAddress) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.hotelAddress = hotelAddress;
    }


}
