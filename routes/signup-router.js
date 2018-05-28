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
    var str = Object.keys(req.body);
    var obj = JSON.parse(str[0]);
    userId = obj.id;
    userPassword = obj.password;
    userName = obj.name;
    userEmail = obj.email;

    if(userId == '' || userPassword == '' || userName == '' || userEmail == '') {

        return res.json({success:false, valid:false});
    } else {

    }

    signupAdapter.signupWrite(obj, function(resultCode, rows){
        if(resultCode == dbConnection.OK){
            res.json({"success":true});
        } else {
            console.log("false reason: id duplicated");
            res.json({"success":false, "valid":true});
        }
    });
});
module.exports = router;
