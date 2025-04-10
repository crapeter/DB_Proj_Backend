package CS._4.Project.Services;

import CS._4.Project.DTOs.*;
import CS._4.Project.Models.*;

import java.util.List;

public class Mapper {
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

  public static List<UserDto> toUserDtoList(List<User> users) {
    return users.stream().map(Mapper::toUserDto).toList();
  }

  public static List<UserInfoDto> toUserInfoDtoList(List<User> users) {
    return users.stream().map(Mapper::toUserInfoDto).toList();
  }
}
