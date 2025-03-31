console.log("Le fichier script.js est chargé");

document.addEventListener('DOMContentLoaded', function() {

    // Variables 

let score = 0;

let grille = [
    "+------------------------+",
    "|                        |",
    "|                        |",
    "|                        |",
    "|                        |",
    "|                        |",
    "|                        |",
    "|                        |",
    "|                        |",
    "|                        |",
    "|                        |",
    "|                        |",
    "|                        |",
    "|                        |",
    "|                        |",
    "|                        |",
    "+------------------------+"
];

let x = 11;
let y = 1;
let ok = true;
let pieceCourante;
let rotationActuelle = 0;

const I = [
    [
        [0, 1, 0, 0],
        [0, 1, 0, 0],
        [0, 1, 0, 0],
        [0, 1, 0, 0]
    ],
    [
        [0, 0, 0, 0],
        [1, 1, 1, 1],
        [0, 0, 0, 0],
        [0, 0, 0, 0]
    ],
    [
        [0, 0, 1, 0],
        [0, 0, 1, 0],
        [0, 0, 1, 0],
        [0, 0, 1, 0]
    ],
    [
        [0, 0, 0, 0],
        [0, 0, 0, 0],
        [1, 1, 1, 1],
        [0, 0, 0, 0]
    ]
];

const O = [
    [
        [0, 0, 0, 0],
        [0, 1, 1, 0],
        [0, 1, 1, 0],
        [0, 0, 0, 0]
    ],
    [
        [0, 0, 0, 0],
        [0, 1, 1, 0],
        [0, 1, 1, 0],
        [0, 0, 0, 0]
    ],
    [
        [0, 0, 0, 0],
        [0, 1, 1, 0],
        [0, 1, 1, 0],
        [0, 0, 0, 0]
    ],
    [
        [0, 0, 0, 0],
        [0, 1, 1, 0],
        [0, 1, 1, 0],
        [0, 0, 0, 0]
    ]
];

const L = [
    [
        [0, 1, 0, 0],
        [0, 1, 0, 0],
        [0, 1, 1, 0],
        [0, 0, 0, 0]
    ],
    [
        [0, 0, 0, 0],
        [0, 1, 1, 1],
        [0, 0, 0, 1],
        [0, 0, 0, 0]
    ],
    [
        [0, 0, 0, 0],
        [0, 1, 1, 0],
        [0, 0, 1, 0],
        [0, 0, 1, 0]
    ],
    [
        [0, 0, 0, 0],
        [0, 0, 0, 1],
        [0, 1, 1, 1],
        [0, 0, 0, 0]
    ]
];

const T = [
    [
        [0, 1, 0, 0],
        [0, 1, 1, 0],
        [0, 1, 0, 0],
        [0, 0, 0, 0]
    ],
    [
        [0, 0, 0, 0],
        [0, 0, 1, 0],
        [0, 1, 1, 1],
        [0, 0, 0, 0]
    ],
    [
        [0, 0, 0, 1],
        [0, 0, 1, 1],
        [0, 0, 0, 1],
        [0, 0, 0, 0]
    ],
    [
        [0, 1, 1, 1],
        [0, 0, 1, 0],
        [0, 0, 0, 0],
        [0, 0, 0, 0]
    ]
];

const S = [
    [
        [0, 0, 0, 0],
        [0, 0, 1, 1],
        [0, 1, 1, 0],
        [0, 0, 0, 0]
    ],
    [
        [0, 0, 0, 0],
        [0, 1, 0, 0],
        [0, 1, 1, 0],
        [0, 0, 1, 0]
    ],
    [
        [0, 0, 0, 0],
        [0, 0, 1, 1],
        [0, 1, 1, 0],
        [0, 0, 0, 0]
    ],
    [
        [0, 0, 0, 0],
        [0, 1, 0, 0],
        [0, 1, 1, 0],
        [0, 0, 1, 0]
    ]
];

const J = [
    [
        [0, 0, 0, 0],
        [0, 0, 1, 0],
        [0, 0, 1, 0],
        [0, 1, 1, 0]
    ],
    [
        [0, 0, 0, 0],
        [0, 1, 1, 0],
        [0, 1, 0, 0],
        [0, 1, 0, 0]
    ],
    [
        [0, 0, 0, 0],
        [0, 1, 1, 1],
        [0, 0, 0, 1],
        [0, 0, 0, 0]
    ],
    [
        [0, 0, 0, 0],
        [0, 1, 0, 0],
        [0, 1, 1, 1],
        [0, 0, 0, 0]
    ]
];

const Z = [
    [
        [0, 0, 0, 0],
        [0, 1, 1, 0],
        [0, 0, 1, 1],
        [0, 0, 0, 0]
    ],
    [
        [0, 0, 0, 0],
        [0, 0, 1, 0],
        [0, 1, 1, 0],
        [0, 1, 0, 0]
    ],
    [
        [0, 0, 0, 0],
        [0, 1, 1, 0],
        [0, 0, 1, 1],
        [0, 0, 0, 0]
    ],
    [
        [0, 0, 0, 0],
        [0, 0, 1, 0],
        [0, 1, 1, 0],
        [0, 1, 0, 0]
    ]
];

const P = [
    [
        [0, 0, 0, 0],
        [0, 1, 0, 0],
        [1, 1, 1, 0],
        [0, 1, 0, 0]
    ],
    [
        [0, 0, 0, 0],
        [0, 1, 0, 0],
        [1, 1, 1, 0],
        [0, 1, 0, 0]
    ],
    [
        [0, 0, 0, 0],
        [0, 1, 0, 0],
        [1, 1, 1, 0],
        [0, 1, 0, 0]
    ],
    [
        [0, 0, 0, 0],
        [0, 1, 0, 0],
        [1, 1, 1, 0],
        [0, 1, 0, 0]
    ]
];

// fonctions

function afficherGrille() {
    let grilleTemp = grille.map(row => row.slice()); // Supprimer la copie profonde, car la grille n'est pas modifiée ici

    for (let i = 0; i < pieceCourante.length; i++) {
        for (let j = 0; j < pieceCourante[i].length; j++) {
            if (pieceCourante[i][j] === 1) {
                let newY = y + i;
                let newX = x + j;

                if (newY >= 1 && newY < grilleTemp.length - 1 && newX >= 1 && newX < grilleTemp[0].length - 1) {
                    grilleTemp[newY] = grilleTemp[newY].substring(0, newX) + '#' + grilleTemp[newY].substring(newX + 1);
                }
            }
        }
    }

    document.querySelector("#jeu").innerHTML = grilleTemp.map(ligne => ligne.replace(/ /g,"&nbsp;")).join("<br>");
}
function mouvementValide(dx, dy, pc) {
    for (let i = 0; i < pc.length; i++) {
        for (let j = 0; j < pc[i].length; j++) {
            if (pc[i][j]) {
                let newX = x + j + dx;
                let newY = y + i + dy;

                if (newX < 1 || newX > grille[0].length - 2 || 
                    newY > grille.length - 2 || newY < 1 || 
                    grille[newY][newX] !== ' ') {
                    return false;
                }
            }
        }
    }
    return true;
}
function fixerPiece() {
    let collision = false;

    for (let i = 0; i < pieceCourante.length; i++) {
        for (let j = 0; j < pieceCourante[i].length; j++) {
            if (pieceCourante[i][j] === 1) {
                let newY = y + i;
                let newX = x + j;

                if (newY >= 1 && newY < grille.length - 1 && newX >= 1 && newX < grille[0].length - 1) {
                    if (grille[newY][newX] !== ' ') {
                        collision = true;
                        break;
                    }
                    grille[newY] = grille[newY].substring(0, newX) + '#' + grille[newY].substring(newX + 1);
                } else {
                    collision = true;
                    break;
                }
            }
        }
        if (collision) break;
    }

    if (collision) {
        console.log("Collision détectée lors de la fixation de la pièce");
        return false;
    }

    score += 10;
    afficherScore();
    return true;
}
function supprimerLignesPleines() {
    let lignesSupprimees = 0;
    for (let i = grille.length - 2; i > 0; i--) {
        if (grille[i].slice(1, -1).split('').every(char => char === '#')) {
            grille.splice(i, 1);
            grille.splice(1, 0, "|                        |");
            lignesSupprimees++;
        }
    }

    if (lignesSupprimees > 0) {
        score += lignesSupprimees * 100;
        afficherScore();
    }

    return lignesSupprimees;
}
function afficherScore() {
    const scoreElement = document.getElementById("score");
    if (scoreElement) {
        scoreElement.innerText = "Score : " + score;
    } else {
        console.warn("Element with ID 'score' not found in the DOM.");
    }
}



function genererNouvellePiece() {
    const pieces = [I, O, L, T, S, J, Z, P];
    const pieceAleatoire = pieces[Math.floor(Math.random() * pieces.length)];
    pieceCourante = pieceAleatoire[0];

    x = Math.floor((grille[0].length - pieceCourante[0].length) / 2);
    y = 1;

    if (!mouvementValide(0, 0, pieceCourante)) {
        gameOver();
    }
}
function deplacer(dx, dy) {
    if (!ok) return;

    if (mouvementValide(dx, dy, pieceCourante)) {
        x += dx;
        y += dy;
    } else if (dy === 1) {
        if (!fixerPiece()) {
            gameOver();
            return;
        }
        supprimerLignesPleines();
        genererNouvellePiece();
    }

    afficherGrille();
}
function rotatePiece() {
    const pieces = [I, O, L, T, S, J, Z, P];
    const currentPieceIndex = pieces.findIndex(piece => piece.includes(pieceCourante));

    if (currentPieceIndex === -1) {
        console.warn("Pièce courante introuvable dans la liste des pièces.");
        return;
    }

    let nouvelleRotationIndex = (rotationActuelle + 1) % pieces[currentPieceIndex].length;
    let nouvellePiece = pieces[currentPieceIndex][nouvelleRotationIndex];

    if (mouvementValide(0, 0, nouvellePiece)) {
        rotationActuelle = nouvelleRotationIndex;
        pieceCourante = nouvellePiece;
        afficherGrille();
    }
}
function update() {
    // Vérifie si le jeu est toujours en cours
    if (ok) {
        // Déplace la pièce d'une unité vers le bas
        deplacer(0, 1);

        // Planifie le prochain appel de la fonction update après 500 millisecondes
        setTimeout(update, 500);
    }
}


// Ajoute un écouteur d'événements pour les touches du clavier
document.addEventListener('keydown', (event) => {
    // Utilise un switch pour gérer différentes touches
    switch(event.key) {
        case 'ArrowUp':
            rotatePiece();  // Fait pivoter la pièce
            break;
        case 'ArrowLeft':
            deplacer(-1, 0);  // Déplace la pièce vers la gauche
            break;
        case 'ArrowRight':
            deplacer(1, 0);  // Déplace la pièce vers la droite
            break;
        case 'ArrowDown':
            deplacer(0, 1);  // Déplace la pièce vers le bas
            break;
        case 'Escape':
            ok = false;  // Arrête le jeu
            break;
    }
});

function gameOver() {
    ok = false;
    document.querySelector("#gameOverMessage").style.display = 'block';
    enregistrerScore(); // Appeler la fonction pour enregistrer le score
}
function enregistrerScore() {
    fetch('record_score.php', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ score: score })
    })
    .then(response => response.json())
    .then(data => {
        console.log('Score enregistré:', data);
        // Vous pouvez afficher un message de succès à l'utilisateur ici
    })
    .catch((error) => {
        console.error('Erreur:', error);
        // Vous pouvez afficher un message d'erreur à l'utilisateur ici
    });
}
function start() {
    ok = true;
    document.querySelector("#gameOverMessage").style.display = 'none';
    genererNouvellePiece();
    afficherGrille();
    afficherScore(); // Affiche le score initial
    update();
}

start(); // Démarre le jeu
});