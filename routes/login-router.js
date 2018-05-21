var express = require('express');
var router = express.Router();
var loginAdapter = require('../adapters/login-db-adapter');
var dbConnection = require('./result');

var userId;
var userPassword;

router.post('/', function(req, res) {

    userId = req.body.id;
    userPassword = req.body.password;

    if(userId == undefined || userPassword == undefined) {
        return res.json({success:false});
    } else {

    }

    loginAdapter.loginSearch(userId, userPassword, function(resultCode,rows){
        if(resultCode == dbConnection.OK){
            if(rows.length > 0){
                if(rows[0].password == req.body.password){
                    if (resultCode == dbConnection.OK) {
                        console.log("login success");
                        res.json({"success":true});
                    }
                    else {
                        console.log("false reason: db disconnected");
                        res.json({"success": false});
                    }
                }
                else {
                    console.log("false reason: wrong pw");
                    res.json({"success":false});
                }
            }
            else {
                console.log("false reason: wrong id");
                res.json({"success":false});
            }
        }
        else {
            console.log("false reason: query false");
            res.json({"success": false});
        }
    });
});

module.exports = router;
