package lk.ijse.elitedrivingschoolproject.util;

import lk.ijse.elitedrivingschoolproject.dto.UserDTO;
import lk.ijse.elitedrivingschoolproject.entity.User;
import lombok.Getter;
import lombok.Setter;

public class AuthUtil {

    @Getter
    @Setter

    private static UserDTO currentUser;

    public static String getRole(){
        return currentUser != null ? currentUser.getRole() : "Admin";
    }

    private static void clear(){
        currentUser = null;
    }


}
