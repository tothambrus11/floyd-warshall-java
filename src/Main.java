public class Main {
    public static void main(String[] args) {
        boolean[][] map = new boolean[][]{
                {false, false, true, true, false, false, false, true, true, true, false, false, false, true, true, false, false},
                {true, false, false, true, false, true, true, true, false, true, true, true, false, true, false, false, true},
                {true, true, true, true, true, true, false, true, true, true, false, true, true, true, true, true, true},
                {true, false, true, false, true, false, false, false, true, false, false, false, true, false, true, false, true},
                {false, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, false},
                {false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false},
                {true, true, false, false, false, true, false, true, false, true, false, true, false, false, false, true, true},
                {false, false, false, false, false, false, false, true, true, true, false, false, false, false, false, false, false}
        };
        int[][][][] d = floydWarshall(map);

        String json = "[";
        int A_x = 6;
        int A_y = 1;

        for (int B_y = 0; B_y < map.length; B_y++) {
            json += "[";
            for (int B_x = 0; B_x < map[0].length; B_x++) {
                json += d[A_x][A_y][B_x][B_y];
                if (B_x != map[0].length - 1) {
                    json += ",";
                }
            }
            json += "]";
            if (B_y != map.length - 1) {
                json += ",";
            }
        }
        json += "]";
        System.out.println(json);

    }

    static int[][][][] floydWarshall(boolean[][] map) {
        int w = map[0].length;
        int h = map.length;

        int[][][][] d = new int[w][h][w][h];

        int x1, y1, x2, y2;
        for (x1 = 0; x1 < w; x1++) {
            for (y1 = 0; y1 < h; y1++) {
                for (x2 = 0; x2 < w; x2++) {
                    for (y2 = 0; y2 < h; y2++) {
                        if (map[y1][x1] && map[y2][x2]) {
                            if (x1 == x2) {
                                if (y1 == y2) {
                                    d[x1][y1][x2][y2] = 0; // Azonos pontok
                                } else if (y1 == y2 + 1 || y1 == y2 - 1) {
                                    d[x1][y1][x2][y2] = 1; // Függőlegesen 1 távolságra vannak
                                } else {
                                    d[x1][y1][x2][y2] = 10000000; // Tök random koordináták
                                }
                            } else if (y1 == y2) {
                                if (x1 == x2 + 1 || x1 == x2 - 1) {
                                    d[x1][y1][x2][y2] = 1; // Vízszintesen 1 távolságra vannak
                                } else {
                                    d[x1][y1][x2][y2] = 10000000; // Tök random koordináták
                                }
                            } else {
                                d[x1][y1][x2][y2] = 10000000; // Tök random koordináták
                            }
                        } else {
                            d[x1][y1][x2][y2] = 10000000; // Falról nem mehetünk sehova
                        }
                    }
                }

            }
        }

        int A_x, A_y, B_x, B_y, C_x, C_y;

        for (C_x = 0; C_x < w; C_x++) {
            for (C_y = 0; C_y < h; C_y++) {
                for (A_x = 0; A_x < w; A_x++) {
                    for (A_y = 0; A_y < h; A_y++) {
                        for (B_x = 0; B_x < w; B_x++) {
                            for (B_y = 0; B_y < h; B_y++) {
                                if (d[A_x][A_y][B_x][B_y] > d[A_x][A_y][C_x][C_y]
                                        + d[C_x][C_y][B_x][B_y]) {

                                    d[A_x][A_y][B_x][B_y] = d[A_x][A_y][C_x][C_y]
                                            + d[C_x][C_y][B_x][B_y];
                                }
                            }
                        }
                    }
                }
            }
        }

        return d;
    }
}
