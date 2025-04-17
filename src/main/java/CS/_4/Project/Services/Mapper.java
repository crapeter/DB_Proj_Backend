package CS._4.Project.Services;

import CS._4.Project.DTOs.*;
import CS._4.Project.Models.*;
import CS._4.Project.Repositories.FirstResponderRepository;
import CS._4.Project.Repositories.ResourceRepository;
import CS._4.Project.Repositories.UserRepository;

import java.util.List;
import java.util.Optional;

public class Mapper {
  private static UserRepository userRepo;
  private static FirstResponderRepository firstResponderRepo;
  private static ResourceRepository resourceRepo;

  public Mapper(UserRepository userRepo, FirstResponderRepository firstResponderRepo, ResourceRepository resourceRepo) {
    Mapper.userRepo = userRepo;
    Mapper.firstResponderRepo = firstResponderRepo;
    Mapper.resourceRepo = resourceRepo;
  }

  public static UserDto toUserDto(User user) {
    UserDto userDto = new UserDto();
    userDto.setFName(user.getFName());
    userDto.setMInit(user.getMInit());
    userDto.setLName(user.getLName());
    userDto.setEmail(user.getEmail());
    userDto.setHomeAddr(user.getHomeAddr());
    userDto.setPhoneNum(user.getPhoneNum());
    userDto.setSex(user.getSex());
    userDto.setDob(user.getDob());
    return userDto;
  }

  public static UserInfoDto toUserInfoDto(User user) {
    UserInfoDto userInfoDto = new UserInfoDto();
    userInfoDto.setFName(user.getFName());
    userInfoDto.setMInit(user.getMInit());
    userInfoDto.setLName(user.getLName());
    userInfoDto.setEmail(user.getEmail());
    return userInfoDto;
  }

  public static AlertDto toAlertDto(Alert alert) {
    Optional<User> optUser = userRepo.findById(alert.getC().getId().getUId());
    if (optUser.isEmpty()) {
      return null;
    }

    AlertDto alertDto = new AlertDto();
    alertDto.setAlertType(alert.getAlertType());
    alertDto.setSeverity(alert.getSeverity());
    alertDto.setLocation(alert.getLocation());
    alertDto.setCallerEmail(optUser.get().getEmail());
    return alertDto;
  }

  public static EmergencyContactDto toEmergencyContactDto(EmergencyContact ec) {
    Optional<User> optUser = userRepo.findById(ec.getU().getId());
    if (optUser.isEmpty()) {
      return null;
    }

    EmergencyContactDto ecDto = new EmergencyContactDto();
    ecDto.setDependentEmail(optUser.get().getEmail());
    ecDto.setFName(ec.getFName());
    ecDto.setLName(ec.getLName());
    ecDto.setRelationship(ec.getRelationship());
    return ecDto;
  }

  public static FirstResponderDto toFirstResponderDto(FirstResponder firstResponder) {
    User user = firstResponder.getU();
    Department department = firstResponder.getDep();
    if (user == null || department == null) {
      return null;
    }

    FirstResponderDto firstResponderDto = new FirstResponderDto();
    firstResponderDto.setFName(user.getFName());
    firstResponderDto.setLName(user.getLName());
    firstResponderDto.setEmail(user.getEmail());
    firstResponderDto.setSex(user.getSex());
    firstResponderDto.setDepName(department.getName());
    return firstResponderDto;
  }

  public static ResponseTeamDto toResponseTeamDto(ResponseTeam responseTeam) {
    FirstResponder teamLead = responseTeam.getTeamLead();
    Department department = responseTeam.getDep();
    if (teamLead == null || department == null) {
      return null;
    }

    ResponseTeamDto responseTeamDto = new ResponseTeamDto();
    responseTeamDto.setDepName(department.getName());
    responseTeamDto.setTeamLeadName(teamLead.getU().getFName() + " " + teamLead.getU().getLName());
    return responseTeamDto;
  }

  public static List<UserDto> toUserDtoList(List<User> users) {
    return users.stream().map(Mapper::toUserDto).toList();
  }

  public static List<UserInfoDto> toUserInfoDtoList(List<User> users) {
    return users.stream().map(Mapper::toUserInfoDto).toList();
  }

  public static List<AlertDto> toAlertDtoList(List<Alert> alerts) {
    return alerts.stream().map(Mapper::toAlertDto).toList();
  }

  public static List<EmergencyContactDto> toEC_DtoList(List<EmergencyContact> ecs) {
    return ecs.stream().map(Mapper::toEmergencyContactDto).toList();
  }

  public static List<FirstResponderDto> toFR_DtoList(List<FirstResponder> firstResponders) {
    return firstResponders.stream().map(Mapper::toFirstResponderDto).toList();
  }

  public static List<ResponseTeamDto> toResponseTeamDtoList(List<ResponseTeam> responseTeams) {
    return responseTeams.stream().map(Mapper::toResponseTeamDto).toList();
  }
}
