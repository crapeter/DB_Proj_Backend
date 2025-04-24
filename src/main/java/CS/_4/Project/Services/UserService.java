package CS._4.Project.Services;

import CS._4.Project.DTOs.*;
import CS._4.Project.Models.Caller;
import CS._4.Project.Models.CallerId;
import CS._4.Project.Models.FirstResponder;
import CS._4.Project.Models.User;
import CS._4.Project.Repositories.CallerRepository;
import CS._4.Project.Repositories.FirstResponderRepository;
import CS._4.Project.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class UserService {
  private final UserRepository userRepo;
  private final FirstResponderRepository firstResponderRepo;
  private final CallerRepository callerRepo;
  private final Mapper mapper;

  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  private static final String FACTORY_ALGORITHM = System.getenv("FACTORY_ALGORITHM");
  private static final String SPEC_ALGORITHM = System.getenv("SPEC_ALGORITHM");
  private static final String CIPHER_ALGORITHM = System.getenv("CIPHER_ALGORITHM");

  public UserService(
      UserRepository userRepo, FirstResponderRepository firstResponderRepo,
      CallerRepository callerRepo, Mapper mapper
  ) {
    this.userRepo = userRepo;
    this.firstResponderRepo = firstResponderRepo;
    this.callerRepo = callerRepo;
    this.mapper = mapper;
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
    return mapper.toUserDtoList(userRepo.findAll());
  }

  public Iterable<UserInfoDto> getUsersInfo() {
    return mapper.toUserInfoDtoList(userRepo.findAll());
  }

  public ResponseEntity<String> correctPassword(String email, String password) {
    User user = userRepo.findByEmail(email);
    if (user == null) {
      return ResponseEntity.status(404).body("User not found");
    }

    EncryptionString storedData = new EncryptionString(user.getPassword(), user.getSalt());

    if (validatePassword(password, storedData)) {
      FirstResponder firstResponder = firstResponderRepo.findByUId(user.getId());
      List<Long> callerUserIds = callerRepo.findByUId(user.getId());
      if (firstResponder == null && !callerUserIds.contains(user.getId())) {
        CallerId callerId = new CallerId();
        callerId.setUId(user.getId());
        callerId.setCId(callerRepo.findMaxCId() + 1);

        Caller caller = new Caller();
        caller.setId(callerId);
        caller.setU(user);

        callerRepo.save(caller);
      }
      return ResponseEntity.ok("Password is correct");
    } else {
      return ResponseEntity.status(401).body("Incorrect password");
    }
  }

  private Boolean validatePassword(String password, EncryptionString storedData) {
    try {
      byte[] encrypted = Base64.getDecoder().decode(storedData.encryptedData());

      String[] saltAndIv = storedData.salt().split(":");
      byte[] salt = Base64.getDecoder().decode(saltAndIv[0]);
      byte[] iv = Base64.getDecoder().decode(saltAndIv[1]);

      byte[] encryptedPassword = genCodes(password, salt, iv);
      logger.debug("Encrypted Password: {}", Arrays.toString(encryptedPassword));
      logger.debug("Stored Encrypted Password: {}", Arrays.toString(encrypted));
      return Arrays.equals(encrypted, encryptedPassword);
    } catch (Exception e) {
      logger.error("Error validating password", e);
    }
    return false;
  }

  private EncryptionString encrypt(String password) {
    try {
      byte[] salt = new byte[16];
      SecureRandom secureRandom = new SecureRandom();
      secureRandom.nextBytes(salt);

      byte[] iv = new byte[16];
      secureRandom.nextBytes(iv);

      byte[] encrypted = genCodes(password, salt, iv);

      String encryptedBase64 = Base64.getEncoder().encodeToString(encrypted);
      String saltAndIvBase64 = Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(iv);

      return new EncryptionString(encryptedBase64, saltAndIvBase64);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private byte[] genCodes(String password, byte[] salt, byte[] iv) {
    try {
      SecretKeyFactory factory = SecretKeyFactory.getInstance(FACTORY_ALGORITHM);
      KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
      SecretKey secretKey = factory.generateSecret(spec);
      SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), SPEC_ALGORITHM);

      IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
      Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

      return cipher.doFinal(password.getBytes());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
