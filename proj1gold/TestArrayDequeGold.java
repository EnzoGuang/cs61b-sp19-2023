import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDequeGold {
    /** Test the StudentArrayDeque.addFirst()
     * @source StudentArrayDequeLauncher.java;
     */
    @Test
    public void testStudentAddFirst() {
        StudentArrayDeque<Integer> stu = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        String message = "";
        for (int i = 0; i < 1000; i++) {
            if (solution.size() == 0) {
                int randomNumber = StdRandom.uniform(1000);
                int addFirstOraddLast = StdRandom.uniform(0, 2);
                switch (addFirstOraddLast) {
                    case 0:
                        stu.addFirst(randomNumber);
                        solution.addFirst(randomNumber);
                        message += "addFirst(" + randomNumber + ")\n";
                        break;
                    case 1:
                        stu.addLast(randomNumber);
                        solution.addLast(randomNumber);
                        message += "addLast(" + randomNumber + ")\n";
                        break;
                }
            } else {
                int randomNumber = StdRandom.uniform(1000);
                /** random number; 0 use addFirst, 1 use addLast
                 * 2 use removeFirst, 3 use removeLast;
                 */
                int flagNumber = StdRandom.uniform(4);
                Integer actualRemoveNumber = null;
                Integer expectedRemoveNumber = null;
                switch (flagNumber) {
                    case 0:
                        stu.addFirst(randomNumber);
                        solution.addFirst(randomNumber);
                        message += "addFirst(" + randomNumber + ")\n";
                        break;
                    case 1:
                        stu.addLast(randomNumber);
                        solution.addLast(randomNumber);
                        message += "addLast(" + randomNumber + ")\n";
                        break;
                    case 2:
                        actualRemoveNumber = stu.removeFirst();
                        expectedRemoveNumber= solution.removeFirst();
                        message += "removeFirst(" + randomNumber + ")\n";
                        break;
                    case 3:
                        actualRemoveNumber= stu.removeLast();
                        expectedRemoveNumber= solution.removeLast();
                        message += "removeLast(" + randomNumber + ")\n";
                        break;
                    default:
                }
                assertEquals(message, expectedRemoveNumber, actualRemoveNumber);
            }
        }
    }
}
