package comp1110.ass2;

public class RugAbreviated {
    public char colour;
    public int ID;
    public RugAbreviated() {
        this.colour = 'n';
        this.ID = 0;
    }

    public void initialiseFromString(String rugString) {
        char[] rugStringChars = rugString.toCharArray();
        colour = rugStringChars[0];
        ID = (Character.getNumericValue(rugStringChars[1]) * 10) + (Character.getNumericValue(rugStringChars[2]));
    }
}
