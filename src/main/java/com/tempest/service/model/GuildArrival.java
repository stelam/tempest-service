package com.tempest.service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by stelam on 2017-11-14.
 */
@Entity
@Table(name = "guild_arrivals")
public class GuildArrival {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "guild_arrival_id")
    private long id;

    @Column(name = "guild_arrival_date")
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date arrivalDate;

    @Column(name = "guild_departure_date")
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date departureDate;

    @ManyToOne
    @JoinColumn (name="member_id")
    @JsonBackReference
    private Member member;

    public long getId() {
        return id;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Member getMember() {
        return member;
    }
}
