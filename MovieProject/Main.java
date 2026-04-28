public class Main {
    public static void main(String[] args) {

        MovieDatabase db = new MovieDatabase();

        Actor a1 = new Actor("Tom Hanks");
        Actor a2 = new Actor("Leonardo DiCaprio");
        Actor a3 = new Actor("Brad Pitt");
        Actor a4 = new Actor("Unused Actor");

        Film f1 = new Film("Film A");
        Film f2 = new Film("Film B");

        f1.addActor(a1);
        f1.addActor(a2);

        f2.addActor(a2);
        f2.addActor(a3);

        a1.addFilm(f1);
        a2.addFilm(f1);
        a2.addFilm(f2);
        a3.addFilm(f2);

        db.addActor(a1);
        db.addActor(a2);
        db.addActor(a3);
        db.addActor(a4);

        db.addFilm(f1);
        db.addFilm(f2);

        // 1
        System.out.println("Actors without films: " + db.actorsWithoutFilms());

        // 2
        System.out.println("Co-actors of Leo: " + db.coActors(a2));

        // 3
        System.out.println("Film with most actors: " +
                db.filmWithMostActors().orElse(null));
    }
}
