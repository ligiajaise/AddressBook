package com.example.addressbook.controller;

import com.example.addressbook.model.AddressBookDto;
import com.example.addressbook.model.FriendDto;
import com.example.addressbook.service.AddressBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<Void> createAddressBook(@PathVariable Long userId,@RequestBody
    AddressBookDto addressDto) {
        addressBookService.createAddressBook(userId,addressDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create/{userId}/{addressBookId}/friends")
    public ResponseEntity<FriendDto> addFriends(@PathVariable Long userId, @PathVariable Long addressBookId, @RequestBody @Valid
                                                     FriendDto friendDto) {
        return ResponseEntity.ok(addressBookService.addFriendToAddressBook(userId,addressBookId,friendDto));
    }

    @GetMapping("/get/{userId}/{addressBookId}/friends")
    public ResponseEntity<List<FriendDto>> getFriendsByUserId(@PathVariable Long userId, @PathVariable Long addressBookId) {
        return ResponseEntity.ok(addressBookService.getAllFriendsByName(userId,addressBookId));
    }

    @GetMapping("/get/{userId}/{addressBookId1}/friends/{addressBookId2}")
    public ResponseEntity<List<String>> getUniqueFriends(@PathVariable Long userId,@PathVariable Long addressBookId1,@PathVariable Long addressBookId2) {
        return ResponseEntity.ok(addressBookService.findUniqueFriends(userId,addressBookId1,addressBookId2));
    }

}
