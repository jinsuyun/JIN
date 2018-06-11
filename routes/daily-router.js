var express = require('express');
var router = express.Router();
var dailyAdapter = require('../adapters/appdaily-db-adapter');
var dbConnection = require('./result');

router.post('/', function(req, res) {

    console.log(req.body);
    var str = Object.keys(req.body);
    var obj = JSON.parse(str[0]);
    console.log(obj);
    //
    // dailyAdapter.dailySearch(req.body.id, function(resultCode,rows){
    //     if(resultCode == dbConnection.OK){
    //         var response;
    //         console.log(rows);
    //         response = Object.assign(rows, {"success":true});
    //         res.json(response);
    //     } else {
    //         res.json({"success":false});
    //     }
    // });

    dailyAdapter.dailyWrite(obj, function(resultCode,rows){
        if(resultCode == dbConnection.OK){
            res.json({"success":true});
        } else {
            console.log("false reason: id duplicated");
            res.json({"success":false});
        }
    });
});

module.exports = router;
