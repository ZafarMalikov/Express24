package user;

import common.BaseEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class User extends BaseEntity<UUID> {
   private String name;
   private String surname;
   private double balance;
   private String password;
   private String phoneNumber;
   private Role roel=Role.USER;
   private LocalDate employmentDate;
   private double salary;
   private UUID restaurantId;
   @Builder
   public User(UUID id, LocalDateTime created, LocalDateTime modified, User createdBy, User modifiedBy, String name, String surname, double balance, String password, String phoneNumber, Role roel, LocalDate employmentDate, double salary, UUID restaurantId) {
      super(id, created, modified, createdBy, modifiedBy);
      this.name = name;
      this.surname = surname;
      this.balance = balance;
      this.password = password;
      this.phoneNumber = phoneNumber;
      this.roel = roel;
      this.employmentDate = employmentDate;
      this.salary = salary;
      this.restaurantId = restaurantId;
   }
}
