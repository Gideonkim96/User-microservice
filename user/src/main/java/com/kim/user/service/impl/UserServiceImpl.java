package com.kim.user.service.impl;

import com.kim.user.dto.LoginDto;
import com.kim.user.dto.UserDto;
import com.kim.user.entity.User;
import com.kim.user.exception.InvalidCredentialsException;
import com.kim.user.exception.UserAlreadyExistsException;
import com.kim.user.mapper.UserMapper;
import com.kim.user.repository.UserRepository;
import com.kim.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Optional;

import static com.kim.user.contants.UserConstants.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public void signup(UserDto userDto) {
        byte[] salt = generateRandomSalt();
        String hashedPassword = hashPassword(userDto.getPassword(), salt);
        User user = UserMapper.mapToUser(userDto, new User());
        user.setPassword(hashedPassword);
        user.setSalt(Base64.getEncoder().encodeToString(salt));
        Optional<User> optionalUser = userRepository.findByUserName(userDto.getUserName());
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("User already registered with given username "
                    + userDto.getUserName());
        }
        userRepository.save(user);
    }

        private byte[] generateRandomSalt() {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[SALT_LENGTH];
            random.nextBytes(salt);
            return salt;
        }

        private String hashPassword(String password, byte[] salt) {
            try {
                SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
                KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
                SecretKey key = factory.generateSecret(spec);
                byte[] hashedPassword = key.getEncoded();
                return Base64.getEncoder().encodeToString(hashedPassword);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                throw new RuntimeException("Error hashing password", e);
            }
        }
    @Override
    public User signin(String userName, String password) {
        Optional<User> optionalUser = userRepository.findByUserName(userName);

        if (!optionalUser.isPresent()) {
            throw new InvalidCredentialsException("Invalid username or password");
        }
        User user = optionalUser.get();

        // 1. Retrieve the stored salt
        String encodedSalt = String.valueOf(user.getSalt());
        byte[] salt = Base64.getDecoder().decode(encodedSalt);

        // 2. Hash the provided password using the stored salt
        String hashedPassword = hashPassword(user.getPassword(), salt);

        // 3. Compare the hashed passwords
        if (!hashedPassword.equals(user.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String s) {
        return null;
    }

//    public boolean signin(String userName, String password) {
//        Optional<User> optionalUser = userRepository.findByUserName(userName);
//        if (!optionalUser.isPresent()) {
//            throw new InvalidCredentialsException("Invalid username or password");
//        }
//        User user = optionalUser.get();
//
//        // Hash the entered password using the stored salt
//        String enteredHashedPassword = hashPassword(password, Base64.getDecoder().decode(user.getSalt()));
//
//        // Compare the hashed entered password with the stored hashed password
//        return enteredHashedPassword.equals(user.getPassword());
//    }
}
