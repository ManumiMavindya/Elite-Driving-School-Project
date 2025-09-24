package lk.ijse.elitedrivingschoolproject.bo.custom.impl;

import lk.ijse.elitedrivingschoolproject.bo.custom.UserBO;
import lk.ijse.elitedrivingschoolproject.bo.exception.DuplicateException;
import lk.ijse.elitedrivingschoolproject.bo.exception.NotFoundException;
import lk.ijse.elitedrivingschoolproject.bo.util.EntityDTOConverter;
import lk.ijse.elitedrivingschoolproject.dao.DAOFactory;
import lk.ijse.elitedrivingschoolproject.dao.DAOTypes;
import lk.ijse.elitedrivingschoolproject.dao.custom.UserDAO;
import lk.ijse.elitedrivingschoolproject.dto.UserDTO;
import lk.ijse.elitedrivingschoolproject.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBOImpl implements UserBO {

    private final UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOTypes.USER);
    private final EntityDTOConverter entityDTOConverter = new EntityDTOConverter();


    @Override
    public List<UserDTO> getAllUsers() throws Exception {

        List<User> userList = userDAO.getAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            userDTOList.add(entityDTOConverter.getUserDTO(user));
        }
        return userDTOList;
    }

    @Override
    public String getLastUserId() throws Exception {

        return userDAO.getLastId();
    }

    @Override
    public boolean saveUser(UserDTO userDTO) throws Exception {

        Optional<User> user = userDAO.findById(userDTO.getUserId());
        if (user.isPresent()) {
            throw new DuplicateException("User already exists");
        }
        return userDAO.save(entityDTOConverter.getUserEntity(userDTO));

    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws Exception {

        Optional<User> user = userDAO.findById(userDTO.getUserId());
        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        return userDAO.update(entityDTOConverter.getUserEntity(userDTO));
    }

    @Override
    public boolean deleteUser(String id) throws Exception {

        Optional<User> user = userDAO.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        return userDAO.delete(id);
    }

    @Override
    public List<String> getAllUserIds() throws Exception {

        return userDAO.getAllIds();
    }

    @Override
    public Optional<UserDTO> findByUserId(String id) throws Exception {

        Optional<User> user = userDAO.findById(id);
        if (user.isPresent()) {
            return Optional.of(entityDTOConverter.getUserDTO(user.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String generateNewUserID() throws Exception {

        return userDAO.generateNewId();
    }

}
