var express = require('express');
var router = express.Router();
var dailyAdapter = require('../adapters/appdaily-db-adapter');
var dbConnection = require('./result');

router.post('/', function(req, res) {

    var str = Object.keys(req.body);
    var obj = JSON.parse(str[0]);
    console.log(obj);

    dailyAdapter.dailyWrite(obj, function(resultCode,rows){
        if(resultCode == dbConnection.OK){
            console.log(rows);
            res.json({"success":true});
        } else {
            console.log("false reason: id duplicated");
            res.json({"success":false});
        }
    });
});

module.exports = router;
