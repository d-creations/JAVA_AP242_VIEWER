package ch.dcreations.apviewer.Step3DModel;

import ch.dcreations.apviewer.Step3DModel.StepShapes.ConnectedFaceSet.ClosedShell;
import ch.dcreations.apviewer.Step3DModel.StepShapes.StepShapes;
import ch.dcreations.apviewer.Step3DModel.StepShapes.StepText;
import javafx.scene.control.TreeItem;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import java.io.*;
import java.util.*;

public class Step3DModel {

    String data = "";
    String name ;
    List<Paint> colorsList = new ArrayList<>();
    int colorIndex = 0;
    Map<Integer,String> dataMap = new TreeMap<>();
    List<MeshView> shapes2DMesh = new ArrayList<>();

    List<StepShapes> stepShapesList = new ArrayList<>();

    AP242Decoder ap242Decoder;
    String header = "";

    public Step3DModel(String name) {
        this.name = name;
    }

    public void parseTheFile(Reader reader) throws IOException {
        colorsList.add(Color.BLUE);
        colorsList.add(Color.BLUEVIOLET);
        colorsList.add(Color.GREEN);
        colorsList.add(Color.GREENYELLOW);
        StringBuilder readData = new StringBuilder();
        int charValue;
        System.out.println("The Drawing From Damian ");
        do {
            charValue = reader.read(); // read next char from file
            readData.append((char)charValue);
        } while (charValue != -1); // reached end of file ?
        int DATApos = readData.toString().indexOf("DATA");
        header = readData.substring(0,DATApos);
        data = readData.substring(DATApos,readData.toString().indexOf("ENDSEC",DATApos));
        data = data.replaceAll("\n","").replaceAll("\r","").replaceAll("DATA;","");
        for (String line : data.split(";")){
            line = line.replaceAll(" ","");
            String[] lineSep = line.split("=");
            String number = lineSep[0].replaceAll("#","");
            if(number.length()>0) {
                int numberCode = Integer.parseInt(number);
                dataMap.put(numberCode, lineSep[1]);
            }
        }
        ap242Decoder = new AP242Decoder(dataMap);
        ap242Decoder.decode();
        for ( ClosedShell closedShell : ap242Decoder.getDrawingShells()){
            drawClosedShell(closedShell);
        }
        for (StepShapes stepShapes : ap242Decoder.getStepShapes()){
            stepShapesList.add(stepShapes);
        }

    }


    private void drawClosedShell(ClosedShell closedShell) {
        MeshView meshView = new MeshView(closedShell.getMeshView());
        shapes2DMesh.add(meshView);
    }

    private Paint getColor() {
        if(colorIndex >= colorsList.size()){
            colorIndex = 0;
        }
        Paint paint = colorsList.get(colorIndex);
        colorIndex++;
        return paint;
    }


    public List<MeshView> getShapes2DMesh() {
        return Collections.unmodifiableList(shapes2DMesh);
    }

    public TreeItem<StepShapes> getStepShapes() {
        TreeItem<StepShapes> treeItem = new TreeItem<>(new StepText("Model"));
        for (StepShapes stepShapes : stepShapesList){
            treeItem.getChildren().add(stepShapes.getTreeItem());
        }

        return treeItem;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
