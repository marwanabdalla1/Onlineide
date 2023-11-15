


package edu.tum.ase.darkmode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication; import org.springframework.web.bind.annotation.RequestMapping; import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication @RestController
public class DarkmodeApplication {
  boolean darkMode = false;

      // modify this to implement a button which toggles the dark mode

public static void main(String[] args) { SpringApplication.run(DarkmodeApplication.class, args);
}
@RequestMapping(path = "/dark-mode/toggle", method = RequestMethod.GET) public String toggleDarkMode() {
    // TODO: implement dark mode toggle




    
    // wait for three seconds before toggling
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
   darkMode = !darkMode;

   return "darkmode is " + (darkMode ? "enabled" : "disabled");

}


@RequestMapping(path = "/dark-mode/status", method = RequestMethod.GET) public String getDarkModeStatus() {
  
  return "darkmode currently is " + (darkMode ? "enabled" : "disabled");
}
  // TODO: implement endpoint which returns dark mode status
}
