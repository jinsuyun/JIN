var express = require('express');
var router = express.Router();
var loginAdapter = require('../adapters/login-db-adapter');
var dbConnection = require('./result');

var userId;
var userPassword;

router.post('/', function(req, res) {

    var str = Object.keys(req.body);
    var obj = JSON.parse(str[0]);
    userId = obj.id;
    userPassword = obj.password;

    if(userId == '' || userPassword == '') {
        return res.json({success:false});
    } else {

    }

    loginAdapter.loginSearch(userId, userPassword, function(resultCode,rows){
        if(resultCode == dbConnection.OK){
            if(rows.length > 0){
                if (resultCode == dbConnection.OK) {
                    var response;
                    console.log("login success");
                    if(rows[0].sex == undefined || rows[0].age == undefined || rows[0].weight == undefined
                        || rows[0].height == undefined || rows[0].targetweight == undefined || rows[0].targetperiod == undefined
                        || rows[0].workperiod == undefined || rows[0].worklevel == undefined) {
                        response = Object.assign(rows[0], {"success":true, "survey":false});
                    } else {
                        response = Object.assign(rows[0], {"success":true, "survey":true});
                    }
                    console.log(response);
                    res.json(response);
                }
                else {
                    console.log("false reason: db disconnected");
                    res.json({"success": false});
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
