import java.util.ArrayList;

public class TestGraphB // Объявляем класс для тестирования
{
    public static void main(String[] args)
    {
        int[][] RightA =
                {
                {0,1,1,0,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,1,0,1,0},
                {0,0,0,0,0,0,1},
                {0,0,0,0,0,1,0},
                {0,0,0,0,0,0,1},
                {0,0,0,0,0,0,0}
                }; // Задаем матрицу инцидентности
        int[][] WrongA =
                {
                {0,1,1,0,0},
                {0,0,0,0,1},
                {0,1,0,0,1},
                {0,1,0,0,0},
                {0,0,0,1,0}
                }; //
        GraphB test = new GraphB(); // создаем объект класса
        int[] answer = test.Taryana(WrongA); // Вызываем метод Taryana для объекта test с аргументом WrongA и сохраняем результат в массив answer
        if (answer!=null) // Если результат не равен null, то выполняем следующие действия
        {
            System.out.print("["); // Открываем скобку для вывода ответа
            for (int i = 0; i < answer.length; i++) // Для каждого элемента в массиве answer выполняем следующие действия
                System.out.print(answer[i] + " "); // Выводим ответ
            System.out.println("]"); // закрываем скобку для вывода ответа
        }
        System.out.println("----------------------");
        answer = test.Taryana(RightA); // Вызываем метод Taryana для объекта test с аргументом RightA и сохраняем результат в массив answer
        if (answer!=null) // Если результат не равен null, то выполняем следующие действия
        {
            System.out.print("["); // Открывыаем скобку для вывода ответа
            for (int i = 0; i < answer.length; i++) // Для каждого элемента в массиве answer выполняем следующие действия
                System.out.print(answer[i] + " "); // Выводим ответ
            System.out.println("]"); // закрываем скобку для вывода ответа
        }
        System.out.println("----------------------");
        RightA = new int[][]
                {
                {0,1,0,0,0,0},
                {0,0,1,0,0,1},
                {0,0,0,1,1,0},
                {1,0,0,0,0,1},
                {0,1,0,1,0,0},
                {0,0,1,0,1,0}
                }; //
        ArrayList<node> couples = test.Fleury(RightA, 0); //
        if (couples!=null) //
            for (int i=0; i<couples.size(); ++i) //
                System.out.println(couples.get(i).prev+" -> "+couples.get(i).next); //
        else // Иначе
            System.out.println("В данном графе нет эйлерова цикла!");
        System.out.println("----------------------");
        RightA = new int[][]
                {
                {0,1,0,0,0,0},
                {0,0,1,0,0,1},
                {0,0,0,1,1,0},
                {1,0,0,0,0,1},
                {0,1,0,1,0,0},
                {0,0,1,0,1,0}
                }; // Задаем матрицу инцидентности
        ArrayList<Integer> path = test.CyclesUnion(RightA, 0); // Вызываем метод Fleury для поиска эйлерова цикла в графе.
        if (path!=null) // Если найден эйлеров цикл, то выводим его в консоль
        {
            for (int i = path.size()-1; i > -1; --i)
                System.out.print(path.get(i)+" -> ");
            System.out.println("END");
        }
        else // Иначе
            System.out.println("В данном графе нет эйлерова цикла!");
        System.out.println("----------------------");
        RightA = new int[][]
                {
                {0,0,0,1,0,0,0,0},
                {1,0,0,0,0,0,0,0},
                {0,1,0,0,1,0,1,0},
                {1,0,0,0,1,0,0,0},
                {0,1,0,0,0,0,0,0},
                {0,0,1,0,1,0,0,0},
                {0,0,1,0,0,1,0,0},
                {0,0,0,0,1,0,1,0}
                }; // Задаем матрицу инцидентности
        ArrayList<node> LastAnswer = test.Kosaraju(RightA); // Вызываем метод Косарайю
        for (int i=0; i<LastAnswer.size(); ++i) // Проходим по всем классам
        {
            int tmp=i+1; // Индекс вершины
            System.out.print("В "+tmp+"-й эргодический входят вершины: ");
            for (int j=0; j<LastAnswer.get(i).ErgodicСlass.size(); ++j) // Проходим по всем вершинам класса
                System.out.print(LastAnswer.get(i).ErgodicСlass.get(j)+" "); // Выводим номер вершины
            System.out.println("");
        }
        System.out.println("----------------------");
    }
}