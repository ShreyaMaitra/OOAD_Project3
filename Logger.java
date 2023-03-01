import java.io.FileWriter;
import java.io.IOException;


public class Logger extends PublisherObserver {
   private String filename;
   private String s="";
   private int day;

   
   public void writeToFile() 
   {
    String filename = "Logger-" + day + ".txt";
    try (FileWriter writer = new FileWriter(filename)) {
        writer.write("Simulation day " + day + ":\n");
        writer.write(s + "\n");

    } catch (IOException e) {
        e.printStackTrace();
    }
}

public void getDate(int day) {
    this.day = day;
   }

   public void setDescription(String s) {
    this.s = this.s+s+"\n";
   }
    
}
