console.log("Le fichier script.js est chargé");

let grille = [
    "+---------------+",
    "|               |",
    "|               |",
    "|               |",
    "|               |",
    "|               |",
    "|               |",
    "|               |",
    "|               |",
    "|               |",
    "+---------------+"
];

let x = 7; // Position initiale colonne
let y = 1; // Position initiale ligne
let ok = true; // Contrôle du jeu

// Fonction pour afficher la grille dans le HTML
function afficherGrille() {
    document.querySelector("#jeu").innerHTML = grille.map(ligne => ligne.replace(/ /g, "&nbsp;")).join("<br>");
}

// // Fonction comparatif de grille
// // Compare deux grilles 
// function comparer(g1, g2) {
//     for (int i=0; i<g1.length; i++)
//         if ((g1[i]).compareTo(g2.grille[i])!=0) return false;
//     return true;
// }

// Fonction pour placer ou effacer un caractère à une position donnée
function placerCar(y, x, c) {
    grille[y] = grille[y].slice(0, x) + c + grille[y].slice(x + 1);
}

// Fonction pour vérifier si une ligne est pleine
function lignePleine(ligne) {
    return ligne === "|###############|"; // Vérifie si la ligne est complètement remplie (toutes les cases sont remplies)
}

// Fonction pour supprimer une ligne pleine et décaler les autres
function supprimerLigne() {
    for (let i = grille.length - 2; i >= 1; i--) {  // On commence à la ligne juste avant la dernière ligne et on remonte
        if (lignePleine(grille[i])) {  // Si la ligne est pleine
            grille.splice(i, 1);  // Supprime la ligne pleine
            grille.splice(1, 0, "|               |");  // Ajoute une nouvelle ligne vide en haut
            break; // Sortir de la boucle après avoir supprimé la ligne
        }
    }
}

// Fonction pour déplacer le joueur
function deplacer(dx, dy) {
    if (!ok) return; // Si le jeu est terminé, on ne fait rien

    if (grille[y + dy] && grille[y + dy][x + dx] === " ") {  // Vérifie si la nouvelle case est vide
        // Effacer la position précédente et déplacer
        placerCar(y, x, " ");
        x += dx;
        y += dy;
        placerCar(y, x, "#"); // Placer le joueur à la nouvelle position
        afficherGrille();  // Afficher la grille mise à jour
    } else if (dy === 1 && (x !== 7 || y !== 1)) {  // Si le joueur arrive en bas de la grille
        // Réinitialiser la position du joueur
        y = 1;
        x = 7;
        placerCar(y, x, "#");
        afficherGrille();
    } else if (x === 7 && y === 1) {  // Game Over si le joueur touche le bas
        document.querySelector("#gameOverMessage").style.display = 'block';
        ok = false;
    }

    // Vérifier et supprimer les lignes pleines après chaque mouvement
    supprimerLigne();
}

// Fonction pour écouter les touches du clavier
function ecouterTouches() {
    document.addEventListener('keydown', (event) => {
        switch (event.key) {
            case 'ArrowLeft': deplacer(-1, 0); break;
            case 'ArrowRight': deplacer(1, 0); break;
            case 'ArrowDown': deplacer(0, 1); break;
            case 'Escape': ok = false; break;
        }
    });
}

// Fonction pour démarrer le jeu
function start() {
    document.querySelector("#gameOverMessage").style.display = 'none';  // Cacher le message "Game Over"
    placerCar(y, x, "#"); // Placer le joueur à la position initiale
    afficherGrille(); // Afficher la grille initiale
    ecouterTouches(); // Écouter les événements de touche
    setTimeout(update, 500); // Démarrer l'update automatique du jeu
}

// Fonction de mise à jour du jeu (déplacement automatique)
function update() {
    if (ok) {
        deplacer(0, 1); // Déplacer le joueur vers le bas
        if (ok) setTimeout(update, 500); // Replanifier l'update à intervalles réguliers
    }
}

start(); // Démarre le jeu
