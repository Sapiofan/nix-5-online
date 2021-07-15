package solutions;


public class MinDistances {
    private static final int MAX = 200000;

    public int[][] distance(int numOfCities, int[][] routes){
        int[][] distance = new int[numOfCities][numOfCities];

        for (int i = 0; i < numOfCities; i++) {
            for (int j = 0; j < numOfCities; j++) {
                if (routes[i][j] == 0 && i != j)
                    distance[i][j] = MAX;
                else
                    distance[i][j] = routes[i][j];
            }
        }

        for (int i = 0; i < numOfCities; i++) {
            for (int j = 0; j < numOfCities; j++) {
                for (int k = 0; k < numOfCities; k++) {
                    if (distance[i][k] + distance[k][j] < distance[i][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }
        return distance;
    }
}
