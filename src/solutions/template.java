package solutions;

public class template {

    public static volatile template instance = null;

    private template() {}

    public static synchronized template getInstance() {
        if (instance == null) {
            instance = new template();
        }
        return instance;
    }

    public static void main(String[] args) {

    }
}
