import java.util.ArrayList; // Импортируем класс из стандартной библиотеки Java
import java.util.Stack; // Импортируем класс из стандартной библиотеки Java

public class GraphB
{
    private Stack<Integer> Stack = new Stack<>(); //  Создаем объект стека для хранения целых чисел
    public int[] Taryana(int[][] orig) // алгоритм Тарьяна для топологической сортировки
    {
        int[] answer = new int[orig[0].length]; // Создаем массив для хранения ответа
        node[] stackVis = new node[answer.length]; // Создаем массив для хранения информации о посещенных узлах
        boolean marker=true; // Устанавливаем маркер на истину
        for (int i = 0; i < orig[0].length; ++i) // Цикл по узлам графа
            stackVis[i] = new node(false, false); // Для каждого узла создаем объект класса node и добавляется в массив stackVis
        for (int i=0; i<answer.length; ++i) // Цикл по узлам графа
            if (stackVis[i].visited==false) // Если узел не был посещен
            {
                marker = InDepth(i, orig, stackVis, marker); // Запускаем метод InDepth для обхода в глубину
            }
        if (marker) // Если маркер равен истине
        {
            for (int i = 0; i < answer.length; ++i) // Цикл по узлам графа
                answer[i] = Stack.pop(); // Извлекаем элемент из стека и добавляется в массив ответа
        }
        else // Иначе
        {
            System.out.println("Для данного графа не существует топологической сортировки");
            answer=null; // Массив ответа устанавливаем в null
        }
        return answer; // Возвращаем массив ответа
    }
    private boolean InDepth(int peak, int[][] orig, node[] visited, boolean marker) // Метод InDepth реализует алгоритм поиска в глубину
    {
        visited[peak].visited = true; // Помечаем текущую вершину, как посещенную
        for (int i = peak, j = 0; j < visited.length; ++j) // Проходим по всем вершинам графа смежным с peak
        {
            if (visited[j].visited == false && orig[i][j] != 0) // Если вершина не посещена и есть ребро в матрице смежности
                marker=InDepth(j, orig, visited, marker); // Рекурсивно вызываем метод для следующей вершины
            else if (visited[j].inStak != true && orig[i][j]!=0) // Если вершина еще не находится в стеке (но уже была посещена) и есть ребро в матрице смежности
                marker = false; // Маркер становится ложным, так как найден цикл
        }
        if (marker) // Если маркер все еще истинный, то текущую вершину можно добавить в стек
        {
            Stack.push(peak); // Добавляем вершину в стек
            visited[peak].inStak = true; // Помечаем вершину как находящуюся в стеке
        }
        return marker; // Возвращаем маркер
    }
    public ArrayList<node> Fleury(int[][] orig, int start) // Метод Fleury для нахождения Эйлерова цикла
    {
        ArrayList<node> answer = new ArrayList<>(); // Список вершин в Эйлеровом цикле
        int [] checkVertical = new int[orig[0].length]; // Массив для проверки количества ребер входящих в каждую вершину
        int[] checkHorizontal = new int[orig[0].length]; //  Массив для проверки количества ребер выходящих из каждой вершины
        int[] findBridge = new int[orig[0].length]; // Массив для поиска мостов
        int count=0; // Счетчик ребер в графе
        for (int i=0; i<orig[0].length; ++i) // Цикл по всем вершинам графа
            for (int j = 0; j < orig[0].length; ++j) // Цикл по всем соседям вершины
                if (orig[i][j] != 0) // Если между вершинами есть ребро
                {
                    count++; // Увеличиваем счетчик ребер
                    ++checkHorizontal[i]; // Увеличиваем количество ребер в текущей вершине
                    ++checkVertical[j]; // Увеличиваем количество ребер, входящих в текущую вершин
                    ++findBridge[j]; // Увеличиваем счетчик ребер для поиска мостов
                }
        for (int i=0; i<orig[0].length; ++i) // Цикл по всем вершинам графа
            if (checkVertical[i]!=checkHorizontal[i] && checkHorizontal[i]!=0 && checkVertical[i]!=0) // Если количество входящих и исходящих ребер в вершине не равно
                return null; // Возвращаем null, так как в таком графе невозможно найти эйлеров цикл

        while (count>0) // Пока есть неиспользованные ребра в графе, выполняем цикл
        {
            for (int i=start, j=0; j<orig[0].length; ++j) // Проходим по всем вершинам, связанным с текущей вершиной
                if (orig[i][j]!=0 && findBridge[j]>1) //Если существует неудаленное ребро между текущей и следующей вершинами, и следующая вершина не является мостом
                {
                    orig[i][j]=0; // Удаляем ребро из графа
                    answer.add(new node(i, j)); // Добавляем ребро в ответ
                    start = j; // Устанавливаем новую текущую вершину
                    --checkHorizontal[i]; // Уменьшаем счетчик ребер выходящих из текущей вершины
                    --count; // Уменьшаем общий счетчик ребер
                    break; // Прерываем цикл
                }
            else if (orig[i][j]!=0 && checkHorizontal[i]==1) // Если существует неудаленное ребро между текущей и следующей вершинами, и следующая вершина не является мостом, но является единственной связанной с текущей вершиной
            {
                    orig[i][j]=0; // Удаляем ребро из графа
                    answer.add(new node(i, j)); // Добавляем ребро в ответ
                    start = j; // Устанавливаем новую текущую вершину
                    --checkHorizontal[i]; // Уменьшаем счетчик ребер выходящих из текущей вершины
                    --count; // Уменьшаем общий счетчик ребер
                    break; // Прерываем цикл
            }
        }
        return answer; //  Возвращаем список ребер в порядке обхода для Эйлерова пути или цикла
    }
    public ArrayList<Integer> CyclesUnion(int[][] orig, int start) // Метод для поиска объединения циклов в графе orig, начинающихся с вершины start
    {
        ArrayList<Integer> answer = new ArrayList<>(); // Инициализируем пустой список для хранения результата
        int [] checkVertical = new int[orig[0].length]; // Массив для хранения количества входящих ребер в каждую вершину
        int[] checkHorizontal = new int[orig[0].length]; // Массив для хранения количества исходящих ребер из каждой вершины
        for (int i=0; i<orig[0].length; ++i) // Проходим по всем вершинам графа
            for (int j = 0; j < orig[0].length; ++j) // И по всем их ребрам
                if (orig[i][j] != 0) // Если ребро существует
                {
                    ++checkHorizontal[i]; // Увеличиваем количество исходящих ребер из вершины i
                    ++checkVertical[j]; // Увеличиваем количество входящих ребер в вершину j
                }
        for (int i=0; i<orig[0].length; ++i) // Проверяем условие необходимости наличия эйлерова пути
            if (checkVertical[i]!=checkHorizontal[i] && checkHorizontal[i]!=0 && checkVertical[i]!=0) // Если количество входящих ребер в i не равно количеству исходящих и оба значения не равны 0
                return null; // Возвращаем null, так как путь не может быть найден

        answer = FindEulerPath(orig, start, answer); // Находим эйлеров путь, начинающийся с вершины start и сохраняем его в списке answer
        return answer; // Возвращаем список со всеми вершинами пути
    }
    private ArrayList<Integer> FindEulerPath(int[][] orig, int i, ArrayList<Integer> answer) // Алгоритм поиска Эйлерова пути
    {
        for (int j=0; j<orig[0].length; ++j) // Проходим по всем вершинам графа смежным с точкой i
            if (orig[i][j]!=0) // Если между вершинами i и j есть ребро
            {
                orig[i][j] = 0; //  Удаляем это ребро, чтобы в дальнейшем не посетить его повторно
                answer = FindEulerPath(orig, j, answer); // Рекурсивно идем вглубь графа, начиная от вершины j
            }
        answer.add(i); // Добавляем вершину i в список ответа
        return answer; // Возвращаем список ответа
    }
    public ArrayList<node> Kosaraju(int[][] orig) //  алгоритм Косарайю
    {
        ArrayList<node> answer = new ArrayList<>(); // Список для ответа

        for (int i=0; i<orig[0].length-1; ++i) // Цикл для транспонирования матрицы
            for (int j=i+1; j<orig[0].length; ++j) // Меняем местами строки и столбцы
            {
                int tmp = orig[i][j]; // меняем элементы местами
                orig[i][j]=orig[j][i];
                orig[j][i]=tmp;
            }

        boolean[] visited = new boolean[orig[0].length]; // Массив, хранящий информацию о том, был ли посещен узел графа
        for (int i = 0; i<visited.length; ++i) // Инициализируем массив visited значением false
            visited[i]=false;
        Stack.push(-5); // Помещаем в стек элемент -5
        for (int i=0; i<visited.length; ++i) // Обходим его в глубину по транспанированной матрице
            if (!visited[i]) // Если узел не посещен
                dfs_inv(orig, i, visited); // Запускаем обход в глубину с текущего узла
        for (int i=0; i<visited.length; ++i) // Сбрасываем visited в false
            visited[i]=false;
        while (Stack.peek()!=-5) // Пока стек не пуст
        {
            int tmp = Stack.pop(); // Извлекаем из стека элемент
            if (visited[tmp] == false) // Если узел не посещен, добавляем его в список answer
            {
                answer.add(dfs(orig, tmp, visited, new node())); // Запускаем обход в глубину (идем по исходной матрице) с текущего узла и добавляем в список ответа
            }
        }
        Stack.pop(); // Удаляем из стека элемент -5
        return answer; // Возвращаем список узлов
    }
    private void dfs_inv(int[][] orig, int peak, boolean[] visited) // Метод обхода графа в глубину с транспонированным графом
    {
        visited[peak]=true; // Отмечаем узел peak как посещенный
        for (int i=peak, j=0; j<orig[0].length; ++j) // Обходим смежные узлы с узлом peak
            if (orig[i][j]!=0 && visited[j] == false) // Если смежный узел j не посещен, обходим его
                dfs_inv(orig, j, visited);
        Stack.push(peak); // Помещаем узел peak в стек
    }
    private node dfs(int[][] orig, int peak, boolean[] visited, node PartOfAnswer) // Метод рекурсивного обхода графа в глубину
    {
        visited[peak] = true; // Отмечаем узел как посещенный
        PartOfAnswer.ErgodicСlass.add(peak); // Добавляем узел в эргодический класс
        for (int j = peak, i = 0; i < visited.length; ++i) // Перебираем все узлы графа, смежные с peak
            if (visited[i]==false && orig[i][j] != 0) // Если узел не посещен и есть ребро между ним и текущим узлом
                PartOfAnswer = dfs(orig, i, visited, PartOfAnswer); // Рекурсивно вызываем метод dfs для нового узла
        return PartOfAnswer; // Возвращаем эргодический класс
    }
}