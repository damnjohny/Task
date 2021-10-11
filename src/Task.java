import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class Task {

    public static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

    public static void main(String[] args) {
        Path path = Path.of("src/input.txt");
        List<String> list = new ArrayList<>();
        Map<String, Integer> names = new HashMap<>();
        Map<Integer, Long> dates = new HashMap<>();
        Map<Integer, Integer> proceeds = new HashMap<>();

        try (Stream<String> lines = Files.lines(path);) {
            lines.forEach(list::add);

        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = 0;

        for (String next : list) {

            String[] features = next.split("#");

            String productName = features[0];
            String date = features[1];
            int proceed = Integer.parseInt(features[2]);

            String[] values = features[1].split("\\.");

            GregorianCalendar cal = new GregorianCalendar(Integer.parseInt(values[2]),
                    Integer.parseInt(values[1]) - 1, Integer.parseInt(values[0]));

            long timeMillis = cal.getTimeInMillis();

            if (!names.containsKey(productName)) {
                names.put(productName, i);
                dates.put(i, timeMillis);
                proceeds.put(i, proceed);

            } else {
                int key = names.get(productName);
                if (dates.get(key) < timeMillis) {
                    dates.replace(i, dates.get(key), timeMillis);
                }

                int oldProceed = proceeds.get(key);
                proceeds.replace(key, oldProceed, oldProceed + proceed);
            }
            i++;
        } // end

        int maxProceed = proceeds.get(0);
        int keyProceed = 0;

        for (Map.Entry<Integer, Integer> value : proceeds.entrySet()) {
//            System.out.println(value.getValue());
            if (value.getValue() > maxProceed) {
                maxProceed = value.getValue();
                keyProceed = value.getKey();
            }
        }

        long resultDate = dates.get(keyProceed);
        Date time = new Date(resultDate);
        String resultName = new String("");
        for (Map.Entry<String, Integer> next : names.entrySet()) {
            if (next.getValue() == keyProceed) resultName = next.getKey(); break;
        }

        System.out.println(resultName + " " + formatter.format(time) + " " + maxProceed);

    }
}
