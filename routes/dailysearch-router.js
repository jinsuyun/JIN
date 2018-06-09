var express = require('express');
var router = express.Router();
var dailyAdapter = require('../adapters/appdaily-db-adapter');
var dbConnection = require('./result');

router.post('/', function(req, res) {

    var str = Object.keys(req.body);
    var obj = JSON.parse(str[0]);
    console.log(obj);

    dailyAdapter.dailySearch(obj.id, function(resultCode,rows){
        if(resultCode == dbConnection.OK){
            res.json(rows);
        } else {
            res.json({"success":false});
        }
    });

});

module.exports = router;
