var express = require('express');
var router = express.Router();
var dailyAdapter = require('../adapters/appdaily-db-adapter');

router.post('/', function(req, res) {

    console.log(req.body);
    var str = Object.keys(req.body);
    var obj = JSON.parse(str[0]);

    dailyAdapter.dailyWrite(obj, function(resultCode,rows){
        var response;
        if(resultCode == dbConnection.OK){
            response = Object.assign(rows[0], {"success":true});
            res.json(response);
        } else {
            console.log("false reason: id duplicated");
            res.json({"success":false});
        }
    });
});

module.exports = router;
