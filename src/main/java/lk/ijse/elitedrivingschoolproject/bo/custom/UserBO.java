package lk.ijse.elitedrivingschoolproject.bo.custom;

import lk.ijse.elitedrivingschoolproject.bo.SuperBO;
import lk.ijse.elitedrivingschoolproject.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserBO extends SuperBO {

    List<UserDTO> getAllUsers() throws Exception;

    String getLastUserId() throws Exception;

    boolean saveUser(UserDTO userDTO) throws Exception;

    boolean updateUser(UserDTO userDTO) throws Exception;

    boolean deleteUser(String id) throws Exception;

    List<String> getAllUserIds() throws Exception;

    Optional<UserDTO> findByUserId(String id) throws Exception;

    String generateNewUserID() throws Exception;

    public UserDTO getUserByEmail(String email) ;



}
