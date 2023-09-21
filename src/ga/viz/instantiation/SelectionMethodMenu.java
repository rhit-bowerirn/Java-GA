package ga.viz.instantiation;

import javax.swing.JComboBox;

import ga.sim.selection.SelectByProportion;
import ga.sim.selection.SelectByRank;
import ga.sim.selection.SelectByRoulette;
import ga.sim.selection.SelectByTournament;
import ga.sim.selection.SelectByTruncation;
import ga.sim.selection.SelectionMethod;

public class SelectionMethodMenu extends JComboBox<SelectionMethod> {

    public SelectionMethodMenu() {
        this.addItem(new SelectByProportion());
        this.addItem(new SelectByRank());
        this.addItem(new SelectByRoulette());
        this.addItem(new SelectByTournament());
        this.addItem(new SelectByTruncation());
    }

    public SelectionMethod selectionMethod() {
        return (SelectionMethod) this.getSelectedItem();
    }
}
