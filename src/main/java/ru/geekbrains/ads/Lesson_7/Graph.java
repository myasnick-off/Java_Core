package ru.geekbrains.ads.Lesson_7;

public interface Graph {

    void addVertex(String label);

    void addVertexAll(String... labels);

    boolean addEdge(int weight, String startLabel, String secondLabel, String... others);

    boolean addEdge(int weight, String startLabel, String endLabel);

    int getSize();

    void display();

    /**
     * англ. Depth-first search, DFS
     */
    void dfs(String startLabel, String endLabel);

    /**
     * англ. breadth-first search, BFS
     */
    void bfs(String startLabel, String endLabel);

//    Stack<String> findShortPathViaBfs(String startLabel, String finishLabel);
}
