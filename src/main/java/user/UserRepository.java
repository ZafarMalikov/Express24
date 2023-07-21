package user;

import common.BaseRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRepository extends BaseRepository<User,UUID> {
    private static final UserRepository repository=new UserRepository();

    public Optional<User>findByPhoneNumber(String phoneNumber){
        return entities.stream().
                filter(user -> user.getPhoneNumber()
                        .equals(phoneNumber))
                        .findFirst();
    }

    public static UserRepository getInstance(){
        return repository;
    }
}
