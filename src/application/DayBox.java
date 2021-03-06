package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class DayBox extends Group {
	
	private int dayNum = 0;
	private ImageView plusImage;
	private Label dayLabel;
	private Rectangle calBox;
	private int labelYPos = 0;
	private DoubleProperty widthProperty;
	private DoubleProperty heightProperty;
	
	
	public DayBox(int day) {
		dayNum = day;
		calBox = new Rectangle(0, 0, 0,0);
		widthProperty = calBox.widthProperty();
		heightProperty = calBox.heightProperty();
		plusImage = new ImageView();
		plusImage.setImage(new Image("application\\PlusIcon.png"));
		plusImage.setFitWidth(30);
		plusImage.setFitHeight(30);
		deselectDayBox();
		calBox.setStroke(Color.GRAY);
		calBox.setStrokeWidth(.2);
		calBox.setFill(Color.WHITE);
		dayLabel = new Label(Integer.toString(dayNum));
		dayLabel.setLayoutX(5);
		dayLabel.setLayoutY(5);
		dayLabel.setFont(Font.font(16));
		getChildren().addAll(calBox,dayLabel, plusImage);
		EventTime.setDay(day);
		calBox.layoutBoundsProperty().addListener((obs, oldval, newval) -> {
			plusImage.setX(newval.getWidth() - (plusImage.getFitWidth() + 10));
			plusImage.setY(plusImage.getFitHeight() - 20);
		});
	}
	
	// Width getter
	public final double getWidthProperty() {
		return widthProperty.get();
	}
	
	// Width setter
	public void setWidthProperty(double width) {
		widthProperty.set(width);
	}
	
	// Width property getter
	public DoubleProperty widthProperty() {
		return widthProperty;
	}

	//Height getter
	public final double getHeightProperty() {
		return heightProperty.get();
	}
	
	// Height setter
	public void setHeightProperty(double height) {
		heightProperty.set(height);
	}
	
	// Height property getter
	public DoubleProperty heightProperty() {
		return heightProperty;
	}
	
	public void getEvents() {
		Collection <Event> eventList = EventDB.getListAt(EventTime.getDate());
		Iterator <Event> iterator = eventList.iterator();
		while(iterator.hasNext()) {
			Event tempEvent = iterator.next();
			drawEventLabel(tempEvent.getEventTitle());
		}
		
	}
	
	private void drawEventLabel(String eventTitle) {
		labelYPos += 25;
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		Label eventLabel = new Label(eventTitle);
		double labelWidth = fontLoader.computeStringWidth(eventLabel.getText(), eventLabel.getFont());
		eventLabel.setLayoutY(labelYPos);
		//eventLabel.setLayoutX((calBox.getWidth() / 2) - (labelWidth) / 2);
	
		getChildren().add(eventLabel);
		
		
	}
	
	public boolean isInImageBounds(double X, double Y) { // TODO: Ask if there is better implementation possible (Call add event from within daybox)
		if(plusImage.getBoundsInParent().contains(X, Y)) {
			return true;
		}
		return false;
	}
	
	public void selectDayBox() {
		plusImage.setVisible(true);
		calBox.setStrokeWidth(2);
	}
	
	public void deselectDayBox() {
		plusImage.setVisible(false);
		calBox.setStrokeWidth(.2);
	}
	
	public void drawEvents(ArrayList<Event> eventList) {
		String eventTitle = "";
		Event tempEvent;
		for(int i = 0; i < eventList.size(); i++) {
			tempEvent = eventList.get(i);
			drawEventLabel(tempEvent.getEventTitle());
		}
	}
	
	public int getDay() {
		return dayNum;
	}
}
