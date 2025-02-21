package model.entities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reservation {
    
    private Integer roomNumber;
    private LocalDate checkin;
    private LocalDate checkout;
    
    public Reservation(){
    }
    public Reservation(Integer roomNumber, LocalDate checkin, LocalDate checkout) {
        this.roomNumber = roomNumber;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Integer getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }
    public LocalDate getCheckin() {
        return checkin;
    }
    public LocalDate getCheckout() {
        return checkout;
    }

    public long duration(){
        Duration d = Duration.between(checkin.atStartOfDay(), checkout.atStartOfDay());
        return d.toDays();
    }

    public String updateDates(LocalDate checkin, LocalDate checkout){
        String date = "06/06/2018";
        LocalDate now = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        if (checkin.isBefore(now) || checkout.isBefore(now)){
            return "Reservation dates for update must be future dates";
        }
        if (!checkout.isAfter(checkin)){
            return "Check-out date must be after check-in date";
        }
        this.checkin = checkin;
        this.checkout = checkout;
        return null;
    }

    @Override
    public String toString(){
        return "Room " +
                getRoomNumber() +
                ", check-in: " +
                fmt.format(checkin) +
                ", check-out: " +
                fmt.format(checkout) +
                ", " +
                duration() +
                " nights"; 
    }   
}
