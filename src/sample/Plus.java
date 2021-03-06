package sample;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Plus extends Application {
    private final Line l1, l2, l3, l4;
    private final AnchorPane root;
    private final ImageView star;
    private final ImageView colorwheel;

    public Plus(AnchorPane root, Stage stage,double center_x,double center_y) throws Exception {

        l1=new Line();
        l1.setEndX(100);
        l1.setLayoutX(90+center_x);
        l1.setLayoutY(192+center_y);
        l1.setStartX(12);
        l1.setStroke(Color.YELLOW);
        l1.setStrokeLineCap(StrokeLineCap.ROUND);
        l1.setStrokeWidth(25);

        l2=new Line();
        l2.setEndX(100);
        l2.setLayoutX(146+center_x);
        l2.setLayoutY(139+center_y);
        l2.setRotate(90.0f);
        l2.setStartX(12);
        l2.setStroke(Color.AQUA);
        l2.setStrokeLineCap(StrokeLineCap.ROUND);
        l2.setStrokeWidth(25);

        l3=new Line();
        l3.setEndX(100);
        l3.setLayoutX(203+center_x);
        l3.setLayoutY(192+center_y);
        l3.setStartX(12);
        l3.setStroke(Color.DEEPPINK);
        l3.setStrokeLineCap(StrokeLineCap.ROUND);
        l3.setStrokeWidth(25);

        l4=new Line();
        l4.setEndX(100);
        l4.setLayoutX(146+center_x);
        l4.setLayoutY(246+center_y);
        l4.setStartX(12);
        l4.setRotate(90);
        l4.setStroke(Color.BLUEVIOLET);
        l4.setStrokeLineCap(StrokeLineCap.ROUND);
        l4.setStrokeWidth(25);

        this.star = new ImageView();
        this.star.setImage(new Image(getClass().getResourceAsStream("/asset/star.png")));
        this.star.setX(160);
        this.star.setY(center_y+30);

        this.colorwheel = new ImageView();
        this.colorwheel.setImage(new Image(getClass().getResourceAsStream("/asset/colourwheel.png")));
        this.colorwheel.setX(162);
        this.colorwheel.setY(l4.getLayoutY()+90);

        this.root=root;
        this.start(stage);

    }

    public void semiIntersect(javafx.scene.shape.Circle circle, int color, Shape r1, Stage stage){
        Shape intersect=Shape.intersect(circle,r1);
        if(intersect.getBoundsInLocal().getWidth()!=-1){
            System.out.println("Ball collidied with PINK");
            stage.close();
        }
    }

    public void collisionCheckCircle(javafx.scene.shape.Circle circle, Stage stage){

        int color;

        if(circle.getFill()==Color.AQUA){
            color=1;
        }
        else if(circle.getFill()==Color.BLUEVIOLET){
            color=2;
        }
        else if(circle.getFill()==Color.DEEPPINK){
            color=3;
        }
        else{
            color=4;
        }

        //r1=aqua=color1

        if(color==4){
            semiIntersect(circle,color,l2,stage);
            semiIntersect(circle,color,l3,stage);
            semiIntersect(circle,color,l4,stage);
        }
        else if(color==3){
            semiIntersect(circle,color,l2,stage);
            semiIntersect(circle,color,l1,stage);
            semiIntersect(circle,color,l4,stage);
        }
        else if(color==2){
            semiIntersect(circle,color,l2,stage);
            semiIntersect(circle,color,l1,stage);
            semiIntersect(circle,color,l3,stage);
        }

        else{
            semiIntersect(circle,color,l3,stage);
            semiIntersect(circle,color,l1,stage);
            semiIntersect(circle,color,l4,stage);

        }
    }

    public void moveCircle(double distance){
        this.star.setY(this.star.getY()+distance);
        this.colorwheel.setY(this.colorwheel.getY()+distance);
        l1.setLayoutY(l1.getLayoutY()+distance);
        l2.setLayoutY(l2.getLayoutY()+distance);
        l3.setLayoutY(l3.getLayoutY()+distance);
        l4.setLayoutY(l4.getLayoutY()+distance);
    }
//
    public double getStar(){
        return  l4.getLayoutY()-195;
    }
    public ImageView getColorWheelImg(){
        return colorwheel;
    }

    public double getColorWheel(){
        return l4.getLayoutY()+120;
    }
    public ImageView getStarImg(){
        return star;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group g=new Group();
        g.getChildren().addAll(l2,l4, l1, l3);
        root.getChildren().add(g);
        root.getChildren().add(star);
        root.getChildren().add(colorwheel);

        RotateTransition rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(-3600);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setDuration(Duration.millis(25000));
        rotate.setNode(g);
        rotate.play();
    }

}
