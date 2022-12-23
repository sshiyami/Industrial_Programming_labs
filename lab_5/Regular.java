import java.io.*;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Regular {
    public static void main(String[] args) throws Exception
    {
        FileReader input = new FileReader("input.txt");
        FileWriter output = new FileWriter("output.txt");
        Scanner inputScanner = new Scanner(input);
        while (inputScanner.hasNextLine()){
            String line = inputScanner.nextLine();
            if (Pattern.matches("((\\d|[a-f]|[A-F]){2}\\:){5}(\\d|[a-f]|[A-F]){2}", line)) {
                output.write(line + "        yes" + "\n");
            } else {
                output.write(line + "        no" + "\n");
            }
        }
        input.close();
        output.close();
    }

}