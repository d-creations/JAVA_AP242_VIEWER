package ch.dcreations.apviewer.Step3DModel.StepShapes.ConnectedFaceSet;

import ch.dcreations.apviewer.Step3DModel.StepShapes.AP242Code;
import ch.dcreations.apviewer.Step3DModel.StepShapes.Face.Face;
import ch.dcreations.apviewer.Step3DModel.StepShapes.FaceBoundLoop.Edge.Edge;
import ch.dcreations.apviewer.Step3DModel.StepShapes.FaceBoundLoop.FaceBound;
import ch.dcreations.apviewer.Step3DModel.StepShapes.Point.CartasianAxisE;
import ch.dcreations.apviewer.Step3DModel.StepShapes.Point.CartesianPoint;
import ch.dcreations.apviewer.Step3DModel.StepShapes.Point.Point;
import ch.dcreations.apviewer.Step3DModel.StepShapes.StepShapes;
import ch.dcreations.apviewer.Step3DModel.StepShapes.Vertex.Vertex;
import com.sun.javafx.collections.ObservableFloatArrayImpl;
import com.sun.javafx.collections.ObservableIntegerArrayImpl;
import javafx.collections.ObservableFloatArray;
import javafx.collections.ObservableIntegerArray;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.TriangleMesh;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ClosedShell extends ConnectedFaceSet implements StepShapes {

    public ClosedShell(String name, Set<Face> setOfFaces) {
        super(name,setOfFaces);
        this.mesh = createMesh(setOfFaces);
      }

    @Override
    public AP242Code getTyp() {
        return AP242Code.CLOSED_SHELL;
    }


    private Mesh mesh;

    private Mesh createMesh(Set<Face> setOfFaces){
        TriangleMesh mesh = new TriangleMesh();
        ObservableFloatArray vertexArray = new ObservableFloatArrayImpl();
        ObservableIntegerArray facesArray = new ObservableIntegerArrayImpl();
        List<Double> axis = new ArrayList<>();
        for (Face stepDrawFace : setOfFaces) {
            for (FaceBound faceBound : stepDrawFace.getFaceBound()) {
                for (CartesianPoint stepDrawLine : faceBound.getStepDrawTriangleLines()){
                    for (int i = 0; i < 1; i++) {
                        Double x1 = stepDrawLine.getPoint().get(CartasianAxisE.X);
                        Double y1 = stepDrawLine.getPoint().get(CartasianAxisE.Y);
                        Double z1 = stepDrawLine.getPoint().get(CartasianAxisE.Z);
                        axis.add(x1);
                        axis.add(y1);
                        axis.add(z1);
                    }
                }
            }
        }
        int i=0;
        int y=0;
        for (int k = 0;k<axis.size();k = k+9) {
            vertexArray.addAll(axis.get(k).floatValue(),axis.get(k+1).floatValue(),axis.get(k+2).floatValue());
            vertexArray.addAll(axis.get(k+3).floatValue(),axis.get(k+4).floatValue(),axis.get(k+5).floatValue());
            vertexArray.addAll(axis.get(k+6).floatValue(),axis.get(k+7).floatValue(),axis.get(k+8).floatValue());
            //vertexArray.addAll(axis.get(axis.size()-(i+3)).floatValue(),axis.get(axis.size()-(i+2)).floatValue(),axis.get(axis.size()-(i+1)).floatValue());
            // ONE FACE WITH 3 Points
            facesArray.addAll(i, 0);
            facesArray.addAll(i + 1, 0);
            facesArray.addAll(i + 2, 0);
            y= y+6;
            i = i+3;
        }

        mesh.getPoints().addAll(// Verticals
                vertexArray
        );
        mesh.getTexCoords().addAll(//textur
                0f,0f//UV1
        );
        mesh.getFaces().addAll(//Faces
                facesArray
        );
        mesh. getFaceSmoothingGroups().addAll(//smooth
        );

        return mesh;
    }

    public Mesh getMeshView() {
        return this.mesh;
    }
}