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

function afficherGrille() {
    document.querySelector("#jeu").innerHTML = grille.map(ligne => ligne.replace(/ /g, "&nbsp;")).join("<br>");
}

function placerCar(y, x, c) {
    grille[y] = grille[y].slice(0, x) + c + grille[y].slice(x + 1);
}

function deplacer(dx, dy) {
    if (!ok) return;
    if (grille[y + dy] && grille[y + dy][x + dx] === " ") {
        placerCar(y, x, " ");
        x += dx;
        y += dy;
        placerCar(y, x, "#");
        afficherGrille();
    } else if (dy === 1 && (x !== 7 || y !== 1)) {
        y = 1;
        x = 7;
        placerCar(y, x, "#");
        afficherGrille();
    } else if (x === 7 && y === 1) {
        document.querySelector("#gameOverMessage").style.display = 'block';
        ok = false;
    }
}

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

function start() {
    document.querySelector("#gameOverMessage").style.display = 'none';
    placerCar(y, x, "#");
    afficherGrille();
    ecouterTouches();
    setTimeout(update, 500);
}

function update() {
    if (ok) {
        deplacer(0, 1);
        if (ok) setTimeout(update, 500);
    }
}

start();
