package servlet;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserServlet extends HttpServlet {

    private final Map<String, String> users = new LinkedHashMap<>();

    @Override
    public void init(){
        log(getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String remoteAddr = req.getRemoteAddr();
        String agent = req.getHeader("User-Agent");
        users.put(remoteAddr, agent);
        log("Request from " + remoteAddr);

        PrintWriter writer = resp.getWriter();
        writer.write("<head>\n" +
                "<style>\n" +
                "table, th, td {\n" +
                "  border: 1px solid black;\n" +
                "  padding: 5px;\n" +
                "}\n" +
                "table {\n" +
                "  border-spacing: 15px;\n" +
                "}\n" +
                "td {\n" +
                "  text-align: center;\n" +
                "}" +
                "</style>\n" +
                "</head>");

        writer.write("<table style=\"width:100%\">");
        writer.write("" +
                "<tr>\n" +
                "  <th>Ip-address</th>\n" +
                "  <th>User-Agent</th>\n" +
                "</tr>");

        for(Map.Entry<String, String> record : users.entrySet()){
            String key = record.getKey();
            String value = record.getValue();
            if(key.equals(remoteAddr)){
                writer.write(String.format(
                        "<tr>\n" +
                                "  <td><b>%s</b></td>\n" +
                                "  <td><b>%s</b></td>\n" +
                                "</tr>", key, value));
            }else {
                writer.write(String.format(
                        "<tr>\n" +
                                "   <td>%s</td>\n" +
                                "   <td>%s</td>\n" +
                                "</tr>", key, value));
            }
        }
        writer.write("</table>");
    }

    @Override
    public void destroy() {
        log(getServletName() + " destroyed");
    }
}