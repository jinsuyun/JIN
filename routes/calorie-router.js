var express = require('express');
var router = express.Router();
var calorieAdapter = require('../adapters/calorie-db-adapter');
var dbConnection = require('./result');

router.post('/', function(req, res) {

    var str = Object.keys(req.body);
    var obj = JSON.parse(str[0]);

    calorieAdapter.calorieWrite(obj, function(resultCode,rows){
        if(resultCode == dbConnection.OK){
            res.json({"success":true});
        } else {
            res.json({"success":false});
        }
    });
});

module.exports = router;
