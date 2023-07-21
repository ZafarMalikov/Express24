import com.github.javafaker.Faker;
import restaurant.Restaurant;
import ui.MainUi;
import user.User;
import user.UserRepository;

import java.util.Scanner;
import java.util.UUID;

public class main {
        public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        MainUi mainUi = new MainUi();

        mainUi.start();

    }
}
