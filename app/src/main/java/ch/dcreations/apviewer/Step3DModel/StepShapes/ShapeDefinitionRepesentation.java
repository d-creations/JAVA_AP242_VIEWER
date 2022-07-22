package ch.dcreations.apviewer.Step3DModel.StepShapes;

import javafx.scene.control.TreeItem;

public class ShapeDefinitionRepesentation extends StepShapes{

    protected ProductDefinitionShape PRODUCT_DEFINITION_SHAPE;
    protected AdvancedBrepShapeRepresentation ADVANCED_BREP_SHAPE_REPRESENTATION;

    public ShapeDefinitionRepesentation(StepShapes productDefinitionShape, StepShapes advancedBrepShapeRepresentation,int lineNumber) {
        super(AP242Code.PRODUCT_DEFINITION_SHAPE,"",lineNumber);
        this.ADVANCED_BREP_SHAPE_REPRESENTATION = (AdvancedBrepShapeRepresentation) advancedBrepShapeRepresentation;
        this.PRODUCT_DEFINITION_SHAPE = (ProductDefinitionShape) productDefinitionShape;
    }

    @Override
    public TreeItem<StepShapes> getTreeItem() {
        TreeItem<StepShapes> treeItem = new TreeItem<>(this);
        treeItem.getChildren().add(PRODUCT_DEFINITION_SHAPE.getTreeItem());
        treeItem.getChildren().add(ADVANCED_BREP_SHAPE_REPRESENTATION.getTreeItem());
        return treeItem;
    }
}
