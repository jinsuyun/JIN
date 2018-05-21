var express = require('express');
var router = express.Router();
var loginAdapter = require('../adapters/login-db-adapter');
var dbConnection = require('./result');

router.post('/', function(req, res) {

    console.log(req.body);

    userId = req.body.id;
    userPassword = req.body.password;

    if(userId == undefined || userPassword == undefined) {
        return res.json({success:false});
    } else {

    }

    loginAdapter.loginSearch(id, password, function(resultCode,rows){
        if(resultCode == dbConnection.OK){
            if(rows.length>0){
                if(rows[0].password == req.body.password){
                    if (resultCode == dbConnection.OK) {
                        res.json({"success":true});
                    }
                    else {
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
            res.json({"success": false})
        }
    });
});

module.exports = router;
