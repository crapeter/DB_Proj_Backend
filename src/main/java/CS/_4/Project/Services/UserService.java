package CS._4.Project.Services;

import CS._4.Project.DTOs.*;
import CS._4.Project.Models.User;
import CS._4.Project.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

@Service
public class UserService {
  private final UserRepository userRepo;

  private static final String ALGORITHM = System.getenv("ALGORITHM");
  private static final String SECRET_KEY = System.getenv("SECRET_KEY");
  private static final String INIT_VECTOR = System.getenv("INIT_VECTOR");

  public UserService(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  public ResponseEntity<String> registerUser(User user) {
    EncryptionString encryptedPassword = encrypt(user.getPassword());
    if (encryptedPassword == null) {
      return ResponseEntity.status(500).body("Error encrypting password");
    }

    try {
      user.setPassword(encryptedPassword.encryptedData());
      user.setSalt(encryptedPassword.salt());
      userRepo.save(user);
      return ResponseEntity.ok("User registered successfully");
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Error encrypting password");
    }
  }

  public Iterable<UserDto> getAllUsers() {
    return Mapper.toUserDtoList(userRepo.findAll());
  }

  public Iterable<UserInfoDto> getUsersInfo() {
    return Mapper.toUserInfoDtoList(userRepo.findAll());
  }

  private EncryptionString encrypt(String password) {
    try {
      byte[] salt = new byte[16];
      SecureRandom secureRandom = new SecureRandom();
      secureRandom.nextBytes(salt);

      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), salt, 65536, 256);
      SecretKey secretKey = factory.generateSecret(spec);
      SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");

      IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes());

      Cipher cipher = Cipher.getInstance(ALGORITHM);
      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
      byte[] encrypted = cipher.doFinal(password.getBytes());

      String encryptedBase64 = Base64.getEncoder().encodeToString(encrypted);
      String saltBase64 = Base64.getEncoder().encodeToString(salt);

      return new EncryptionString(encryptedBase64, saltBase64);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
