import java.util.UUID;

public class CreateUUIDs {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(UUID.randomUUID());
        }
    }
}