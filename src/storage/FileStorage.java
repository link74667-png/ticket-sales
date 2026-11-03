package storage;

import model.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

// Работа с файлом: формат строки  id;тип;владелец
public class FileStorage {
    private final String path;

    public FileStorage(String path) { this.path = path; }

    public void save(List<Ticket> tickets) throws IOException {
        try (PrintWriter out = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(path), StandardCharsets.UTF_8))) {
            for (Ticket t : tickets) {
                out.println(t.getId() + ";" + t.getType() + ";" + t.getOwner());
            }
        }
    }

    public List<Ticket> load(double basePrice) throws IOException {
        List<Ticket> result = new ArrayList<>();
        File file = new File(path);
        if (!file.exists()) return result; // первый запуск
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] p = line.split(";");
                int id = Integer.parseInt(p[0]);
                if (p[1].equals("VIP")) result.add(new VipTicket(id, p[2], basePrice));
                else result.add(new StandardTicket(id, p[2], basePrice));
            }
        }
        return result;
    }
}
