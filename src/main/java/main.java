import com.github.javafaker.Faker;
import restaurant.Restaurant;
import user.User;
import user.UserRepository;

import java.util.UUID;

public class main {
    public static void main(String[] args) {
        Faker faker = new Faker();

        UserRepository instance = UserRepository.getInstance();
        instance.add(new User(UUID.randomUUID(),faker.date()));


    }
}
