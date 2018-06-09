var express = require('express');
var router = express.Router();
var calorieAdapter = require('../adapters/calorie-db-adapter');
var dbConnection = require('./result');

router.get('/', function(req, res) {

    calorieAdapter.calorieWrite(function(resultCode,rows){
        if(resultCode == dbConnection.OK){
            res.json({"success":true});
        } else {
            res.json({"success":false});
        }
    });
});

module.exports = router;
