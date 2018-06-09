var express = require('express');
var router = express.Router();
var foodlistAdapter = require('../adapters/foodlist-db-adapter');
var dbConnection = require('./result');

router.get('/', function(req, res) {

    foodlistAdapter.foodlistSearch(function(resultCode,rows){
        if(resultCode == dbConnection.OK){
            var response;
            console.log(rows);
            response = Object.assign(rows, {"success":true});
            res.json(response);
        } else {
            res.json({"success":false});
        }
    });
});

module.exports = router;
