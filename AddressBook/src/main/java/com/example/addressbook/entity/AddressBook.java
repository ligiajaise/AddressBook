package com.example.addressbook.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class AddressBook {

    @Id
    @Column(name="address_book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressBookId;

    private String addressBookName;

    @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "addressBook", cascade = CascadeType.ALL)
    private List<Friend> friends;


}
