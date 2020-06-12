package intro;

public class SearchMovieTest extends SearchMovieParentTest{
	
	private static String pelicula = "It";
	
    public static void main(String[] args) {
        navegarUrl("https://imdb.com");
        validarHomePage();
        buscarPelicula(pelicula);
        validarBusqueda(pelicula);
        cerrarBrowser();
    }
}
