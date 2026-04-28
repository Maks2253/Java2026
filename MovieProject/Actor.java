import java.util.ArrayList;
import java.util.List;

public class Actor {
    private String name;
    private List<Film> films = new ArrayList<>();

    public Actor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void addFilm(Film film) {
        films.add(film);
    }

    @Override
    public String toString() {
        return name;
    }
}