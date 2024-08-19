import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;
import java.util.Scanner;

class CurrencyConverter {

    public static double getExchangeRate(String baseCurrency, String targetCurrency) throws Exception {
        String apiKey = "f47cdf7c7e8eb782fd2d8aa7";  // Your actual API key
        String urlStr = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", apiKey, baseCurrency);

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();

        JSONObject jsonResponse = new JSONObject(content.toString());
        JSONObject conversionRates = jsonResponse.getJSONObject("conversion_rates");

        // Use getDouble or cast to double if necessary
        double exchangeRate = conversionRates.getDouble(targetCurrency);

        return exchangeRate;
    }

    public static double convertCurrency(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the base currency (e.g., USD, EUR): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter the target currency (e.g., INR, EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter the amount to convert: ");
        double amount = scanner.nextDouble();

        try {
            double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
            System.out.println("Exchange Rate (" + baseCurrency + " to " + targetCurrency + "): " + exchangeRate);

            double convertedAmount = convertCurrency(amount, exchangeRate);
            System.out.printf("Converted Amount: %.2f %s%n", convertedAmount, targetCurrency);
        } catch (Exception e) {
            System.out.println("Error occurred while fetching exchange rates: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
