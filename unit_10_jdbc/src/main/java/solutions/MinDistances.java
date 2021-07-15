package solutions;


public class MinDistances {
    private final int MAX = 200000;

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

//    private int[] getResult(List<String> input, int[][] distances){
//        int[] rows = new int[100];
//        int counter = 0;
//
//        for(String in : input){
//            String[] distance = in.split(" ");
//            if(Character.isLetter(distance[0].charAt(0)) && distance.length == 2){
//                int firstCityIndex = cities.get(distance[0]);
//                int secondCityIndex = cities.get(distance[1]);
//                if(distances[firstCityIndex-1][secondCityIndex-1] == MAX)
//                    rows[counter] = -1;
//                else
//                    rows[counter] = distances[firstCityIndex-1][secondCityIndex-1];
//                counter++;
//            }
//        }
//
//        int[] result = new int[counter];
//        for (int i = 0; i < counter; i++) {
//            result[i] = rows[i];
//        }
//        return result;
//    }
}
