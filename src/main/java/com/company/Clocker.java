package com.company;

import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Clocker {
    public static String urlParametersBody = "&tzOffset=0&tzName=Europe%2FLondon&gpsLong=&gpsLat=&gpsAccuracy=&clocktype=&comment=";
    public static String loginURL = ;

    private final String username;
    private final String password;

    public Clocker(String username, String password ) {
        this.username = username;
        this.password = password;
    }

    public static void main(String[] args) {
       if(args.length != 2) {
           System.out.println("Usage: Clocker <username> <password>");
           System.exit(1);
       }
        Clocker main = new Clocker(args[0], args[1]);
        main.clockIn();
    }

    public void clockIn() {
//        com.company.RandomTimer randomTimer = new com.company.RandomTimer();
//        randomTimer.waitToClockin();

        try {
            String urlParameters = buildBody(this.username, this.password);
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;

            URL url = new URL(loginURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("accept", "application/json, text/javascript, */*; q=0.01");
            conn.setRequestProperty("accept-encoding", "gzip, deflate, br");
            conn.setRequestProperty("accept-language", "en-IE,en-GB;q=0.9,en;q=0.8,ga-IE;q=0.7,ga;q=0.6,en-US;q=0.5");
            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            conn.setUseCaches(false);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.write(postData);

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    String buildBody(String username, String password) throws UnsupportedEncodingException {

        String result = "username=" +
                username +
                "&sessionToken=" +
                (getConvertedPassword(password)) +
                urlParametersBody;

        return result;
    }

    /*  Format of password for login is password + 16 characters, and then the lot
        converted to HEX
     */
    String getConvertedPassword(String password) {
        return asciiToHex(password + randomPadding());
    }

    String randomPadding() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 16;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    String asciiToHex(String asciiStr) {
        byte[] bytes = asciiStr.getBytes(StandardCharsets.UTF_8);

        BigInteger bigInt = new BigInteger(bytes);
        String hexString = bigInt.toString(16);

        return hexString;
    }
}
