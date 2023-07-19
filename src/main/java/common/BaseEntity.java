package common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseEntity<ID> {
   private ID id;
   private LocalDateTime created;
   private LocalDateTime modified;
   private User createdBy;
   private User modifiedBy;



}
