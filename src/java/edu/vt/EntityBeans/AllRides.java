/*
 * Created by Justin Kennedy on 2019.12.01  * 
 * Copyright © 2019 Justin Kennedy. All rights reserved. * 
 */
package edu.vt.EntityBeans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jusmk96
 */
@Entity
@Table(name = "AllRides")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AllRides.findAll", query = "SELECT a FROM AllRides a")
    , @NamedQuery(name = "AllRides.findById", query = "SELECT a FROM AllRides a WHERE a.id = :id")
    , @NamedQuery(name = "AllRides.findByPassanger1Id", query = "SELECT a FROM AllRides a WHERE a.passanger1Id = :passanger1Id")
    , @NamedQuery(name = "AllRides.findByPassanger2Id", query = "SELECT a FROM AllRides a WHERE a.passanger2Id = :passanger2Id")
    , @NamedQuery(name = "AllRides.findByPassanger3Id", query = "SELECT a FROM AllRides a WHERE a.passanger3Id = :passanger3Id")
    , @NamedQuery(name = "AllRides.findByPassanger4Id", query = "SELECT a FROM AllRides a WHERE a.passanger4Id = :passanger4Id")
    , @NamedQuery(name = "AllRides.findByPassanger5Id", query = "SELECT a FROM AllRides a WHERE a.passanger5Id = :passanger5Id")
    , @NamedQuery(name = "AllRides.findByPassanger6Id", query = "SELECT a FROM AllRides a WHERE a.passanger6Id = :passanger6Id")
    , @NamedQuery(name = "AllRides.findBySeatsAvailable", query = "SELECT a FROM AllRides a WHERE a.seatsAvailable = :seatsAvailable")
    , @NamedQuery(name = "AllRides.findByStartingCity", query = "SELECT a FROM AllRides a WHERE a.startingCity = :startingCity")
    , @NamedQuery(name = "AllRides.findByStartingState", query = "SELECT a FROM AllRides a WHERE a.startingState = :startingState")
    , @NamedQuery(name = "AllRides.findByEndingCity", query = "SELECT a FROM AllRides a WHERE a.endingCity = :endingCity")
    , @NamedQuery(name = "AllRides.findByEndingState", query = "SELECT a FROM AllRides a WHERE a.endingState = :endingState")
    , @NamedQuery(name = "AllRides.findByTripDate", query = "SELECT a FROM AllRides a WHERE a.tripDate = :tripDate")
    , @NamedQuery(name = "AllRides.findByNumberOfPassangers", query = "SELECT a FROM AllRides a WHERE a.numberOfPassangers = :numberOfPassangers")
    , @NamedQuery(name = "AllRides.findByUserPrimaryKey", query = "SELECT a FROM AllRides a WHERE a.driverId.id = :primaryKey")
})

public class AllRides implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "passanger_1_id")
    private int passanger1Id;
    @Basic(optional = false)
    @Column(name = "passanger_2_id")
    private int passanger2Id;
    @Basic(optional = false)
    @Column(name = "passanger_3_id")
    private int passanger3Id;
    @Basic(optional = false)
    @Column(name = "passanger_4_id")
    private int passanger4Id;
    @Basic(optional = false)
    @Column(name = "passanger_5_id")
    private int passanger5Id;
    @Basic(optional = false)
    @Column(name = "passanger_6_id")
    private int passanger6Id;
    @Basic(optional = false)
    @Column(name = "seats_available")
    @NotNull
    private int seatsAvailable;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "startingAddress1")
    private String startingAddress1;
    @Size(max = 128)
    @Column(name = "startingAddress2")
    private String startingAddress2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "startingCity")
    private String startingCity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "startingState")
    private String startingState;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "startingZipcode")
    private String startingZipcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "endingAddress1")
    private String endingAddress1;
    @Size(max = 128)
    @Column(name = "endingAddress2")
    private String endingAddress2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "endingCity")
    private String endingCity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "endingState")
    private String endingState;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "endingZipcode")
    private String endingZipcode;
    @Basic(optional = false)
    @Column(name = "trip_time")
    private int trip_time;
    @Basic(optional = false)
    @Column(name = "trip_distance")
    private int trip_distance;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trip_cost")
    private int trip_cost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trip_date")
    @Temporal(TemporalType.DATE)
    private Date tripDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number_of_passangers")
    private int numberOfPassangers;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "carMake")
    private String carMake;
    @Basic(optional = false)
    //@NotNull
    @Size(min = 1, max = 128)
    @Column(name = "carModel")
    private String carModel;
    @Basic(optional = false)
    //@NotNull
    @Size(min = 1, max = 128)
    @Column(name = "carColor")
    private String carColor;
    @Basic(optional = false)
    //@NotNull
    @Size(min = 1, max = 32)
    @Column(name = "carLicensePlate")
    private String carLicensePlate;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "carMpg")
    private int carMpg;
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    @ManyToOne
    private User driverId;

    public AllRides() {
    }

    public AllRides(Integer id) {
        this.id = id;
    }

    public AllRides(Integer id, int passanger1Id, int passanger2Id, 
            int passanger3Id, int passanger4Id, int passanger5Id, 
            int passanger6Id, int seatsAvailable, String startingAddress1, 
            String startingCity, String startingState,
            String startingZipcode, String endingAddress1, String endingCity,
            String endingState, String endingZipcode, int trip_time, int trip_distance,
            int trip_cost, String carMake, String carModel, String carColor, int carMpg,
            String carLicensePlate, Date tripDate, int numberOfPassangers) {
        this.id = id;
        this.passanger1Id = passanger1Id;
        this.passanger2Id = passanger2Id;
        this.passanger3Id = passanger3Id;
        this.passanger4Id = passanger4Id;
        this.passanger5Id = passanger5Id;
        this.passanger6Id = passanger6Id;
        this.seatsAvailable = seatsAvailable;
        this.startingAddress1 = startingAddress1;
        this.startingCity = startingCity;
        this.startingState = startingState;
        this.startingZipcode = startingZipcode;
        this.endingAddress1 = endingAddress1;
        this.endingCity = endingCity;
        this.endingState = endingState;
        this.endingZipcode = endingZipcode;
        this.trip_time = trip_time;
        this.trip_distance = trip_distance;
        this.trip_cost = trip_cost;
        this.carMake = carMake;
        this.carModel = carModel;
        this.carColor = carColor;
        this.carMpg = carMpg;
        this.carLicensePlate = carLicensePlate;
        this.tripDate = tripDate;
        this.numberOfPassangers = numberOfPassangers;
    }
    
    public void setAll( 
            int passanger1Id, int passanger2Id, int passanger3Id, 
            int passanger4Id, int passanger5Id, int passanger6Id, 
            int seatsAvailable, String startingAddress1, String startingCity, String startingState,
            String startingZipcode, String endingAddress1, String endingCity,
            String endingState, String endingZipcode, int trip_time, int trip_distance,
            int trip_cost, String carMake, String carModel, String carColor, int carMpg,
            String carLicensePlate, 
            Date tripDate, int numberOfPassangers){
        this.passanger1Id = passanger1Id;
        this.passanger2Id = passanger2Id;
        this.passanger3Id = passanger3Id;
        this.passanger4Id = passanger4Id;
        this.passanger5Id = passanger5Id;
        this.passanger6Id = passanger6Id;
        this.seatsAvailable = seatsAvailable;
        this.startingAddress1 = startingAddress1;
        this.startingCity = startingCity;
        this.startingState = startingState;
        this.startingZipcode = startingZipcode;
        this.endingAddress1 = endingAddress1;
        this.endingCity = endingCity;
        this.endingState = endingState;
        this.endingZipcode = endingZipcode;
        this.trip_time = trip_time;
        this.trip_distance = trip_distance;
        this.trip_cost = trip_cost;
        this.carMake = carMake;
        this.carModel = carModel;
        this.carColor = carColor;
        this.carMpg = carMpg;
        this.carLicensePlate = carLicensePlate;
        this.tripDate = tripDate;
        this.numberOfPassangers = numberOfPassangers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPassanger1Id() {
        return passanger1Id;
    }

    public void setPassanger1Id(int passanger1Id) {
        this.passanger1Id = passanger1Id;
    }

    public int getPassanger2Id() {
        return passanger2Id;
    }

    public void setPassanger2Id(int passanger2Id) {
        this.passanger2Id = passanger2Id;
    }

    public int getPassanger3Id() {
        return passanger3Id;
    }

    public void setPassanger3Id(int passanger3Id) {
        this.passanger3Id = passanger3Id;
    }

    public int getPassanger4Id() {
        return passanger4Id;
    }

    public void setPassanger4Id(int passanger4Id) {
        this.passanger4Id = passanger4Id;
    }

    public int getPassanger5Id() {
        return passanger5Id;
    }

    public void setPassanger5Id(int passanger5Id) {
        this.passanger5Id = passanger5Id;
    }

    public int getPassanger6Id() {
        return passanger6Id;
    }

    public void setPassanger6Id(int passanger6Id) {
        this.passanger6Id = passanger6Id;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public String getStartingAddress1() {
        return startingAddress1;
    }

    public void setStartingAddress1(String startingAddress1) {
        this.startingAddress1 = startingAddress1;
    }

    public String getStartingAddress2() {
        return startingAddress2;
    }

    public void setStartingAddress2(String startingAddress2) {
        this.startingAddress2 = startingAddress2;
    }

    public String getStartingCity() {
        return startingCity;
    }

    public void setStartingCity(String startingCity) {
        this.startingCity = startingCity;
    }

    public String getStartingState() {
        return startingState;
    }

    public void setStartingState(String startingState) {
        this.startingState = startingState;
    }

    public String getStartingZipcode() {
        return startingZipcode;
    }

    public void setStartingZipcode(String startingZipcode) {
        this.startingZipcode = startingZipcode;
    }

    public String getEndingAddress1() {
        return endingAddress1;
    }

    public void setEndingAddress1(String endingAddress1) {
        this.endingAddress1 = endingAddress1;
    }

    public String getEndingAddress2() {
        return endingAddress2;
    }

    public void setEndingAddress2(String endingAddress2) {
        this.endingAddress2 = endingAddress2;
    }

    public String getEndingCity() {
        return endingCity;
    }

    public void setEndingCity(String endingCity) {
        this.endingCity = endingCity;
    }

    public String getEndingState() {
        return endingState;
    }

    public void setEndingState(String endingState) {
        this.endingState = endingState;
    }

    public String getEndingZipcode() {
        return endingZipcode;
    }

    public void setEndingZipcode(String endingZipcode) {
        this.endingZipcode = endingZipcode;
    }

    public int getTrip_time() {
        return trip_time;
    }

    public void setTrip_time(int trip_time) {
        this.trip_time = trip_time;
    }

    public int getTrip_distance() {
        return trip_distance;
    }

    public void setTrip_distance(int trip_distance) {
        this.trip_distance = trip_distance;
    }

    public int getTrip_cost() {
        return trip_cost;
    }

    public void setTrip_cost(int trip_cost) {
        this.trip_cost = trip_cost;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarLicensePlate() {
        return carLicensePlate;
    }

    public void setCarLicensePlate(String carLicensePlate) {
        this.carLicensePlate = carLicensePlate;
    }

    public int getCarMpg() {
        return carMpg;
    }

    public void setCarMpg(int carMpg) {
        this.carMpg = carMpg;
    }

    

    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }

    public int getNumberOfPassangers() {
        return numberOfPassangers;
    }

    public void setNumberOfPassangers(int numberOfPassangers) {
        this.numberOfPassangers = numberOfPassangers;
    }

    public User getDriverId() {
        return driverId;
    }

    public void setDriverId(User driverId) {
        this.driverId = driverId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AllRides)) {
            return false;
        }
        AllRides other = (AllRides) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }
    
}
