import comp1110.ass2.Marrakech;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class D_isRugVaildtest {
    String gS= "Pc03104iPy01004iPp01704iPr06205iA36SBc13c04r08c03p18p18n00c13p04r08n00n00n00n00r13c14p06r02r02c18c18y19y19r12r12r07n00n00c19c19r18y07n00y04y04y15y15r18p12y18y18c01y06y16y16c12p15r15r15";

    @Test
    public void testRugLength() {
        assertFalse(Marrakech.isRugValid(gS, "c01234"));
        assertTrue(Marrakech.isRugValid(gS, "c200102"));
    }

    @Test
    public void testColor() {
        assertFalse(Marrakech.isRugValid(gS, "z012345"));
        assertTrue(Marrakech.isRugValid(gS, "c200102"));
    }

    @Test
    public void testID() {
        assertFalse(Marrakech.isRugValid(gS, "ca00102"));
        assertTrue(Marrakech.isRugValid(gS, "c200102"));
    }

    @Test
    public void testRugCoordinates() {
        assertFalse(Marrakech.isRugValid(gS, "c200107"));
        assertTrue(Marrakech.isRugValid(gS, "c200102"));
    }

    @Test
    public void testRugIdUniqueness() {
        assertFalse(Marrakech.isRugValid(gS, "c012345"));
        assertTrue(Marrakech.isRugValid(gS, "c200102"));
    }

}
