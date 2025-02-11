console.log("Le fichier script.js est chargé");
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


function afficherGrille() {
    // Crée une copie profonde de la grille pour ne pas modifier l'originale
    let grilleTemp = grille.map(row => row.slice());

    // Parcourt chaque élément de la pièce courante
    for (let i = 0; i < pieceCourante.length; i++) {
        for (let j = 0; j < pieceCourante[i].length; j++) {
            // Si l'élément de la pièce est un 1 (partie pleine de la pièce)
            if (pieceCourante[i][j] === 1) {
                // Calcule les nouvelles coordonnées dans la grille
                let newY = y + i;
                let newX = x + j;

                // Vérifie si les nouvelles coordonnées sont dans les limites de la grille
                if (newY >= 1 && newY < grilleTemp.length - 1 && newX >= 1 && newX < grilleTemp[0].length - 1) {
                    // Remplace le caractère à la position calculée par '#'
                    grilleTemp[newY] = grilleTemp[newY].substring(0, newX) + '#' + grilleTemp[newY].substring(newX + 1);
                }
            }
        }
    }

    // Affiche la grille mise à jour dans l'élément HTML avec l'id "jeu"
    // Remplace les espaces par &nbsp; pour préserver les espaces dans le HTML
    document.querySelector("#jeu").innerHTML = grilleTemp.map(ligne => ligne.replace(/ /g,"&nbsp;")).join("<br>");
}


function mouvementValide(dx, dy, pc) {
    // Parcourt chaque élément de la pièce courante
    for (let i = 0; i < pieceCourante.length; i++) {
        for (let j = 0; j < pieceCourante[i].length; j++) {
            // Si l'élément de la pièce est non nul (partie pleine de la pièce)
            if (pc[i][j]) {
                // Calcule les nouvelles coordonnées après le mouvement
                let newX = x + j + dx;
                let newY = y + i + dy;

                // Vérifie si le mouvement est valide :
                // - Pas de sortie à gauche ou à droite de la grille
                // - Pas de sortie en bas ou en haut de la grille
                // - La nouvelle position n'est pas déjà occupée
                if (newX < 1 || newX > grille[0].length - 2 || 
                    newY > grille.length - 2 || newY < 1 || 
                    grille[newY][newX] !== ' ') {
                    return false; // Le mouvement n'est pas valide
                }
            }
        }
    }
    return true; // Si on arrive ici, le mouvement est valide
}



function fixerPiece() {
    // Parcourt chaque élément de la pièce courante
    for (let i = 0; i < pieceCourante.length; i++) {
        for (let j = 0; j < pieceCourante[i].length; j++) {
            // Si l'élément de la pièce est un 1 (partie pleine de la pièce)
            if (pieceCourante[i][j] === 1) {
                // Calcule les coordonnées dans la grille
                let newY = y + i;
                let newX = x + j;

                // Vérifie si les coordonnées sont dans les limites de la grille
                if (newY >= 1 && newY < grille.length - 1 && newX >= 1 && newX < grille[0].length - 1) {
                    // Si la position dans la grille est vide
                    if (grille[newY][newX] === ' ') {
                        // Remplace l'espace vide par '#' pour fixer la pièce
                        grille[newY] = grille[newY].substring(0, newX) + '#' + grille[newY].substring(newX + 1);
                    } else {
                        // Si la position n'est pas vide, il y a une collision
                        console.log("Collision détectée lors de la fixation de la pièce");
                        return false; // La fixation a échoué
                    }
                }
            }
        }
    }

    // Ajoute 10 points pour la pièce posée
    score += 10;
    afficherScore(); // Met à jour l'affichage du score

    return true; // La fixation a réussi
}


function supprimerLignesPleines() {
    let lignesSupprimees = 0;

    // Parcourt la grille de bas en haut, en excluant la première et la dernière ligne
    for (let i = grille.length - 2; i > 0; i--) {
        // Vérifie si tous les caractères de la ligne (sauf les bords) sont des '#'
        if (grille[i].slice(1, -1).split('').every(char => char === '#')) {
            // Supprime la ligne pleine
            grille.splice(i, 1);
            // Ajoute une nouvelle ligne vide en haut de la grille
            grille.splice(1, 0, "|                        |");
            // Incrémente le compteur de lignes supprimées
            lignesSupprimees++;
        }
    }

    if (lignesSupprimees > 0) {
        score += lignesSupprimees * 100; // 100 points par ligne
        afficherScore(); // Met à jour l'affichage du score
    }

    // Retourne le nombre de lignes supprimées
    return lignesSupprimees;
}

function afficherScore() {
    document.getElementById("score").innerText = "Score : " + score;
}



function genererNouvellePiece() {
    // Définit un tableau contenant toutes les formes de pièces possibles
    const pieces = [I, O, L, T, S, J, Z];

    // Sélectionne aléatoirement une pièce parmi les formes disponibles
    const pieceAleatoire = pieces[Math.floor(Math.random() * pieces.length)];

    // Définit la pièce courante comme la première rotation de la pièce aléatoire
    pieceCourante = pieceAleatoire[0];

    // Calcule la position x initiale pour centrer la pièce horizontalement
    x = Math.floor((grille[0].length - pieceCourante[0].length) / 2);

    // Définit la position y initiale (en haut de la grille)
    y = 1;

    // Vérifie si la position initiale de la nouvelle pièce est valide
    if (!mouvementValide(0, 0, pieceCourante)) {
        // Si la position n'est pas valide, le jeu est terminé
        ok = false;
        // Affiche le message de fin de jeu
        document.querySelector("#gameOverMessage").style.display = 'block';
    }
}



function deplacer(dx, dy) {
    // Vérifie si le jeu est toujours en cours
    if (!ok) return;

    // Vérifie si le mouvement proposé est valide
    if (mouvementValide(dx, dy, pieceCourante)) {
        // Si le mouvement est valide, met à jour les coordonnées de la pièce
        x += dx;
        y += dy;
    } else if (dy === 1) {
        // Si le mouvement vers le bas n'est pas valide (la pièce touche le fond ou une autre pièce)
        // Fixe la pièce à sa position actuelle
        fixerPiece();
        // Vérifie et supprime les lignes pleines
        supprimerLignesPleines();
        // Génère une nouvelle pièce
        genererNouvellePiece();
    }

    // Met à jour l'affichage de la grille
    afficherGrille();
}


function rotatePiece() {
    // Définit un tableau contenant toutes les formes de pièces possibles
    const pieces = [I, O, L, T, S, J, Z];

    // Trouve l'index de la pièce courante dans le tableau des pièces
    const currentPieceIndex = pieces.findIndex(piece => piece.includes(pieceCourante));

    // Calcule l'index de la prochaine rotation
    let nouvelleRotationIndex = (rotationActuelle + 1) % pieces[currentPieceIndex].length;

    // Obtient la nouvelle forme de la pièce après rotation
    let nouvellePiece = pieces[currentPieceIndex][nouvelleRotationIndex];

    // Vérifie si la nouvelle position après rotation est valide
    if (mouvementValide(0, 0, nouvellePiece)) {
        // Si la rotation est valide, met à jour l'index de rotation
        rotationActuelle = nouvelleRotationIndex;
        // Met à jour la pièce courante avec sa nouvelle forme
        pieceCourante = nouvellePiece;
        // Met à jour l'affichage de la grille
        afficherGrille();
    }
}

function afficherScore() {
    document.getElementById("score").innerText = "Score : " + score;
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


function update() {
    // Vérifie si le jeu est toujours en cours
    if (ok) {
        // Déplace la pièce d'une unité vers le bas
        deplacer(0, 1);

        // Planifie le prochain appel de la fonction update après 500 millisecondes
        setTimeout(update, 500);
    }
}

function start() {
    // Initialise l'état du jeu à "en cours"
    ok = true;

    // Cache le message de fin de jeu s'il était affiché
    document.querySelector("#gameOverMessage").style.display = 'none';

    // Génère la première pièce du jeu
    genererNouvellePiece();

    // Affiche la grille initiale
    afficherGrille();

    // Démarre la boucle de jeu
    update();
}

afficherScore();

// Lance le jeu immédiatement
start();
