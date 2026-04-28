import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MovieDatabase {
    private List<Film> films = new ArrayList<>();
    private List<Actor> actors = new ArrayList<>();

    public List<Film> getFilms() {
        return films;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void addFilm(Film film) {
        films.add(film);
    }

    public void addActor(Actor actor) {
        actors.add(actor);
    }

    // 1) Актори, які не зіграли жодного фільму
    public List<Actor> actorsWithoutFilms() {
        return actors.stream()
                .filter(a -> a.getFilms().isEmpty())
                .collect(Collectors.toList());
    }

    // 2) Актори, з якими грав заданий актор
    public List<Actor> coActors(Actor actor) {
        return actor.getFilms().stream()
                .flatMap(f -> f.getActors().stream())
                .filter(a -> !a.equals(actor))
                .distinct()
                .collect(Collectors.toList());
    }

    // 3) Фільм з найбільшою кількістю акторів
    public Optional<Film> filmWithMostActors() {
        return films.stream()
                .max((f1, f2) -> Integer.compare(
                        f1.getActors().size(),
                        f2.getActors().size()
                ));
    }
}