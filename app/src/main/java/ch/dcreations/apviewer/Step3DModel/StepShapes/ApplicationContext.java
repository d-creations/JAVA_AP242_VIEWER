package ch.dcreations.apviewer.Step3DModel.StepShapes;

import ch.dcreations.apviewer.Step3DModel.StepShapes.AP242Code;
import ch.dcreations.apviewer.Step3DModel.StepShapes.StepShapes;
import javafx.scene.control.TreeItem;

public class ApplicationContext implements StepShapes {
    public ApplicationContext(String name) {
    }

    @Override
    public AP242Code getTyp() {
        return AP242Code.APPLICATION_CONTEXT;
    }
    @Override
    public TreeItem<StepShapes> getTreeItem() {
        TreeItem<StepShapes> treeItem = new TreeItem<>(this);
        return treeItem;
    }

    @Override
    public String toString() {
        return AP242Code.APPLICATION_CONTEXT.toString() + "name";
    }
}
