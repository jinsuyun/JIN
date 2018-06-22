var express = require('express');
var router = express.Router();
var loginAdapter = require('../adapters/trainer-login-db-adapter');
var dbConnection = require('./result');

var userId;
var userPassword;

router.get('/', function(req, res) {
    res.render('staty.html');
});

router.post('/', function(req, res) {
    

    userId = req.body.user_id;
    userPassword = req.body.password;

    if(userId == undefined || userPassword == undefined) {
        console.log("undefined")
        return res.json({success:false});
    } else {

    }

    loginAdapter.loginSearch(userId, userPassword, function(resultCode,rows){
        if(resultCode == dbConnection.OK){
            if(rows.length > 0){
                if(rows[0].password == req.body.password){
                    if (resultCode == dbConnection.OK) {
                        console.log("login success");
                        res.render("graph.html");
                    }
                    else {
                        console.log("false reason: db disconnected");
                    }
                }
                else {
                    console.log("false reason: wrong pw");
                }
            }
            else {
                console.log("false reason: wrong id");
            }
        }
        else {
            console.log("false reason: query false");
        }
    });
});

module.exports = router;
