package ru.java.mentor.oldranger.club.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.java.mentor.oldranger.club.model.user.UserProfile;

@Data
@AllArgsConstructor
public class UpdateProfileDto {

    private UserProfile profile;
    private ErrorDto error;
}
