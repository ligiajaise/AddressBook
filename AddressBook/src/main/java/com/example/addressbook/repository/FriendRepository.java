package com.example.addressbook.repository;

import com.example.addressbook.entity.AddressBook;
import com.example.addressbook.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend,Long> {
    List<Friend> findByAddressBook(AddressBook addressBook);
}
