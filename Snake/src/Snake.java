import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<SnakeSection> sections;
    private boolean isAlive;
    private SnakeDirection direction;

    public Snake(int x, int y) {
        this.sections = new ArrayList<SnakeSection>();
        this.sections.add(new SnakeSection(x, y));
        this.isAlive = true;
    }

    public List<SnakeSection> getSections() {
        return this.sections;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public SnakeDirection getDirection() {
        return this.direction;
    }

    public int getX() {
        return this.sections.get(0).getX();
    }

    public int getY() {
        return this.sections.get(0).getY();
    }

    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }

    public void move() {

        if (!isAlive) return;

        if (direction == SnakeDirection.UP) move(0, -1);
        if (direction == SnakeDirection.RIGHT) move(1, 0);
        if (direction == SnakeDirection.DOWN) move(0, 1);
        if (direction == SnakeDirection.LEFT) move(-1, 0);
        
    }

    public void move(int dx, int dy) {
        SnakeSection head = sections.get(0);
        head = new SnakeSection(head.getX() + dx, head.getY() + dy);

        checkBorders(head);
        if (!isAlive) return;

        checkBody(head);
        if (!isAlive) return;

        Mouse mouse = Room.game.getMouse();
        if (head.getX() == mouse.getX() && head.getY() == mouse.getY()) {
            sections.add(0, head);
            Room.game.eatMouse();
        } else {
            sections.add(0, head);
            sections.remove(sections.size() - 1);
        }

    }

    public void checkBody(SnakeSection head) {
        if (sections.contains(head)) isAlive = false;
    }

    public void checkBorders(SnakeSection head) {
        if (head.getX() < 0 || head.getX() >= Room.game.getWidth() || head.getY() < 0 || head.getY() >= Room.game.getHeight()) isAlive = false;
    }
}