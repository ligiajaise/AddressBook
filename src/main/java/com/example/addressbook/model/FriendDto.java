package com.example.addressbook.model;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FriendDto {

    @NotNull(message = "Friend Name is required")
    private String friendName;
    @NotNull(message = "Phone Number is required")
    @Pattern(regexp = "^[0-9]+$", message = "Phone number should only contain digits.")
    @Size(min=10, max = 12, message = "Not a valid Phone Number.")
    private String phoneNo;

}
