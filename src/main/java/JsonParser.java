
/*
import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.JSONObject;
*/

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser {
    public ProtocolClass parseJson(String line) throws ParseException {
        Object obj = new JSONParser().parse(line);
        JSONObject jo = (JSONObject) obj;

        return new ProtocolClass((String) jo.get("event_type"),
                                    (String) jo.get("data"));
    }
}
