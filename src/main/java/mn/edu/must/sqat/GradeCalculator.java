package mn.edu.must.sqat;

public class GradeCalculator {

    // 95+ -> +A, 90-94 -> A, 87-89 -> B+, 83-86 -> B, 80-82 -> B-, 77-79 -> C+, 73-76 -> C, 70-72 -> C-, 65-69 -> D+, 60-64 -> D-, 0-59 -> F
    // score нь 0-100 хязгаараас гарвал IllegalArgumentException шиднэ
    public String letterGrade(double score) {
        if (Double.isNaN(score) || score < 0 || score > 100) {
            throw new IllegalArgumentException("Оноо 0-100 хооронд байх ёстой: " + score);
        }
        if (score >= 95) return "+A";
        if (score >=90) return "A";
        if (score >= 87) return "B+";
        if (score >= 83) return "B";
        if (score >= 80) return "B-";
        if (score >= 77) return "C+";
        if (score >= 73) return "C";
        if (score >= 70) return "C-";
        if (score >= 65) return "D+";
        if (score >= 60) return "D-";
        return "F";
    }

    // Ирц(10), лаб+бие даалт(40), сорил1(10), сорил2(10), шалгалт(30)
    public double totalScore(double att, double lab, double quiz1, double quiz2, double exam) {
        check("Ирц", att, 10);
        check("Лаб+бие даалт", lab, 40);
        check("Сорил 1", quiz1, 10);
        check("Сорил 2", quiz2, 10);
        check("Шалгалт", exam, 30);
        return att + lab + quiz1 + quiz2 + exam;
    }

    private static void check(String name, double value, double max) {
        if (Double.isNaN(value) || value < 0 || value > max) {
            throw new IllegalArgumentException(name + " 0-" + (int) max + " хооронд байх ёстой: " + value);
        }
    }
}
