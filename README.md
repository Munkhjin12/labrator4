# Лаборатори №4 — Нэгжийн тест, JUnit 5

**Оюутны нэр:** Л.Мөнхжин  
**Код:** B242270094

## Хувилбарууд

`java -version` гаралт:

```
openjdk version "21.0.10" 2026-01-20
OpenJDK Runtime Environment Homebrew (build 21.0.10)
OpenJDK 64-Bit Server VM Homebrew (build 21.0.10, mixed mode, sharing)
```

`mvn -version` гаралт:

```
Apache Maven 3.9.12 (848fbb4bf2d427b72bdb2471c22fced7ebd9a7a1)
Maven home: /opt/homebrew/Cellar/maven/3.9.12/libexec
Java version: 21.0.10, vendor: Homebrew, runtime: /opt/homebrew/Cellar/openjdk@21/21.0.10/libexec/openjdk.jdk/Contents/Home
Default locale: en_MN, platform encoding: UTF-8
OS name: "mac os x", version: "26.3.1", arch: "aarch64", family: "mac"
```
## Тестийн тоо

- Тестийн метод: **14** (12 `@Test` + 2 `@ParameterizedTest`).
- Амжилттай ажилласан тохиолдол: **38** (12 энгийн + 21 `letterGrade` + 5 `totalScore`).
- `results/mvn-test.txt`: `Tests run: 38, Failures: 0, Errors: 0, Skipped: 0`, `BUILD SUCCESS`.
- Одоогийн `GradeCalculator`-ийн шатлалд 95 ба түүнээс дээш оноо `+A`; 90 оноо `A` болно.

## Мутацийн үр дүн

`letterGrade` доторх `score >= 90`-ийг `score > 90` болгон туршихад **2 тохиолдол унасан**: `ninetyIsExactlyA` болон `letterGradeBoundaries`-ийн `90,A` мөр. Хоёуланд нь `expected: <A> but was: <B+>` гэж гарсан бөгөөд нотолгоо `results/mvn-test-mutant.txt` файлд хадгалагдсан. Эх кодын нөхцөлийг `>= 90` болгон сэргээсний дараа бүх тест амжилттай болсон.

## Дүгнэлт

Энэ лабораторид `GradeCalculator`-ийн үсгэн дүн болон нийлбэр онооны үйлдлүүдэд JUnit 5 тест бичиж шалгасан.
Нийт 14 тестийн метод ажиллуулсан бөгөөд parameterized тестүүдийн мөрүүдийг тусад нь тоолоход `Tests run` 38 болсон.
`results/mvn-test.txt` тайланд 38 тест алдаагүй дуусаж, `BUILD SUCCESS` гарсан.
Мутацийн туршилтаар `score >= 90` нөхцөлийг `score > 90` болгоход 90 онооны хоёр шалгалт `A`-ийн оронд `B+` гарсныг илрүүлсэн.
Хамгийн сонирхолтой нь `ninetyIsExactlyA` тест: яг заагийн оноог шалгаснаар нөхцөлийн нэг тэмдэг өөрчлөгдөхөд гарсан алдааг барьсан.
`assertThrows`-оор `letterGrade`-ийн хүрээнээс гадуурх болон `totalScore`-ийн сөрөг, дээд хэмжээнээс хэтэрсэн оролтуудыг шалгасан.
Даалгаврын жишээнд 95 оноог `A` гэж өгсөн боловч энэ төслийн одоогийн ангиллаар `+A` буцдаг болгосон ба энэ нь манай хичээлийн  үсгэн үнэлгээний бүтэцтэй тааруулсан болно.

## Ажиллуулах

```
mkdir -p results && mvn test 2>&1 | tee results/mvn-test.txt
```

Мутацийн тайланг үүсгэхдээ эхлээд `GradeCalculator.java` дахь `score >= 90`-ийг түр `score > 90` болгон өөрчилж, дараах командыг ажиллуулсан. Үүний дараа нөхцөлийг буцааж `>= 90` болгон, дээрх командыг дахин ажиллуулж ногоон тайланг шинэчилсэн.

```
mvn test 2>&1 | tee results/mvn-test-mutant.txt
```