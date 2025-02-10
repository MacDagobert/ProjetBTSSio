console.log("Le fichier script.js est chargé");
let score = 0;


let grille = [
    "+--------------------------+",
    "|                          |",
    "|                          |",
    "|                          |",
    "|                          |",
    "|                          |",
    "|                          |",
    "|                          |",
    "|                          |",
    "|                          |",
    "|                          |",
    "|                          |",
    "|                          |",
    "|                          |",
    "|                          |",
    "|                          |",
    "+--------------------------+"
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
const J = [
    [
      [1, 0, 0],
      [1, 1, 1],
      [0, 0, 0]
    ],
    [
      [0, 1, 1],
      [0, 1, 0],
      [0, 1, 0]
    ],
    [
      [0, 0, 0],
      [1, 1, 1],
      [0, 0, 1]
    ],
    [
      [0, 1, 0],
      [0, 1, 0],
      [1, 1, 0]
    ]
  ];

  const L = [
    [
        [0, 0, 1],
        [1, 1, 1],
        [0, 0, 0]
    ],
    [
        [0, 1, 0],
        [0, 1, 0],
        [0, 1, 1]
    ],
    [
        [0, 0, 0],
        [1, 1, 1],
        [1, 0, 0]
    ],
    [
        [1, 1, 0],
        [0, 1, 0],
        [0, 1, 0]
    ]
];

const O = [
    [
        [0, 1, 1, 0],
        [0, 1, 1, 0],
        [0, 0, 0, 0]
    ]
];

const S = [
    [
        [0, 1, 1],
        [1, 1, 0],
        [0, 0, 0]
    ],
    [
        [0, 1, 0],
        [0, 1, 1],
        [0, 0, 1]
    ],
    [
        [0, 0, 0],
        [0, 1, 1],
        [1, 1, 0]
    ],
    [
        [1, 0, 0],
        [1, 1, 0],
        [0, 1, 0]
    ]
];

const Z = [
    [
        [1, 1, 0],
        [0, 1, 1],
        [0, 0, 0]
    ],
    [
        [0, 0, 1],
        [0, 1, 1],
        [0, 1, 0]
    ],
    [
        [0, 0, 0],
        [1, 1, 0],
        [0, 1, 1]
    ],
];

    const T = [
    [
        [0, 1, 0],
        [1, 1, 1],
        [0, 0, 0]
    ],
    [
        [0, 1, 0],
        [0, 1, 1],
        [0, 1, 0]
    ],
    [
        [0, 0, 0],
        [1, 1, 1],
        [0, 1, 0]
    ],
    [
        [0, 1, 0],
        [1, 1, 0],
        [0, 1, 0]
    ]
];

function affichageTetrominos(forme) {
    const tetrominos = Tetrominos[forme];
    const container = document.getElementById('bloc');
    for (let y = 0; y < tetrominos.length; y++) {
        for (let x = 0; x < tetrominos[y].length; x++) {
            if (tetrominos[y][x] === 1) {
                const bloc = document.createElement('div');
                bloc.style.gridRowStart = y + 1;
                bloc.style.gridColumnStart = x + 1;
                bloc.classList.add('tetromino');
                container.appendChild(bloc);
            }
        }
    }
}

function afficherGrille() {
    let grilleTemp = grille.map(row => row.slice());
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

function mouvementValide(dx, dy) {
    for (let i = 0; i < pieceCourante.length; i++) {
        for (let j = 0; j < pieceCourante[i].length; j++) {
            if (pieceCourante[i][j]) {
                let newX = x + j + dx;
                let newY = y + i + dy;
                if (newX < 1 || newX > grille[0].length - 2 || newY > grille.length - 2 || newY < 1 || grille[newY][newX] !== ' ') {
                    return false;
                }
            }
        }
    }
    return true;
}


function fixerPiece() {
    for (let i = 0; i < pieceCourante.length; i++) {
        for (let j = 0; j < pieceCourante[i].length; j++) {
            if (pieceCourante[i][j] === 1) {
                let newY = y + i;
                let newX = x + j;
                if (newY >= 1 && newY < grille.length - 1 && newX >= 1 && newX < grille[0].length - 1) {
                    if (grille[newY][newX] === ' ') {
                        grille[newY] = grille[newY].substring(0, newX) + '#' + grille[newY].substring(newX + 1);
                    } else {
                        console.log("Collision détectée lors de la fixation de la pièce");
                        return false;
                    }
                }
            }
        }
    }
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
        score += lignesSupprimees * 10; // 100 points par ligne
        afficherScore(); // Met à jour l'affichage du score
    }
    
    return lignesSupprimees;
}

function afficherScore() {
    document.getElementById("score").innerText = "Score : " + score;
}



function genererNouvellePiece() {
    const pieces = [I, J, L, O, S, T, Z];
    const pieceChoisie = pieces[Math.floor(Math.random() * pieces.length)];
    

    rotationActuelle = Math.floor(Math.random() * pieceChoisie.length);
    pieceCourante = pieceChoisie[rotationActuelle];
    x = Math.floor((grille[0].length - pieceCourante[0].length) / 2);
    y = 1;
    if (!mouvementValide(0, 0)) {
        ok = false;
        document.querySelector("#gameOverMessage").style.display = 'block';
    }
}


function deplacer(dx, dy) {
    if (!ok) return;
    if (mouvementValide(dx, dy)) {
        x += dx;
        y += dy;
    } else if (dy === 1) {
        fixerPiece();
        supprimerLignesPleines();
        genererNouvellePiece();
    }
    afficherGrille();
}

function rotatePiece() {
    // Correction : Vérification des index et de la syntaxe
    let pieceActuelle = pieceCourante === I[rotationActuelle] ? I :
                        pieceCourante === J[rotationActuelle] ? J :
                        pieceCourante === L[rotationActuelle] ? L :
                        pieceCourante === O[rotationActuelle] ? O : // Correction ici : 'rotationActuelle' au lieu de 'rotation Actuelle'
                        pieceCourante === S[rotationActuelle] ? S :
                        pieceCourante === T[rotationActuelle] ? T : Z;

    let nouvelleRotation = (rotationActuelle + 1) % pieceActuelle.length;
    let nouvellePiece = pieceActuelle[nouvelleRotation];

    // Vérifie si le mouvement est valide avant d'appliquer la rotation
    if (mouvementValide(0, 0, nouvellePiece)) {
        rotationActuelle = nouvelleRotation; // Met à jour la rotation actuelle
        pieceCourante = nouvellePiece;      // Met à jour la pièce actuelle
        afficherGrille();                   // Met à jour l'affichage
    }
}





document.addEventListener('keydown', (event) => {
    switch(event.key) {
        case 'ArrowUp': rotatePiece(); break;
        case 'ArrowLeft': deplacer(-1, 0); break;
        case 'ArrowRight': deplacer(1, 0); break;
        case 'ArrowDown': deplacer(0, 1); break;
        case 'Escape': ok = false; break;
    }
});

function update() {
    if (ok) {
        deplacer(0, 1);
        setTimeout(update, 500);
    }
}

function start() {
    ok = true;
    document.querySelector("#gameOverMessage").style.display = 'none';
    genererNouvellePiece();
    afficherGrille();
    update();
}

afficherScore();


start();
