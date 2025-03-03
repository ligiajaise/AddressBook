package com.example.addressbook.controller;

import com.example.addressbook.model.FriendDto;
import com.example.addressbook.service.AddressBookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class AddressBookControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressBookService addressBookService;

    private FriendDto friendDto;

    @BeforeEach
    public void setUp(){
        friendDto= new FriendDto("friend1", "1234567890");
    }

    @Test
    public void testAddFriends() throws Exception{
        when(addressBookService.addFriendToAddressBook(1L, 1L,friendDto)).thenReturn(friendDto);


        ResultActions result = mockMvc.perform(post("/api/v1/addressbook/create/1/1/friends")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(friendDto)));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.friendName").value("friend1"))
                .andExpect(jsonPath("$.phoneNo").value("1234567890"));


    }

    @Test
    public void testGetFriendsByUserId() throws Exception {
        // Arrange
        when(addressBookService.getAllFriendsByName(1L,1L)).thenReturn(List.of(friendDto));

        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/addressbook/get/1/1/friends"));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].friendName").value("friend1"))
                .andExpect(jsonPath("$[0].phoneNo").value("1234567890"));
    }

    @Test
    public void testGetUniqueFriends() throws Exception {
        // Arrange
        when(addressBookService.findUniqueFriends(1L,1L,2L)).thenReturn(List.of(friendDto));

        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/addressbook/get/1/1/friends/2"));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].friendName").value("friend1"))
                .andExpect(jsonPath("$[0].phoneNo").value("1234567890"));
    }
}
