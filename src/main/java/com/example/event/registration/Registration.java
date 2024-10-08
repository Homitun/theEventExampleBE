package com.example.event.registration;

import java.time.LocalDate;
import java.util.List;

import com.example.event.event.Event;
import com.example.event.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    private LocalDate registrationDate = LocalDate.now();
    private Integer status = 0; // nếu là -1 đã thanh toán rồi 
    private Integer paymentMethod;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User users;
    private String refuseMessage;
    // Getters and setters
}