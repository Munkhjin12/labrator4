package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GradeCalculatorTest {

    // ---------- letterGrade: ердийн утгууд ----------

    @Test
    @DisplayName("95 оноо A дүн байх ёстой")
    void ninetyFiveIsA() {
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(95.0);
        assertEquals("A", grade);
    }

    @Test
    @DisplayName("85, 75, 65 оноо тус тус B, C, D дүн байх ёстой")
    void typicalMiddleGrades() {
        GradeCalculator calc = new GradeCalculator();
        assertAll(
            () -> assertEquals("B", calc.letterGrade(85.0)),
            () -> assertEquals("C", calc.letterGrade(75.0)),
            () -> assertEquals("D", calc.letterGrade(65.0))
        );
    }

    @Test
    @DisplayName("30 оноо F дүн байх ёстой")
    void thirtyIsF() {
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(30.0);
        assertEquals("F", grade);
    }

    // ---------- letterGrade: хязгаарын утгууд ----------

    @Test
    @DisplayName("90 оноо яг A дүн байх ёстой (хязгаарын тохиолдол)")
    void ninetyIsExactlyA() {
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(90.0);
        assertEquals("A", grade);
    }

    @Test
    @DisplayName("89.99 оноо B дүн байх ёстой (A-ийн хязгаарын доод талд)")
    void eightyNineNinetyNineIsB() {
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(89.99);
        assertEquals("B", grade);
    }

    @Test
    @DisplayName("60 оноо яг D, 59.99 оноо F байх ёстой (тэнцэх хязгаар)")
    void sixtyIsDAndFiftyNineNinetyNineIsF() {
        GradeCalculator calc = new GradeCalculator();
        assertAll(
            () -> assertEquals("D", calc.letterGrade(60.0)),
            () -> assertEquals("F", calc.letterGrade(59.99))
        );
    }

    @Test
    @DisplayName("0 ба 100 оноо хүчинтэй хязгаар: F ба A")
    void zeroAndHundredAreValid() {
        GradeCalculator calc = new GradeCalculator();
        assertAll(
            () -> assertEquals("F", calc.letterGrade(0.0)),
            () -> assertEquals("A", calc.letterGrade(100.0))
        );
    }

    // ---------- letterGrade: буруу оролт ----------

    @Test
    @DisplayName("-1 ба 101 оноонд IllegalArgumentException шидэх ёстой")
    void letterGradeOutOfRangeThrows() {
        GradeCalculator calc = new GradeCalculator();
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1)),
            () -> assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101)),
            () -> assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(100.01)),
            () -> assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(Double.NaN))
        );
    }

    // ---------- totalScore ----------

    @Test
    @DisplayName("Дээд оноонуудын нийлбэр 100 байх ёстой")
    void totalScoreMaxIsHundred() {
        GradeCalculator calc = new GradeCalculator();
        double total = calc.totalScore(10, 40, 10, 10, 30);
        assertEquals(100.0, total, 1e-9);
    }

    @Test
    @DisplayName("Бүх оноо 0 бол нийлбэр 0 байх ёстой")
    void totalScoreAllZero() {
        GradeCalculator calc = new GradeCalculator();
        double total = calc.totalScore(0, 0, 0, 0, 0);
        assertEquals(0.0, total, 1e-9);
    }

    @Test
    @DisplayName("totalScore: сөрөг утгад IllegalArgumentException шидэх ёстой (att = -5)")
    void totalScoreNegativeThrows() {
        GradeCalculator calc = new GradeCalculator();
        assertThrows(IllegalArgumentException.class, () -> calc.totalScore(-5, 40, 10, 10, 30));
    }

    @Test
    @DisplayName("totalScore: дээд хязгаараас хэтэрсэн утгад exception шидэх ёстой (lab = 41)")
    void totalScoreOverMaxThrows() {
        GradeCalculator calc = new GradeCalculator();
        assertThrows(IllegalArgumentException.class, () -> calc.totalScore(10, 41, 10, 10, 30));
    }

    // ---------- Parameterized ----------

    @ParameterizedTest(name = "letterGrade({0}) = {1}")
    @DisplayName("letterGrade хязгаарын утгууд (parameterized)")
    @CsvSource({"100,A", "95,A", "90,A", "89.99,B", "85,B", "80,B", "79.99,C", "75,C", "70,C", "69.99,D", "65,D", "60,D", "59.99,F", "30,F", "0,F"})
    void letterGradeBoundaries(double score, String expected) {
        assertEquals(expected, new GradeCalculator().letterGrade(score));
    }

    @ParameterizedTest(name = "totalScore({0},{1},{2},{3},{4}) = {5}")
    @DisplayName("totalScore нийлбэр (parameterized)")
    @CsvSource({
        "10,40,10,10,30,100",
        "0,0,0,0,0,0",
        "8,32,7,8,25,80",
        "5,20,5,5,15,50",
        "10,0,0,0,0,10"
    })
    void totalScoreSums(double att, double lab, double q1, double q2, double exam, double expected) {
        assertEquals(expected, new GradeCalculator().totalScore(att, lab, q1, q2, exam), 1e-9);
    }

}
