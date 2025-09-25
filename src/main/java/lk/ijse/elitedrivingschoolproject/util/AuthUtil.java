package lk.ijse.elitedrivingschoolproject.util;

import lk.ijse.elitedrivingschoolproject.entity.User;
import lombok.Getter;
import lombok.Setter;

public class AuthUtil {

    @Getter
    @Setter

    private static User user;

    public static String getRole(){
        return user != null ? user.getRole() : null;
    }

    private static void clear(){
        user = null;
    }


}
