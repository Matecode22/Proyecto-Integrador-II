package Model;

public class BuscadorCapsulas {
    private CapsulaConocimiento[] listaCapsulas;

    public BuscadorCapsulas(CapsulaConocimiento[] listaCapsulas) {
        this.listaCapsulas = listaCapsulas;
    }

    public void buscarPorTexto(String cadenaBusqueda) {
        for (CapsulaConocimiento capsula : listaCapsulas) {
            if (capsula.getDescripcion().contains(cadenaBusqueda) ||
                    capsula.getLeccionAprendida().contains(cadenaBusqueda)) {
                System.out.println(capsula.toString());
            }
        }
    }

    public void buscarPorHashtag(String hashtagBusqueda) {
        for (CapsulaConocimiento capsula : listaCapsulas) {
            if (capsula.getHashtags().contains(hashtagBusqueda)) {
                System.out.println(capsula.toString());
            }
        }
    }
}
