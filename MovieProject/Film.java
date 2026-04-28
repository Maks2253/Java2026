import java.util.ArrayList;
import java.util.List;

public class Film {
    private String title;
    private List<Actor> actors = new ArrayList<>();

    public Film(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void addActor(Actor actor) {
        actors.add(actor);
    }

    @Override
    public String toString() {
        return title;
    }
}