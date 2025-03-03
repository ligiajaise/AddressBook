package com.example.addressbook.repository;

import com.example.addressbook.entity.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressBookRepository extends JpaRepository<AddressBook,Long> {
}
