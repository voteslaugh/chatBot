package bot;

public class Stat {
    private int count = 0;
    User user;

    public Stat(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void update() {
        count += 1;
    }

    public int getCount() {
        return count;
    }
}
