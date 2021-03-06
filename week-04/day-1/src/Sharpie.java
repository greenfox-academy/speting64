/*Create Sharpie class
We should know about each sharpie their color (which should be a string), width (which will be a floating point number), inkAmount (another floating point number)
        When creating one, we need to specify the color and the width
        Every sharpie is created with a default 100 as inkAmount
        We can use() the sharpie objects
        which decreases inkAmount*/

public class Sharpie {
    String color;
    float width;
    float inkAmount;

    public Sharpie(String color, float width){
        this.color = color;
        this.width = width;
    }

    public void use(){
        inkAmount = 100;
        inkAmount -= width;
    }
}
class Color{
    public static void main(String[] args) {
        Sharpie Blue = new Sharpie("Blue",(float)61.56);
        Blue.use();
        System.out.println(Blue.inkAmount);
        System.out.println(Blue.width);
    }
}
