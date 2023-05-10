// Handles environment filters

package environment; 
import edu.nmsu.cs.legendofnine.GamePanel;
import java.awt.Graphics2D;
public class EnvironmentManager {

    GamePanel gp;
    Lighting lighting;
    public EnvironmentManager(GamePanel gp) {
        this.gp = gp;
    }
    public void setup() {

        lighting = new Lighting(gp, 700);
    }

    public void draw(Graphics2D g2) {

        lighting.draw(g2);
    }
}