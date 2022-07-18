package ch.dcreations.apviewer.Step3DModel.StepShapes;


import javafx.scene.control.TreeItem;
import javafx.scene.shape.Shape3D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Pcurve implements StepShapes {

    protected final List<Map<String, String>> preferencesMapList = new ArrayList<>();

    protected String name;
    protected StepShapes basis;
    protected StepShapes curve;

    public Pcurve(String name, StepShapes basis, StepShapes curve) {
        this.name = name;
        this.basis = basis;
        this.curve = curve;
    }

    @Override
    public AP242Code getTyp() {
        return AP242Code.PCURVE;
    }

    @Override
    public TreeItem<StepShapes> getTreeItem() {
        TreeItem<StepShapes> treeItem = new TreeItem<>(this);
        treeItem.getChildren().add(basis.getTreeItem());
        treeItem.getChildren().add(curve.getTreeItem());
        return treeItem;
    }

    @Override
    public String toString() {
        return AP242Code.PCURVE + " "+ name;
    }

    @Override
    public List<Map<String, String>> getPreferencesList() {
        return Collections.unmodifiableList(this.preferencesMapList);
    }

    @Override
    public boolean setPreference(Map<String, String> preference) {
        return false;
    }


    @Override
    public Shape3D getShape() {
        return null;
    }
}
