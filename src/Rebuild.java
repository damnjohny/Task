import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Rebuild {

    public static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

    public static Map<String, Product> products = new HashMap<>();

    public static Path path = Path.of("src/input.txt");


    public static void main(String[] args) throws ParseException, IOException {
        List<String> stringList = Files.readAllLines(path);

        for (String next : stringList) {

            String[] split = next.split("#");

            String productName = split[0];
            Date productDate = formatter.parse(split[1]);
            long productProceed = Long.parseLong(split[2]);


            if (!products.containsKey(productName)) {
                products.put(productName, new Product(productDate, productProceed));
            } else {
               products.get(productName).setProceed(products.get(productName).getProceed() + productProceed);

               if (products.get(productName).getDate().getTime() < productDate.getTime()) {
                   products.get(productName).setDate(productDate);
               }
            }
        }

        long maxProceed = 0;
        String key = "";
        for (Map.Entry<String, Product> next : products.entrySet()) {
            if (next.getValue().getProceed() > maxProceed) {
                maxProceed = next.getValue().getProceed();
                key = next.getKey();
            }
        }

        System.out.println(key + " " + formatter.format(products.get(key).getDate()) + " " + maxProceed);
    }

}
