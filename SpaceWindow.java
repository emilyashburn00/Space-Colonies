// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Emily Ashburn
package spacecolonies;

import java.awt.Color;
import CS2114.Button;
import CS2114.CircleShape;
import CS2114.Shape;
import CS2114.TextShape;
import CS2114.Window;
import CS2114.WindowSide;
import list.AList;

/**
 * Class that creates a GUI to colonize planets with people
 * 
 * @author Emily Ashburn
 * @version 2019.11.07
 */
public class SpaceWindow {
    private Window window;
    private ColonyCalculator colonyCalculator;
    private Button accept;
    private Button reject;
    private static final int START = 100;
    private static final int CIRCLE_SIZE = 50;
    private static final int PLANET_SIZE = 70;
    private static final Color PLANET_1 = new Color(125, 30, 80);
    private static final Color PLANET_1_COVER = new Color(250, 60, 160);
    private static final Color PLANET_2 = new Color(60, 120, 90);
    private static final Color PLANET_2_COVER = new Color(120, 240, 180);
    private static final Color PLANET_3 = new Color(100, 100, 125);
    private static final Color PLANET_3_COVER = new Color(200, 200, 250);
    private TextShape data = new TextShape(SpaceWindow.START - 40, 20, "");
    private AList<CircleShape> peopleShapes;
    private Shape[] planetShapes;
    private Shape[] coverShapes;
    private TextShape p1;
    private TextShape p2;
    private TextShape p3;

    public SpaceWindow(ColonyCalculator calc) {
        this.colonyCalculator = calc;
        window = new Window("Space Colony Placement");

        accept = new Button("Accept");
        reject = new Button("Reject");
        accept.onClick(this, "clickedAccept");
        reject.onClick(this, "clickedReject");

        window.addButton(accept, WindowSide.SOUTH);
        window.addButton(reject, WindowSide.SOUTH);
        planetShapes = new Shape[4];
        peopleShapes = new AList<CircleShape>();
        coverShapes = new Shape[4];
        this.addPlanets();
        this.planetsUpdate();
        this.populate();
        finished();

    }

    /**
     * Adds planets to the window and information
     */
    public void addPlanets() {
        Planet one = ColonyCalculator.getPlanets()[1];
        Planet two = ColonyCalculator.getPlanets()[2];
        Planet three = ColonyCalculator.getPlanets()[3];
        Shape planet1 = new Shape(120, 180, PLANET_SIZE, PLANET_SIZE, PLANET_1);
        p1 = new TextShape(105, 180 + PLANET_SIZE, one.getName() + ", "
                + one.getPopulationSize() + "/" + one.getCapacity());
        TextShape t1 = new TextShape(115, 180 + PLANET_SIZE + p1.getHeight(),
                one.getSkills().toString());
        Shape planet2 = new Shape(270, 180, PLANET_SIZE, PLANET_SIZE, PLANET_2);
        p2 = new TextShape( + PLANET_SIZE + 90, 180 + PLANET_SIZE,
                two.getName() + ", " + two.getPopulationSize() + "/"
                        + two.getCapacity());
        TextShape t2 = new TextShape(95 + START + PLANET_SIZE,
                180 + PLANET_SIZE + p2.getHeight(), two.getSkills().toString());
        Shape planet3 = new Shape(420, 180, PLANET_SIZE, PLANET_SIZE, PLANET_3);
        p3 = new TextShape(START + (2 * PLANET_SIZE) + 170, 180 + PLANET_SIZE,
                three.getName() + ", " + three.getPopulationSize() + "/"
                        + three.getCapacity());
        TextShape t3 = new TextShape(START + (2 * PLANET_SIZE) + 173,
                180 + PLANET_SIZE + p3.getHeight(),
                three.getSkills().toString());
        window.addShape(planet1);
        window.addShape(planet2);
        window.addShape(planet3);
        p1.setBackgroundColor(Color.white);
        p2.setBackgroundColor(Color.white);
        p3.setBackgroundColor(Color.white);
        t1.setBackgroundColor(Color.white);
        t2.setBackgroundColor(Color.white);
        t3.setBackgroundColor(Color.white);
        data.setBackgroundColor(Color.white);
        data.setText(colonyCalculator.getQueue().getFront().toString());
        window.addShape(p1);
        window.addShape(p2);
        window.addShape(p3);
        window.addShape(t1);
        window.addShape(t2);
        window.addShape(t3);
        window.addShape(data);
        planetShapes[1] = planet1;
        planetShapes[2] = planet2;
        planetShapes[3] = planet3;
    }

    /**
     * returns the color of the preferred planet
     * 
     * @param person
     * @return
     */
    public Color matchPersonColor(Person person) {
        if (person == null) {
            return Color.BLUE;
        }
        int index = colonyCalculator.getPlanetIndex(person.getPlanetName());
        if (index == 0) {
            return Color.LIGHT_GRAY;
        }
        return coverShapes[index].getForegroundColor();
    }

    /**
     * populates the window with circles representing applicants
     */
    public void populate() {
        Object[] objTemp = colonyCalculator.getQueue().toArray();
        Person[] temp = new Person[objTemp.length];
        for (int j = 0; j < temp.length; j++) {
            temp[j] = (Person) objTemp[j];
        }

        for (int i = 0; i < temp.length; i++) {
            CircleShape persons = new CircleShape(i * (CIRCLE_SIZE), 80,
                    CIRCLE_SIZE, matchPersonColor(temp[i]));
            peopleShapes.add(persons);
            window.addShape(persons);
        }
    }

    /**
     * Removes the first shape and moves circles
     * 
     * @param button
     */
    public void clickedAccept(Button button) {
        if (colonyCalculator.accept()) {
            window.removeShape(peopleShapes.remove(0));
            moveCircles();
            planetsUpdate();
            finished();
        }
    }

    /**
     * moves all circles to left
     */
    private void moveCircles() {

        for (int i = 0; i < peopleShapes.getLength(); i++) {
            peopleShapes.getEntry(i).setX((i - 1) * (CIRCLE_SIZE));
        }
        window.repaint();
    }

    /**
     * update the planets' graphs
     */
    private void planetsUpdate() {
        Planet planet = ColonyCalculator.getPlanets()[1];
        Shape shape1 = new Shape(120, 180, PLANET_SIZE,
                PLANET_SIZE
                        - (((int) PLANET_SIZE * planet.getPopulationSize() + 1)
                                / planet.getCapacity()),
                PLANET_1_COVER);
        window.addShape(shape1);
        window.moveToFront(planetShapes[1]);
        window.moveToFront(shape1);
        p1.setText(planet.getName() + ", " + planet.getPopulationSize() + "/"
                + planet.getCapacity());
        window.repaint();

        planet = ColonyCalculator.getPlanets()[2];
        Shape shape2 = new Shape(270, 180, PLANET_SIZE,
                PLANET_SIZE
                        - (((int) PLANET_SIZE * planet.getPopulationSize() + 1)
                                / planet.getCapacity()),
                PLANET_2_COVER);
        window.addShape(shape2);
        window.moveToFront(planetShapes[2]);
        window.moveToFront(shape2);
        p2.setText(planet.getName() + ", " + planet.getPopulationSize() + "/"
                + planet.getCapacity());
        window.repaint();

        planet = ColonyCalculator.getPlanets()[3];
        Shape shape3 = new Shape(420, 180, PLANET_SIZE,
                PLANET_SIZE
                        - (((int) PLANET_SIZE * planet.getPopulationSize() + 1)
                                / planet.getCapacity()),
                PLANET_3_COVER);
        window.moveToFront(planetShapes[3]);
        window.addShape(shape3);
        window.moveToFront(shape3);
        p3.setText(planet.getName() + ", " + planet.getPopulationSize() + "/"
                + planet.getCapacity());
        
        window.repaint();
        coverShapes[1] = shape1;
        coverShapes[2] = shape2;
        coverShapes[3] = shape3;

    }

    /**
     * if clicked reject, will reject the person
     * 
     * @param button
     */
    public void clickedReject(Button button) {
        window.removeShape(peopleShapes.remove(0));
        moveCircles();
        colonyCalculator.reject();
        finished();
    }

    /**
     * disables or enables the button, or shows a message if finished
     */
    private void finished() {
        if (colonyCalculator.getQueue().isEmpty()) {
            window.removeAllShapes();
            data.setText("All applicants processed!");
            window.addShape(data);
            reject.disable();
            accept.disable();
        } else if (colonyCalculator.getPlanetForPerson(
                colonyCalculator.getQueue().getFront()) == null) {
            data.setText(colonyCalculator.getQueue().getFront().toString());
            accept.disable();
        } else {
            data.setText(colonyCalculator.getQueue().getFront().toString());
            accept.enable();
        }
        window.repaint();
    }

}