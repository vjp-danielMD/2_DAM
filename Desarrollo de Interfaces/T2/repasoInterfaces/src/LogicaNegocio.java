
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author daniel
 */
public class LogicaNegocio {

    List<Raton> ratones;

    public LogicaNegocio() {
        this.ratones = new ArrayList<>(Arrays.asList(
                new Raton("Logitech", "G502 HERO", "2.6"),
                new Raton("Razer", "DeathAdder V2", "3.0"),
                new Raton("Corsair", "Dark Core RGB Pro", "2.8"),
                new Raton("SteelSeries", "Rival 3", "2.4"),
                new Raton("HP", "Omen Vector", "2.2"),
                new Raton("ASUS", "ROG Gladius III", "3.2"),
                new Raton("Microsoft", "Classic Intellimouse", "2.0"),
                new Raton("Logitech", "MX Master 3", "2.9"),
                new Raton("Razer", "Naga Pro", "3.5"),
                new Raton("Corsair", "Ironclaw RGB Wireless", "3.1"),
                new Raton("Logitech", "G PRO X SUPERLIGHT 2", "3.8"),
                new Raton("Razer", "Viper V3 Pro", "3.6"),
                new Raton("Pulsar", "X2 Wireless", "3.4"),
                new Raton("Glorious", "Model O 2 Wireless", "3.3"),
                new Raton("SteelSeries", "Aerox 5 Wireless", "3.7"),
                new Raton("Cooler Master", "MM712", "2.9"),
                new Raton("Endgame Gear", "XM2we", "3.1"),
                new Raton("ASUS", "TUF Gaming M4 Air", "2.5"),
                new Raton("HyperX", "Pulsefire Haste 2", "3.0"),
                new Raton("Fnatic", "Bolt Wireless", "3.2"),
                new Raton("Logitech", "G305 LIGHTSPEED", "2.7"),
                new Raton("Razer", "Basilisk V3 Pro", "3.6"),
                new Raton("SteelSeries", "Prime Wireless", "3.3"),
                new Raton("Corsair", "SABRE RGB PRO Wireless", "3.4"),
                new Raton("ASUS", "TUF Gaming M3 Gen II", "2.5"),
                new Raton("HyperX", "Pulsefire Dart", "3.0"),
                new Raton("Cooler Master", "MM731", "2.9"),
                new Raton("Glorious", "Model D Wireless", "3.2"),
                new Raton("Pulsar", "Xlite V2 Wireless", "3.5"),
                new Raton("Endgame Gear", "XM1r", "3.1")
        ));
    }

    public List<Raton> getRatones() {
        return ratones;
    }

    public void agregarRaton(Raton r) {
        ratones.add(r);
    }

    public void eliminarRaton(int index) {
        if (index >= 0 && index < ratones.size()) {
            ratones.remove(index);
        }
    }

}
