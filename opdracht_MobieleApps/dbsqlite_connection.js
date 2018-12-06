let express = require('express');
let app = express();
let fs = require("fs");
let bodyParser = require("body-parser");
let mysql = require('mysql');
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());


let server = app.listen(8080, function () {
    // var host = server.address().address
    // var port = server.address().port
    //  console.log("Example app listening at http://%s:%s", host, port)
    console.log("Server draait");
})


app.post('/addUser', function (req, res) {

    console.log(JSON.stringify(req.body))

    var data = JSON.stringify(req.body);

    data = data.substring(1,data.length-1);

    json = JSON.parse(data);

    var email = json["email"];
    var gemeente = json["gemeente"];
    var idemail = json["idemail"];


    console.log(email);
    console.log(gemeente);
    console.log(idemail)

    //DATABASE


    var con = mysql.createConnection({
        host: "localhost",
        user: "root",
        password: "root",
        database: "mydb"
    });


    console.log("Connected!");
    var sql = "INSERT IGNORE INTO User (idemail,email,gemeente) VALUES ? ";
    // email = "" + email;
    // console.log(email);

    var values = [
        [idemail,email, gemeente]
    ];

    con.query(sql, [values], function (err, result) {
        if (err) throw err;
        console.log("Number of records inserted: " + result.affectedRows);
    });

})


app.post('/addZoekertje', function (req, res) {
    var data = JSON.stringify(req.body);
    data = data.substring(1,data.length-1);
    var json = JSON.parse(data);

    var titel = json["titel"];
    var beschrijving = json["beschrijving"];
    var prijs = json["prijs"];
    var image = json["image"];
    var userid = json["userid"];



    var con = mysql.createConnection({
        host: "localhost",
        user: "root",
        password: "root",
        database: "mydb"
    });


    console.log("Connected!");
    var sql = "INSERT INTO Zoekertje (titel,beschrijving,prijs,image,userid) VALUES ? ";

    var values = [
        [titel,beschrijving,prijs,image,userid]
    ];

    con.query(sql, [values], function (err, result) {
        if (err) throw err;
        console.log("Number of records inserted: " + result.affectedRows);
    });

})


app.post('/addBieding', function (req, res) {
    let data = JSON.stringify(req.body);
    data = data.substring(1,data.length-1);
    let json = JSON.parse(data);

    let biedernaam = json["biedernaam"];
    let biederprijs = json["biederprijs"];
    let datum = json["datum"];
    let zoekertjeid = json["zoekertjeid"];

    //  toevoegBieding(biedernaam,biederprijs,datum,zoekertjeid);

    let con = mysql.createConnection({
        host: "localhost",
        user: "root",
        password: "root",
        database: "mydb"
    })



    console.log("Connected!");
    let sql = "INSERT INTO Bieding(biedernaam,biederprijs,datum,zoekertjeid) VALUES ? ";
    let values = [
        [biedernaam,biederprijs,datum,zoekertjeid]
    ];


    con.query(sql, [values], function (err, result) {
        if (err) throw err;

        //RESULT QUERY OMZETTEN
        console.log(result);
        res.send(result);
    });

})


app.post('/getZoekertjesPersoon',function (req,res) {
    let data = JSON.stringify(req.body);
    data = data.substring(1,data.length-1);
    let json = JSON.parse(data);
    let userid = json["userid"];

    let con = mysql.createConnection({
        host: "localhost",
        user: "root",
        password: "root",
        database: "mydb"
    });

    let sql = "Select * FROM Zoekertje Where userid = ?";
    let values = [
        [userid]
    ];

    con.query(sql, [values], function (err, result) {
        if (err) throw err;

        //RESULT QUERY OMZETTEN
        console.log(result);
        res.send(result);

    });

})


app.post('/getAlleZoekertjes',function (req,res) {
    let con = mysql.createConnection({
        host: "localhost",
        user: "root",
        password: "root",
        database: "mydb"
    });

    let sql = "Select * FROM Zoekertje";


    con.query(sql, function (err, result) {
        if (err) throw err;
        console.log(result);
        res.send(result);

    });

})


app.post('/deleteZoekertje', function (req, res) {

    let data = JSON.stringify(req.body);
    data = data.substring(1,data.length-1);
    let json = JSON.parse(data);
    let idZoekertje = json["idZoekertje"];


    let con = mysql.createConnection({
        host: "localhost",
        user: "root",
        password: "root",
        database: "mydb"
    });


    let sql = "DELETE FROM Zoekertje WHERE idZoekertje = ?";

    let values = [
        [idZoekertje]
    ];

    con.query(sql, [values], function (err, result) {
        if (err) throw err;


    })
})


app.post('/getBiedingenVanZoekertje',function (req,res) {
    let data = JSON.stringify(req.body);
    data = data.substring(1,data.length-1);
    let json = JSON.parse(data);
    let zoekertjeid = json["zoekertjeid"];



    let con = mysql.createConnection({
        host: "localhost",
        user: "root",
        password: "root",
        database: "mydb"
    });

    let sql = "Select * FROM Bieding WHERE zoekertjeid = ?";
    let values = [
        [zoekertjeid]
    ];


    con.query(sql, values, function (err, result) {
        if (err) throw err;
        console.log(result);
        res.send(result);

    });

})




function toevoeguser(){
    var mysql = require('mysql');

    var con = mysql.createConnection({
        host: "localhost",
        user: "root",
        password: "root",
        database: "mydb"
    });

    con.connect(function(idemail, email, gemeente) {
        console.log("Connected!");
        var sql = "INSERT INTO User (idemail,email,gemeente) VALUES ? ";
        email = "" + email;
        console.log(email);
        /*   email = JSON.stringify(email);
           idemail = JSON.stringify(idemail);
           gemeente = JSON.stringify(gemeente);
          */
        var values = [
            [idemail,email, gemeente]
        ];


        con.query(sql, [values], function (err, result) {
            if (err) throw err;
            console.log("Number of records inserted: " + result.affectedRows);
        });
    });


}


function toevoegBieding(biedernaam,biederprijs,datum,zoekertjeid){
    let con = mysql.createConnection({
        host: "localhost",
        user: "root",
        password: "root",
        database: "mydb"
    });

    console.log("Connected!");
    let sql = "INSERT INTO Bieding(biedernaam,biederprijs,datum,zoekertjeid)) VALUES ? ";
    let values = [
        [biedernaam,biederprijs,datum,zoekertjeid]
    ];


    con.query(sql, [values], function (err, result) {
        if (err) throw err;
        console.log("Number of records inserted: " + result.affectedRows);
    });


}