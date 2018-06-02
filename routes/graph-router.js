var express = require('express');
var router = express.Router();
var dailyAdapter = require('../adapters/appdaily-db-adapter');
var dbConnection = require('./result');

router.get('/', function(req, res) {

    var id = req.params.id;
    res.render('graph.html');
});

router.post('/', function(req, res) {

    var str = Object.keys(req.body);
    var obj = JSON.parse(str[0]);
    userId = obj.id;

    if(userId == '') {
        return res.json({success:false});
    } else {

    }

    dailyAdapter.dailySearch(userId, function(resultCode,rows){
        if(resultCode == dbConnection.OK){
            if(rows.length > 0){
                if (resultCode == dbConnection.OK) {
                    var response;
                    response = Object.assign(rows[0], {"success":true});
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

})

module.exports = router;
