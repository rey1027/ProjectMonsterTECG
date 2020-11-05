import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *  * Contiene lo necesario para utilizar Json y Jackson
 *  * @author RACHEL
 *  * @version 1.0
 */
public class Json {
    /**
     * Crear un atributo ObjectMapper estatico
     */
    private static final ObjectMapper objectMapper = getDefaultObjectMapper();

    /**
     * Método donde se creo un ObjectMapper que funciona como el patron de diseño singleton
     * @return ObjectMapper que se utilizará despues
     */
    private static ObjectMapper getDefaultObjectMapper() {
        return new ObjectMapper();
    }

    /**
     *Metodo para poder parsear un objeto
     * @param jsonSource utilizada para obtener la informacion de un archivo .json
     * @return lo que se solicito leer del archivo
     * @throws JsonProcessingException propia del parse de Json
     */
    public static JsonNode parse(String jsonSource) throws JsonProcessingException {
        return objectMapper.readTree(jsonSource);
    }




}
