import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet(name = "countStatistics", urlPatterns = "/")
public class Main extends HttpServlet {
    static StatisticsCollector protocolStatistics = new StatisticsCollector();

    public static void main(String[] args) throws IOException {
        runPandaProcess();
    }

    private static void runPandaProcess() throws IOException {
        Runtime rt = Runtime.getRuntime();
        String[] commands = {"bigPandaExec.exe"};
        Process proc = null;

        proc = rt.exec(commands);

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(proc.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(proc.getErrorStream()));


        JsonParser jp = new JsonParser();
        String line = null;
        while (true) {
            if (!((line = stdInput.readLine()) != null)) break;
            try {
                ProtocolClass pc = null;
                pc = jp.parseJson(line);
                protocolStatistics.updateStatistics (pc.getEventType(), pc.getData());
            } catch (ParseException e) {
                // Do nothing, invalid file
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        resp.getWriter().write("Event Types:");
        for (String et : protocolStatistics.getEventTypesCounters()) {
            resp.getWriter().write(et);
        }

        resp.getWriter().write("Data:");
        for (String data : protocolStatistics.getDataCounters()) {
            resp.getWriter().write(data);
        }
    }
}
