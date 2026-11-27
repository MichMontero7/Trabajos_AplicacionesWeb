//Es como un import 
const express = require("express");
const app = express();

const PORT = 3000;

const games = [
    { "id": 1, "Name": "Among Us", "genre": "strategy" },
    { "id": 2, "Name": "Clash Royale", "genre": "Tower Defense" },
    { "id": 3, "Name": "Minecraft", "genre": "Aventure" }
];

//Metodo get para mostrar el objeto de juegos
app.get("/games", (req, res) => {
    res.json(games);
});

app.listen(PORT, () =>{ //Arrow function
    console.log("App is running on port " + PORT);
})
