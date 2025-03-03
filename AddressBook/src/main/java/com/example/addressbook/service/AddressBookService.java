package com.example.addressbook.service;

import com.example.addressbook.model.AddressBookDto;
import com.example.addressbook.model.FriendDto;
import java.util.List;

public interface AddressBookService {

    public void createAddressBook(Long userId, AddressBookDto friendsDto);
    public FriendDto addFriendToAddressBook(Long userId, Long addressBookId, FriendDto friendDto) ;
    public List<FriendDto> getAllFriendsByName(Long userId,Long addressBookId) ;
    public List<String> findUniqueFriends(Long userId,Long addressBookId1,Long addressBookId2);

}
