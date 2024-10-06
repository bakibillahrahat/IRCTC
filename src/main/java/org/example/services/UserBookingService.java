package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.User;
import org.example.utils.UserServiceUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;


public class UserBookingService {
    private User user;
    private List<User> userList;
    private ObjectMapper objectMapper = new ObjectMapper();

    private final String USERS_PATH = "/Users/md.bakibillahrahat/IdeaProjects/IRCTC/src/main/java/org/example/services/../localDb/users.json";


    public  UserBookingService(User user) throws IOException {
        this.user = user;
        loadUserListFromFile();
    }
    public  UserBookingService() throws IOException {
        loadUserListFromFile();
    }

    private void loadUserListFromFile() throws IOException {
        userList = objectMapper.readValue(new File(USERS_PATH), new TypeReference<List<User>>() {});
    }

    public boolean loginUser() {
            Optional<User> foundUser = userList.stream().filter(user1 -> {
                return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
            }).findFirst();
            return foundUser.isPresent();
    }

    public boolean signUp(User user1) throws IOException {
        try{
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;

        }catch (Exception ex){
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        try{
            File usersFile = new File(USERS_PATH);
            objectMapper.writeValue(usersFile, userList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void fetchBooking(String ticketId) {
        // fetch user ticket boking

    }

    public Boolean cancelBooking(String ticketId) {

        return Boolean.FALSE;
    }
}
