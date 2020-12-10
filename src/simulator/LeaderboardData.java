package simulator;


import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LeaderboardData {
    public static void append(String s){
        try
        {
            String filename= "leaderboard.txt";
            FileWriter fw = new FileWriter(filename,true);
            fw.write(s + "\n");
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }


    public static String read(String fileName) throws IOException {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }
}
