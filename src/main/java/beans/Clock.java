import java.util.*;
import br.usp.marketvc.beans.*;

public class Clock extends TimerTask {

  public void run() {
    Market.newHour();
  }

}