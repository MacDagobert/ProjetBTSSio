package heydon;
import java.util.Random;

// Classe représentant un tetromino (pièce de Tetris)

    public class Tetromino {
    private static final int[][][] SHAPE_I = {
        {{0, 1}, {1, 1}, {2, 1}, {3, 1}},
        {{2, 0}, {2, 1}, {2, 2}, {2, 3}},
        {{0, 1}, {1, 1}, {2, 1}, {3, 1}},
        {{2, 0}, {2, 1}, {2, 2}, {2, 3}}
    };

    private static final int[][][] SHAPE_O = {
        {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
        {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
        {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
        {{0, 0}, {1, 0}, {0, 1}, {1, 1}}
    };

    private static final int[][][] SHAPE_L = {
        {{0, 0}, {0, 1}, {0, 2}, {1, 2}},
        {{0, 0}, {0, 1}, {2, 0}, {1, 0}},
        {{0, 0}, {1, 0}, {1, 1}, {1, 2}},
        {{0, 1}, {1, 1}, {2, 1}, {2, 0}}
    };

    private static final int[][][] SHAPE_J = {
        {{1, 0}, {1, 1}, {1, 2}, {0, 2}},
        {{0, 0}, {0, 1}, {1, 1}, {2, 1}},
        {{0, 0}, {1, 0}, {0, 1}, {0, 2}},
        {{0, 1}, {1, 1}, {2, 1}, {2, 0}}
    };

    private static final int[][][] SHAPE_T = {
        {{0, 0}, {1, 0}, {2, 0}, {1, 1}},
        {{1, 0}, {1, 1}, {0, 1}, {1, 2}},
        {{1, 0}, {1, 1}, {0, 1}, {2, 1}},
        {{0, 0}, {1, 0}, {2, 0}, {2, 1}}
    };

    private static final int[][][] SHAPE_Z = {
        {{0, 0}, {1, 0}, {1, 1}, {2, 1}},
        {{1, 0}, {1, 1}, {0, 1}, {0, 2}},
        {{0, 0}, {1, 0}, {1, 1}, {2, 1}},
        {{1, 0}, {1, 1}, {0, 1}, {0, 2}}
    };

    private static final int[][][] SHAPE_S = {
        {{0, 0}, {1, 0}, {0, 1}, {-1, 1}},
        {{0, 0}, {0, 1}, {1, 1}, {1, 2}},
        {{0, 0}, {1, 0}, {0, 1}, {-1, 1}},
        {{0, 0}, {0, 1}, {1, 1}, {1, 2}}
    };

    private int[][][] shapes;
    private int currentRotation;

    public Tetromino(String type) {
        currentRotation = 0;
        switch (type) {
            case "I":
                shapes = SHAPE_I;
                break;
            case "O":
                shapes = SHAPE_O;
                break;
            case "L":
                shapes = SHAPE_L;
                break;
            case "J":
                shapes = SHAPE_J;
                break;
            case "T":
                shapes = SHAPE_T;
                break;
            case "Z":
                shapes = SHAPE_Z;
                break;
            case "S":
                shapes = SHAPE_S;
                break;
            default:
                shapes = new int[][][]{{{0}}};
        }
    }
private static final String[] TETROMINO_TYPES = {"I", "O", "L", "J", "T", "Z", "S"};
    private static final Random random = new Random();
    
    
    // Méthode statique pour obtenir un Tetromino aléatoire
    public static Tetromino getRandomTetromino() {
        int index = random.nextInt(TETROMINO_TYPES.length);
        return new Tetromino(TETROMINO_TYPES[index]);
    }
    // Retourne la forme actuelle du tetromino
    public int[][] getCurrentShape() {
        return shapes[currentRotation];
    }

    // Effectue une rotation du tetromino
    public void rotate() {
        currentRotation = (currentRotation + 1) % shapes.length; // Change la rotation actuelle
    }
}


