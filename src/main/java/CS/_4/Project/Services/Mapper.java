package CS._4.Project.Services;

import CS._4.Project.DTOs.*;
import CS._4.Project.Models.*;
import CS._4.Project.Repositories.FirstResponderRepository;
import CS._4.Project.Repositories.IncidentReportRepository;
import CS._4.Project.Repositories.ResourceRepository;
import CS._4.Project.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Mapper {
  private final UserRepository userRepo;
  private final FirstResponderRepository firstResponderRepo;
  private final ResourceRepository resourceRepo;
  private final IncidentReportRepository incidentReportRepo;

  public Mapper(
      UserRepository userRepo, FirstResponderRepository firstResponderRepo,
      ResourceRepository resourceRepo, IncidentReportRepository incidentReportRepo
  ) {
    this.userRepo = userRepo;
    this.firstResponderRepo = firstResponderRepo;
    this.resourceRepo = resourceRepo;
    this.incidentReportRepo = incidentReportRepo;
  }

  public UserDto toUserDto(User user) {
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

  public UserInfoDto toUserInfoDto(User user) {
    UserInfoDto userInfoDto = new UserInfoDto();
    userInfoDto.setFName(user.getFName());
    userInfoDto.setMInit(user.getMInit());
    userInfoDto.setLName(user.getLName());
    userInfoDto.setEmail(user.getEmail());
    return userInfoDto;
  }

  public AlertDto toAlertDto(Alert alert) {
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

  public EmergencyContactDto toEmergencyContactDto(EmergencyContact ec) {
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

  public FirstResponderDto toFirstResponderDto(FirstResponder firstResponder) {
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

  public ResponseTeamDto toResponseTeamDto(ResponseTeam responseTeam) {
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

  public ResponseTeamIncidentDto toResponseTeamIncidentDto(ResponseTeamIncident responseTeamIncident) {
    ResponseTeamIncidentDto rti_Dto = new ResponseTeamIncidentDto();

    // These are from ResponseTeamIncident
    ResponseTeam responseTeam = responseTeamIncident.getRt();
    IncidentReport incidentReport = responseTeamIncident.getIr();

    // These are from incidentReport
    Department department = incidentReport.getD();
    Alert alert = incidentReport.getA();
    List<Long> resourcesIds = incidentReportRepo.findByDIdandAId(department.getId(), alert.getId());
    List<String> resources = resourceRepo.getResourceNamesById(resourcesIds);


    // Populate the DTO
    String TeamLeader = responseTeam.getTeamLead().getU().getFName() + " " + responseTeam.getTeamLead().getU().getLName();
    rti_Dto.setRtTeamLeader(TeamLeader);
    rti_Dto.setDepName(department.getName());
    rti_Dto.setIncidentReport(incidentReport.getIrDescription());
    rti_Dto.setAlertType(alert.getAlertType());
    rti_Dto.setSeverity(alert.getSeverity());
    rti_Dto.setReportTime(incidentReport.getIrDate());
    rti_Dto.setResources(resources);

    return rti_Dto;
  }

  public List<UserDto> toUserDtoList(List<User> users) {
    return users.stream().map(this::toUserDto).toList();
  }

  public List<UserInfoDto> toUserInfoDtoList(List<User> users) {
    return users.stream().map(this::toUserInfoDto).toList();
  }

  public List<AlertDto> toAlertDtoList(List<Alert> alerts) {
    return alerts.stream().map(this::toAlertDto).toList();
  }

  public List<EmergencyContactDto> toEC_DtoList(List<EmergencyContact> ecs) {
    return ecs.stream().map(this::toEmergencyContactDto).toList();
  }

  public List<FirstResponderDto> toFR_DtoList(List<FirstResponder> firstResponders) {
    return firstResponders.stream().map(this::toFirstResponderDto).toList();
  }

  public List<ResponseTeamDto> toResponseTeamDtoList(List<ResponseTeam> responseTeams) {
    return responseTeams.stream().map(this::toResponseTeamDto).toList();
  }
}
