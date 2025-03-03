package com.example.addressbook.service;

import com.example.addressbook.entity.AddressBook;
import com.example.addressbook.entity.Friend;
import com.example.addressbook.entity.User;
import com.example.addressbook.exception.ApplicationException;
import com.example.addressbook.model.AddressBookDto;
import com.example.addressbook.model.FriendDto;
import com.example.addressbook.repository.AddressBookRepository;
import com.example.addressbook.repository.FriendRepository;
import com.example.addressbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

@Service
public class AddressBookServiceImpl implements AddressBookService{

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRepository friendRepository;

    /**
     * Creates a new address book for the user id passed in the parameter
     * @param userId
     * @param addressBookDto
     */
    @Override
    public void createAddressBook(Long userId, AddressBookDto addressBookDto) {
        AddressBook addressBook = new AddressBook();
        addressBook.setAddressBookName(addressBookDto.getAddressBookName());
        User user = userRepository.findById(userId)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUserName(addressBookDto.getUserName());
                    return userRepository.save(newUser);
                });
        addressBook.setUser(user);
        addressBookRepository.save(addressBook);
    }

    /**
     * Adds a new friend, name and phone no in the address book id specified for the userid in the parmeters
     * Validates the input for adding the friend details
     * @param userId
     * @param addressBookId
     * @param friendDto
     * @return
     */
    @Override
    @Transactional
    public FriendDto addFriendToAddressBook(Long userId, Long addressBookId, FriendDto friendDto) {

        AddressBook addressBook = addressBookRepository.findById(addressBookId).orElseThrow(() -> new ApplicationException("Address Book not found"));
        if (!addressBook.getUser().getUserId().equals(userId)){
           System.out.println("exception");
        }
        Friend friend =new Friend();
        friend.setName(friendDto.getFriendName());
        friend.setPhoneNumber(friendDto.getPhoneNo());
        friend.setAddressBook(addressBook);
        friend = friendRepository.save(friend);
        return friend.toDto();
    }

    /**
     * Returns the list of friends sorted by the name for the userId and the address book id specified in the parameters
     * @param userId
     * @param addressBookId
     * @return
     */
    @Override
    public List<FriendDto> getAllFriendsByName(Long userId,Long addressBookId) {
        AddressBook addressBook = addressBookRepository.findById(addressBookId).orElseThrow(() -> new ApplicationException("Address Book not found"));

        List<Friend> friends = friendRepository.findByAddressBook(addressBook);

        return friends.stream()
                    .map(friend -> new FriendDto(friend.getName(),friend.getPhoneNumber()))
                    .sorted(Comparator.comparing(com.example.addressbook.model.FriendDto::getFriendName))
                    .collect(toList());
    }

    /**
     * Find the unique friends of the user among the two address books specified in the parameters
     * @param userId
     * @param addressBookId1
     * @param addressBookId2
     * @return
     */
    @Override
    public List<String> findUniqueFriends(Long userId,Long addressBookId1,Long addressBookId2) {
         AddressBook addressBook1 = addressBookRepository.findById(addressBookId1)
                .orElseThrow(() -> new ApplicationException("AddressBook not found"));
        AddressBook addressBook2 = addressBookRepository.findById(addressBookId2)
                .orElseThrow(() -> new ApplicationException("AddressBook not found"));

        if (!addressBook1.getUser().getUserId().equals(userId) || !addressBook2.getUser().getUserId().equals(userId)) {
            throw new ApplicationException("AddressBooks do not belong to the user");
        }

        List<Friend> friends1 = friendRepository.findByAddressBook(addressBook1);
        List<Friend> friends2 = friendRepository.findByAddressBook(addressBook2);

        return  Stream.of(friends1,friends2)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Friend::getName, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
