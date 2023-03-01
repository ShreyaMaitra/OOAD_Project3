import java.io.FileWriter;
import java.io.IOException;

public class Tracker extends PublisherObserver {
    
    private String t="";

    public void setTrackerDescription(String t) {
        this.t = this.t+t+"\n";
       }

       public void writeToFile() 
   {
    String filename = "Tracker.txt";
    try (FileWriter writer = new FileWriter(filename)) {
        writer.write(t + "\n");

    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
