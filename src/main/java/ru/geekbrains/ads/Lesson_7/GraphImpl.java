package ru.geekbrains.ads.Lesson_7;

import java.util.*;

public class GraphImpl implements Graph {

    private final List<Vertex> vertexList;          // список вершин графа
    private final int[][] adjMatrix;                // матрица связей
    Map<String, Integer> pathMap = new HashMap<>(); // мапа всех возможных путей из одной вершины в другую
    // с его суммарным весом

    public GraphImpl(int maxVertexNumber) {
        this.vertexList = new ArrayList<>(maxVertexNumber);
        this.adjMatrix = new int[maxVertexNumber][maxVertexNumber];
    }

    // добавление вершины графа
    @Override
    public void addVertex(String label) {
        vertexList.add(new Vertex(label));

    }

    // множественное добавление вершин графа
    @Override
    public void addVertexAll(String... labels) {
        for (String label : labels) {
            vertexList.add(new Vertex(label));
        }
    }

    // добавление весовых связей между несколькими вершинами
    @Override
    public boolean addEdge(int weight, String startLabel, String secondLabel, String... others) {
        boolean result = addEdge(weight, startLabel, secondLabel);

        for (String other : others) {
            result &= addEdge(weight, startLabel, other);
        }
        return result;
    }

    // добавление весовых связей между двумя вершинами
    @Override
    public boolean addEdge(int weight, String startLabel, String endLabel) {
        int startIndex = indexOf(startLabel);
        int endIndex = indexOf(endLabel);

        if (startIndex == -1 || endIndex == -1) {
            return false;
        }
        adjMatrix[startIndex][endIndex] = weight;
        return true;
    }

    // нахождение индекса вершины по ее названию
    private int indexOf(String label) {
        for (int i = 0; i < getSize(); i++) {
            if (vertexList.get(i).getLabel().equals(label)) {
                return i;
            }
        }
        return -1;
    }

    // поиск всех путей от вершины startLabel до вершины endLabel проходом в глубину
    @Override
    public void dfs(String startLabel, String endLabel) {
        int startIndex = indexOf(startLabel);
        int totalTime = 0;
        if (startIndex == -1) {
            throw new IllegalArgumentException("Error!!! Wrong index: " + startLabel);
        }
        Stack<Vertex> stack = new Stack<>();
        Vertex next = vertexList.get(startIndex);
        StringBuilder pathString = new StringBuilder(startLabel);
        pathMap.clear();

        visitVertex(stack, next);
        while (!stack.isEmpty()) {
            Vertex current = stack.peek();
            next = getNearUnvisitedVertex(current);
            if (next != null) {
                totalTime += adjMatrix[vertexList.indexOf(current)][vertexList.indexOf(next)];
                pathString.append(" -> ").append(next.getLabel());
                if (!next.getLabel().equals(endLabel)) {
                    visitVertex(stack, next);
                } else {
                    stack.pop();
                    pathMap.put(pathString.toString(), totalTime);
                    pathString = new StringBuilder(startLabel);
                    totalTime = 0;
                }
            } else {
                stack.pop();
            }
        }
    }

    // поиск всех путей от вершины startLabel до вершины endLabel проходом в ширину
    // P.S.: получился индусский код, но это все что пришло на ум
    @Override
    public void bfs(String startLabel, String endLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Error!!! Wrong index: " + startLabel);
        }

        List<Vertex[]> pairList = new ArrayList<>();
        Queue<Vertex> queue = new LinkedList<>();
        Vertex next = vertexList.get(startIndex);

        visitVertex(queue, next);

        while (!queue.isEmpty()) {
            Vertex current = queue.peek();
            next = getNearUnvisitedVertex(current);
            if (next != null) {
                pairList.add(new Vertex[]{current, next});
                if (!next.getLabel().equals(endLabel)) {
                    visitVertex(queue, next);
                } else {
                    queue.remove();
                }
            } else {
                queue.remove();
            }
        }
        findPaths(startLabel, pairList);
    }

    // вспомогательнй метод для построения путей из списка пар связанных вершин, полученных bfs
    private void findPaths(String startLabel, List<Vertex[]> pairList) {
        pathMap.clear();
        for (int i = 0; i < pairList.size() - 1; i++) {

            Vertex[] current = pairList.get(i);

            if (current[0].getLabel().equals(startLabel)) {

                StringBuilder pathString = new StringBuilder(current[0].getLabel());
                pathString.append(" -> ").append(current[1].getLabel());

                int x = indexOf(current[0].getLabel());
                int y = indexOf(current[1].getLabel());
                int time = adjMatrix[x][y];

                for (int j = 1; j < pairList.size(); j++) {

                    if (current[1].equals(pairList.get(j)[0])) {
                        current = pairList.get(j);

                        x = indexOf(current[0].getLabel());
                        y = indexOf(current[1].getLabel());
                        time += adjMatrix[x][y];

                        pathString.append(" -> ").append(current[1].getLabel());
                    }
                }
                pathMap.put(pathString.toString(), time);
            }
        }
    }

    // метод посещения вершины и добавления ее в стек
    private void visitVertex(Stack<Vertex> stack, Vertex vertex) {
        stack.push(vertex);
        vertex.setVisited(true);
    }

    // метод посещения вершины и добавления ее в очередь
    private void visitVertex(Queue<Vertex> queue, Vertex vertex) {
        queue.add(vertex);
        vertex.setVisited(true);
    }

    // получение рядом стоящей связанной непосещенной вершины
    private Vertex getNearUnvisitedVertex(Vertex vertex) {
        int currentIndex = vertexList.indexOf(vertex);
        for (int i = 0; i < getSize(); i++) {
            if (adjMatrix[currentIndex][i] > 0 && !vertexList.get(i).isVisited()) {
                return vertexList.get(i);
            }
        }
        return null;
    }

    // вывод в консоль всех путей от startLabel до startLabel и общего времени их прохождения
    public void showPaths() {

        // поиск наименьшего суммврного веса (bestTime) среди всех путей
        List<Integer> timeList = new ArrayList<>(pathMap.values());
        int bestTime = timeList.get(0);
        for (int i = 1; i < timeList.size(); i++) {
            if (timeList.get(i) < bestTime) {
                bestTime = timeList.get(i);
            }
        }
        // вывод в консоль всех путей и их суммарных весов
        Set<String> paths = pathMap.keySet();
        StringBuilder bestPath = new StringBuilder();
        for (String path : paths) {
            System.out.println(path + " " + pathMap.get(path));
            if (pathMap.get(path) == bestTime) {
                bestPath.append(path);
            }
        }
        // вывод в консоль кратчайшего пути
        System.out.println("----------------------------");
        System.out.println("Кратчайший путь: " + bestPath + " " + bestTime);
    }

    @Override
    public int getSize() {
        return vertexList.size();
    }

    @Override
    public void display() {
        for (int i = 0; i < getSize(); i++) {
            System.out.println(vertexList.get(i));
            for (int j = 0; j < getSize(); j++) {
                if (adjMatrix[i][j] > 0) {
                    System.out.printf(" -%d-> %s\n", adjMatrix[i][j], vertexList.get(j));
                }
            }
            System.out.println();
        }
    }
}
