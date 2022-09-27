package bot;

public class Bot {
    public void checkCommand(Commands command) {
        if (command == Commands.START) {
            System.out.println("Hello, I'm bot!");
        }
    }
}
