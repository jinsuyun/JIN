var express = require('express');
var router = express.Router();
var signupAdapter =require('../adapters/signup-db-adapter');
var dbConnection = require('./result');

var userId;
var userPassword;
var userName;
var userEmail;

router.post('/', function(req, res) {

    console.log(req.body);
    userId = req.body.id;
    userPassword = req.body.password;
    userName = req.body.name;
    userEmail = req.body.email;

    if(userId == undefined || userPassword == undefined || userName == undefined || userEmail == undefined) {
        return res.json({success:false});
    } else {

    }

    signupAdapter.signupWrite(req.body, function(resultCode, rows){
        if(resultCode == dbConnection.OK){
            res.json({"success":true});
        }
        else {
            console.log("false reason: wrong id");
            res.json({"success":false});
        }
    });
});
module.exports = router;
