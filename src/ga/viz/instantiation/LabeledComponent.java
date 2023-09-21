package ga.viz.instantiation;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LabeledComponent extends JPanel {
    public LabeledComponent(String label, JComponent component, boolean inLine) {
        if (!inLine) {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        }
        this.add(new JLabel(label));
        this.add(component);
    }

}
