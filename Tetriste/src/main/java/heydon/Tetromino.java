package heydon;
import java.util.Random;


// Classe représentant un tetromino (pièce de Tetris)

   

/**
 * Classe représentant un Tetromino (pièce de Tetris).
 * Un Tetromino est défini par ses différentes formes (rotations possibles) et son état actuel.
 */
public class Tetromino {

    // Définition des différentes formes possibles pour chaque type de Tetromino.
    // Chaque forme est représentée par un tableau 3D contenant les positions relatives des blocs.

    /**
     * Forme "I" (ligne) avec ses 4 rotations possibles.
     */
    private static final int[][][] SHAPE_I = {
        {{0, 1}, {1, 1}, {2, 1}, {3, 1}}, // Rotation 0
        {{2, 0}, {2, 1}, {2, 2}, {2, 3}}, // Rotation 90°
        {{0, 1}, {1, 1}, {2, 1}, {3, 1}}, // Rotation 180°
        {{2, 0}, {2, 1}, {2, 2}, {2, 3}}  // Rotation 270°
    };

    /**
     * Forme "O" (carré) avec ses rotations (identiques dans toutes les orientations).
     */
    private static final int[][][] SHAPE_O = {
        {{0, 0}, {1, 0}, {0, 1}, {1, 1}}, // Rotation 0
        {{0, 0}, {1, 0}, {0, 1}, {1, 1}}, // Rotation 90°
        {{0, 0}, {1, 0}, {0, 1}, {1, 1}}, // Rotation 180°
        {{0, 0}, {1, 0}, {0, 1}, {1, 1}}  // Rotation 270°
    };

    /**
     * Forme "L" avec ses différentes rotations.
     */
    private static final int[][][] SHAPE_L = {
        {{0, 0}, {0, 1}, {0, 2}, {1, 2}}, // Rotation 0
        {{0, 0}, {0, 1}, {2, 0}, {1, 0}}, // Rotation 90°
        {{0, 0}, {1, 0}, {1, 1}, {1, 2}}, // Rotation 180°
        {{0, 1}, {1, 1}, {2, 1}, {2, 0}}   // Rotation 270°
    };

    /**
     * Forme "J" avec ses différentes rotations.
     */
    private static final int[][][] SHAPE_J = {
        {{1, 0}, {1, 1}, {1, 2}, {0, 2}}, // Rotation 0
        {{0, 0}, {0, 1}, {1, 1}, {2, 1}}, // Rotation 90°
        {{0, 0}, {1, 0}, {0, 1}, {0, 2}}, // Rotation 180°
        {{0, 0}, {1, 0}, {2, 0}, {2, 1}}   // Rotation 270°
    };
    
    
    /**
     * Forme "T" avec ses 4 rotations possibles.
     */
    private static final int[][][] SHAPE_T = {
        {{1, 0}, {0, 1}, {1, 1}, {2, 1}}, // Rotation 0
        {{1, 0}, {1, 1}, {2, 1}, {1, 2}}, // Rotation 90°
        {{0, 1}, {1, 1}, {2, 1}, {1, 2}}, // Rotation 180°
        {{1, 0}, {0, 1}, {1, 1}, {1, 2}}  // Rotation 270°
    };

    /**
     * Forme "Z" avec ses 4 rotations possibles.
     */
    private static final int[][][] SHAPE_Z = {
        {{0, 0}, {1, 0}, {1, 1}, {2, 1}}, // Rotation 0
        {{1, 0}, {1, 1}, {0, 1}, {0, 2}}, // Rotation 90°
        {{0, 0}, {1, 0}, {1, 1}, {2, 1}}, // Rotation 180°
        {{1, 0}, {1, 1}, {0, 1}, {0, 2}}  // Rotation 270°
    };

    /**
     * Forme "S" avec ses 4 rotations possibles.
     */
    private static final int[][][] SHAPE_S = {
        {{0, 0}, {1, 0}, {0, 1}, {-1, 1}}, // Rotation 0
        {{0, 0}, {0, 1}, {1, 1}, {1, 2}}, // Rotation 90°
        {{0, 0}, {1, 0}, {0, 1}, {-1, 1}}, // Rotation 180°
        {{0, 0}, {0, 1}, {1, 1}, {1, 2}}   // Rotation 270°
    };

    /**
     * Tableau contenant les formes et leurs rotations pour un Tetromino spécifique.
     */
    private int[][][] shapes;

    /**
     * Indique la rotation actuelle du Tetromino (valeur entre `0` et `3`).
     */
    private int currentRotation;

    /**
     * Type du Tetromino (par exemple "T", "Z", "S").
     */
    private String type;

    private static final String[] TETROMINO_TYPES = {"I", "O", "L", "J", "T", "Z", "S"}; // Types de Tetrominos disponibles
    private static final Random random = new Random(); // Générateur aléatoire pour sélectionner un Tetromino

    /**
     * Constructeur de la classe Tetromino.
     * Initialise un Tetromino en fonction de son type spécifié.
     *
     * @param type Le type du Tetromino (par exemple, "I", "O", "L", "J", "T", "Z", ou "S")
     */
    public Tetromino(String type) {
        this.type = type; // Définit le type du Tetromino
        currentRotation = 0; // Initialise la rotation à 0 (rotation par défaut)

        // Associe les formes correspondantes au type spécifié
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
                shapes = new int[][][]{{{0}}}; // Forme par défaut si le type est invalide
        }
    }

    /**
     * Méthode statique pour obtenir un Tetromino aléatoire.
     *
     * @return Un objet `Tetromino` avec un type sélectionné aléatoirement parmi les types disponibles
     */
    public static Tetromino getRandomTetromino() {
        int index = random.nextInt(TETROMINO_TYPES.length); // Sélectionne un index aléatoire
        return new Tetromino(TETROMINO_TYPES[index]); // Retourne un nouveau Tetromino du type correspondant
    }

    /**
     * Retourne la forme actuelle du Tetromino en fonction de sa rotation.
     *
     * @return Un tableau 2D représentant les coordonnées relatives des blocs dans la rotation actuelle
     */
    public int[][] getCurrentShape() {
        return shapes[currentRotation];
    }

    /**
     * Effectue une rotation du Tetromino dans le sens horaire.
     * Passe à la rotation suivante ou revient à la première rotation si la dernière est atteinte.
     */
    public void rotate() {
        currentRotation = (currentRotation + 1) % shapes.length; // Change la rotation actuelle
    }

    /**
     * Retourne le type du Tetromino.
     *
     * @return Une chaîne de caractères représentant le type du Tetromino (par exemple, "I", "O", etc.)
     */
    public String getType() {
        return type;
    }
}


