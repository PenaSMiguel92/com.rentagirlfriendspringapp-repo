package com.rentagirlfriend.entities;

import java.time.LocalDateTime;

import com.rentagirlfriend.utils.BookingStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Data
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private long booking_id;
    @Column(name = "posted_by")
    private long posted_by;
    //private int hours_planned;
    @Column(name = "potential_earnings")
    private double potential_earnings;
    @Column(name = "booking_status")
    private BookingStatus booking_status;
    @Column(name = "begin_time")
    private LocalDateTime begin_time;
    @Column(name = "end_time")
    private LocalDateTime end_time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    Account account;
    //ToDo: figure out how to represent day-hour-minute
    //begintime, endtime;
    public Booking(long posted_by, double potential_earnings, LocalDateTime begin_time, LocalDateTime end_time) {
        this.posted_by = posted_by;
        this.potential_earnings = potential_earnings;
        this.begin_time = begin_time;
        this.end_time = end_time;
    }




}


