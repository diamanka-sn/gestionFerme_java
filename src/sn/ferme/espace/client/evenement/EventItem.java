package sn.ferme.espace.client.evenement;

import sn.ferme.espace.client.model.ModelItem;
import java.awt.Component;

public interface EventItem {

    public void itemClick(Component com, ModelItem item);
    public void itemCommand(ModelItem comm);
}
