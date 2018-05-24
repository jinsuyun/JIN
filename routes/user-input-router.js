var express = require('express');
var router = express.Router();
var userinfoAdapter =require('../adapters/userinfo-db-adapter');
var dbConnection = require('./result');

var userId;
var userSex;
var userAge;
var userWeight;
var userHeight;
var userTargetWeight;
var userTargetPeriod;
var userWorkLevel;

router.post('/', function(req, res) {

    userId = req.body.id;
    userSex = req.body.sex;
    userAge = req.body.age;
    userWeight = req.body.weight;
    userHeight = req.body.height;
    userTargetWeight = req.body.targetweight;
    userTargetPeriod = req.body.targetperiod;
    userWorkLevel = req.body.worklevel;

    if(userId == undefined || userSex == undefined || userAge == undefined || userWeight == undefined
        || userHeight == undefined || userTargetWeight == undefined || userTargetPeriod == undefined || userWorkLevel == undefined) {
        return res.json({success:false});
    } else {

    }

    userinfoAdapter.write(req.body, function(resultCode, rows){
        if(resultCode == dbConnection.OK){
            res.json({"success":true});
        }
        else {
            console.log("false reason: query false");
            res.json({"success": false});
        }
    });
});

module.exports = router;
